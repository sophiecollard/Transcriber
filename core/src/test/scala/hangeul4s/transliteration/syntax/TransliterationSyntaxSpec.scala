package hangeul4s.transliteration.syntax

import cats.instances.either._
import cats.instances.vector._
import cats.syntax.traverse._
import hangeul4s.error.TransliterationFailure
import hangeul4s.model.hangeul.HangeulJamo
import hangeul4s.model.hangeul.HangeulJamo.Medial
import hangeul4s.model.romanization.RomanLetter
import hangeul4s.transliteration.Transliterator
import org.specs2.mutable.Specification

class TransliterationSyntaxSpec extends Specification {

  private implicit val transliterator: Transliterator[RomanLetter, HangeulJamo] =
    Transliterator.instance {
      case RomanLetter.A => Right(Medial.ㅏ)
      case RomanLetter.E => Right(Medial.ㅔ)
      // not a sensible failure to return but this is irrelevant here
      case _ => Left(TransliterationFailure.EmptyResult)
    }

  "TransliterationSyntax#TransliterationOps[A]" should {

    "provide a 'transliterateTo' method on A instances" in {
      RomanLetter.A.transliterateTo[HangeulJamo] must beRight[HangeulJamo](Medial.ㅏ)
    }

  }

  private implicit val transliteratorF: Transliterator[List[RomanLetter], Vector[HangeulJamo]] =
    Transliterator.instance { letters =>
      letters.map {
        case RomanLetter.A => Right(Medial.ㅏ)
        case RomanLetter.E => Right(Medial.ㅔ)
        // not a sensible failure to return but this is irrelevent here
        case _ => Left(TransliterationFailure.EmptyResult)
      }.toVector.sequence
    }

  "TransliterationSyntax#TransliterationFOps[F[_], A]" should {

    "provide a 'transliterateToF' method on F[A] instances" in {
      List(RomanLetter.A, RomanLetter.E).transliterateToF[Vector, HangeulJamo] must
        beRight(Vector[HangeulJamo](Medial.ㅏ, Medial.ㅔ))
    }

  }

}
