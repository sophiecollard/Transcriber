package hangeul4s.model.hangeul

import java.text.Normalizer

import cats.syntax.either._ // required for toOption method in Scala 2.11
import hangeul4s.encoding.{Codec, Decoder, Encoder}
import hangeul4s.error.DecodingFailure
import hangeul4s.syntax.string.StringOps

/**
  * Type representing a character from the Hangeul Syllables Unicode block
  */
sealed trait HangeulSyllable {

  def initial: HangeulJamo.Initial

  def medial: HangeulJamo.Medial

  def maybeFinal: Option[HangeulJamo.Final] = this match {
    case HangeulSyllable.TwoJamo(_, _)      => None
    case HangeulSyllable.ThreeJamo(_, _, f) => Some(f)
  }

  def charSequence: CharSequence = this match {
    case HangeulSyllable.TwoJamo(i, m)      => s"${i.char}${m.char}"
    case HangeulSyllable.ThreeJamo(i, m, f) => s"${i.char}${m.char}${f.char}"
  }

  override def toString: String =
    Encoder[HangeulSyllable, Char]
      .encode(this)
      .toString

}

object HangeulSyllable {

  final case class TwoJamo(
    initial: HangeulJamo.Initial,
    medial: HangeulJamo.Medial
  ) extends HangeulSyllable

  final case class ThreeJamo(
    initial: HangeulJamo.Initial,
    medial: HangeulJamo.Medial,
    _final: HangeulJamo.Final
  ) extends HangeulSyllable

  def twoJamo(
    initial: HangeulJamo.Initial,
    medial: HangeulJamo.Medial
  ): HangeulSyllable =
    TwoJamo(initial, medial)

  def threeJamo(
    initial: HangeulJamo.Initial,
    medial: HangeulJamo.Medial,
    _final: HangeulJamo.Final
  ): HangeulSyllable =
    ThreeJamo(initial, medial, _final)

  /**
    * Instance for decoding/encoding [[HangeulSyllable]] from/to Char
    *
    * The decimal code point of precomposed Hangeul syllables in the Hangul Syllables Unicode block can be derived from
    * the indices of their constituent [[HangeulJamo]] characters using the following formula:
    *
    *   ((initial) × 588 + (medial) × 28 + (final)) + 44032
    *
    * See https://en.wikipedia.org/wiki/Korean_language_and_computers#Hangul_in_Unicode
    */
  implicit val charCodec: Codec[Char, HangeulSyllable] =
    new Codec[Char, HangeulSyllable] {
      override def decode(encoded: Char): Either[DecodingFailure, HangeulSyllable] = {
        val decomposition = Normalizer.normalize(encoded.toString, Normalizer.Form.NFD)

        (
          decomposition.safeCharAt(0).flatMap(Decoder[Char, HangeulJamo.Initial].decode(_).toOption),
          decomposition.safeCharAt(1).flatMap(Decoder[Char, HangeulJamo.Medial].decode(_).toOption),
          decomposition.safeCharAt(2).flatMap(Decoder[Char, HangeulJamo.Final].decode(_).toOption)
        ) match {
          case (Some(initial), Some(medial), None) =>
            Right(HangeulSyllable.TwoJamo(initial, medial))
          case (Some(initial), Some(medial), Some(_final)) =>
            Right(HangeulSyllable.ThreeJamo(initial, medial, _final))
          case _ =>
            Left(DecodingFailure.FailedToDecodeHangeulSyllable(encoded))
        }
      }

      override def encode(decoded: HangeulSyllable): Char =
        Normalizer.normalize(decoded.charSequence, Normalizer.Form.NFC).charAt(0)
    }

}
