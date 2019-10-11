package hangeul4s.model.hangeul

import java.text.Normalizer

import cats.syntax.either._ // required for toOption method in Scala 2.11
import hangeul4s.encoding.{Codec, Decoder, Encoder}
import hangeul4s.error.DecodingFailure
import hangeul4s.syntax.string.StringOps

sealed trait HangeulSyllabicBlock {

  def initial: HangeulJamo.Initial

  def medial: HangeulJamo.Medial

  def maybeFinal: Option[HangeulJamo.Final] = this match {
    case HangeulSyllabicBlock.TwoLetter(_, _)      => None
    case HangeulSyllabicBlock.ThreeLetter(_, _, f) => Some(f)
  }

  def charSequence: CharSequence = this match {
    case HangeulSyllabicBlock.TwoLetter(i, m)      => s"${i.char}${m.char}"
    case HangeulSyllabicBlock.ThreeLetter(i, m, f) => s"${i.char}${m.char}${f.char}"
  }

  override def toString: String =
    Encoder[HangeulSyllabicBlock, Char]
      .encode(this)
      .toString

}

object HangeulSyllabicBlock {

  final case class TwoLetter(
    initial: HangeulJamo.Initial,
    medial: HangeulJamo.Medial
  ) extends HangeulSyllabicBlock

  final case class ThreeLetter(
    initial: HangeulJamo.Initial,
    medial: HangeulJamo.Medial,
    _final: HangeulJamo.Final
  ) extends HangeulSyllabicBlock

  def twoLetter(
    initial: HangeulJamo.Initial,
    medial: HangeulJamo.Medial
  ): HangeulSyllabicBlock =
    TwoLetter(initial, medial)

  def threeLetter(
    initial: HangeulJamo.Initial,
    medial: HangeulJamo.Medial,
    _final: HangeulJamo.Final
  ): HangeulSyllabicBlock =
    ThreeLetter(initial, medial, _final)

  /**
    * Instance for decoding/encoding [[HangeulSyllabicBlock]] from/to Char
    *
    * The decimal code point of precomposed Hangeul syllables in the Hangul Syllables Unicode block can be derived from
    * the indices of their constituent [[HangeulJamo]] characters using the following formula:
    *
    *   ((initial) × 588 + (medial) × 28 + (final)) + 44032
    *
    * See https://en.wikipedia.org/wiki/Korean_language_and_computers#Hangul_in_Unicode
    */
  implicit val charCodec: Codec[Char, HangeulSyllabicBlock] =
    new Codec[Char, HangeulSyllabicBlock] {
      override def decode(encoded: Char): Either[DecodingFailure, HangeulSyllabicBlock] = {
        val decomposition = Normalizer.normalize(encoded.toString, Normalizer.Form.NFD)

        (
          decomposition.safeCharAt(0).flatMap(Decoder[Char, HangeulJamo.Initial].decode(_).toOption),
          decomposition.safeCharAt(1).flatMap(Decoder[Char, HangeulJamo.Medial].decode(_).toOption),
          decomposition.safeCharAt(2).flatMap(Decoder[Char, HangeulJamo.Final].decode(_).toOption)
        ) match {
          case (Some(initial), Some(medial), None) =>
            Right(HangeulSyllabicBlock.TwoLetter(initial, medial))
          case (Some(initial), Some(medial), Some(_final)) =>
            Right(HangeulSyllabicBlock.ThreeLetter(initial, medial, _final))
          case _ =>
            Left(DecodingFailure.FailedToDecodeHangeulSyllabicBlock(encoded))
        }
      }

      override def encode(decoded: HangeulSyllabicBlock): Char =
        Normalizer.normalize(decoded.charSequence, Normalizer.Form.NFC).charAt(0)
    }

}
