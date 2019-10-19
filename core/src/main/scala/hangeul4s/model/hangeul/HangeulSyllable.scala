package hangeul4s.model.hangeul

import java.text.Normalizer

import cats.syntax.either._ // required for toOption method in Scala 2.11
import hangeul4s.encoding.{Decoder, Encoder}
import hangeul4s.error.DecodingFailure
import hangeul4s.syntax.string.StringOps

/**
  * Type representing a character from the Hangeul Syllables Unicode block
  */
sealed trait HangeulSyllable {

  def initial: HangeulJamo.Initial

  def medial: HangeulJamo.Medial

  final def maybeFinal: Option[HangeulJamo.Final] = this match {
    case HangeulSyllable.TwoJamo(_, _)      => None
    case HangeulSyllable.ThreeJamo(_, _, f) => Some(f)
  }

  final def toJamoString: String = this match {
    case HangeulSyllable.TwoJamo(i, m)      => s"${i.char}${m.char}"
    case HangeulSyllable.ThreeJamo(i, m, f) => s"${i.char}${m.char}${f.char}"
  }

  override def toString: String =
    s"hangeul4s.model.hangeul.HangeulSyllable(${HangeulSyllable.charEncoder.encode(this)})"

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

  implicit val charDecoder: Decoder[Char, HangeulSyllable] =
    Decoder.instance { char =>
      val decomposition = Normalizer.normalize(char.toString, Normalizer.Form.NFD)

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
          Left(DecodingFailure.FailedToDecodeHangeulSyllable(char))
      }
    }

  implicit val charEncoder: Encoder[HangeulSyllable, Char] =
    Encoder.instance { syllable =>
      Normalizer.normalize(syllable.toJamoString, Normalizer.Form.NFC).charAt(0)
    }

}
