package com.github.sophiecollard.hangeul4s.model.hangeul

import java.text.Normalizer

import com.github.sophiecollard.hangeul4s.encoding.{Codec, Encoder}
import com.github.sophiecollard.hangeul4s.error.DecodingError
import com.github.sophiecollard.hangeul4s.syntax.string.StringOps

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
    Encoder[Char, HangeulSyllabicBlock]
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
    `final`: HangeulJamo.Final
  ) extends HangeulSyllabicBlock

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
      override def encode(decoded: HangeulSyllabicBlock): Char = {
        val composition = Normalizer.normalize(decoded.charSequence, Normalizer.Form.NFC)

        composition.charAt(0)
      }

      override def decode(encoded: Char): Either[DecodingError, HangeulSyllabicBlock] = {
        val decomposition: String = Normalizer.normalize(encoded.toString, Normalizer.Form.NFD)

        (
          decomposition.safeCharAt(0).flatMap(HangeulJamo.Initial.fromChar),
          decomposition.safeCharAt(1).flatMap(HangeulJamo.Medial.fromChar),
          decomposition.safeCharAt(2).flatMap(HangeulJamo.Final.fromChar)
        ) match {
          case (Some(initial), Some(medial), None) =>
            Right(HangeulSyllabicBlock.TwoLetter(initial, medial))
          case (Some(initial), Some(medial), Some(final_)) =>
            Right(HangeulSyllabicBlock.ThreeLetter(initial, medial, final_))
          case _ =>
            Left(DecodingError.FailedToDecodeHangeulSyllabicBlock(encoded))
        }
      }
    }

}
