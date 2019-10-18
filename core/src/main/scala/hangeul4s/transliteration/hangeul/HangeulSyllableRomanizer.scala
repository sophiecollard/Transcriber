package hangeul4s.transliteration.hangeul

import cats.Monoid
import cats.instances.vector._
import hangeul4s.error.TransliterationFailure
import hangeul4s.model.hangeul.HangeulJamo.{Final, Initial}
import hangeul4s.model.hangeul.HangeulSyllable
import hangeul4s.model.hangeul.HangeulSyllable.{ThreeJamo, TwoJamo}
import hangeul4s.model.romanization.RomanLetter
import hangeul4s.model.romanization.RomanLetter._
import hangeul4s.transliteration.hangeul.HangeulJamoRomanizer._

private [transliteration] object HangeulSyllableRomanizer {

  def transliterateSyllable(
    maybePrecedingFinal: Option[Final],
    syllable: HangeulSyllable,
    maybeFollowingInitial: Option[Initial]
  ): Either[TransliterationFailure, Vector[RomanLetter]] =
    syllable match {
      case TwoJamo(initial, medial) =>
        Right(
          Monoid.combine(
            transliterateInitialInContext(maybePrecedingFinal, initial),
            transliterateMedial(medial)
          )
        )
      case ThreeJamo(initial, medial, final_) =>
        Right(
          Monoid.combineAll(
            List(
              transliterateInitialInContext(maybePrecedingFinal, initial),
              transliterateMedial(medial),
              transliterateFinalInContext(final_, maybeFollowingInitial)
            )
          )
        )
    }

  private def transliterateFinalInContext(
    _final: Final,
    maybeFollowingInitial: Option[Initial]
  ): Vector[RomanLetter] =
    (_final, maybeFollowingInitial) match {
      case (Final.ㄱ, Some(Initial.ㄴ) | Some(Initial.ㄹ) | Some(Initial.ㅁ)) =>
        Vector(N, G)
      case (Final.ㄱ, Some(Initial.ㅇ)) =>
        Vector(G)
      case (Final.ㄴ, Some(Initial.ㄹ)) =>
        Vector(L)
      case (Final.ㄷ, Some(Initial.ㄴ) | Some(Initial.ㄹ) | Some(Initial.ㅁ)) =>
        Vector(N)
      case (Final.ㄷ, Some(Initial.ㅇ)) =>
        Vector(D) // Also sometimes J
      case (Final.ㄹ, Some(Initial.ㅇ)) =>
        Vector(R)
      case (Final.ㄺ, Some(Initial.ㅇ)) =>
        Vector(L, G)
      case (Final.ㄻ, Some(Initial.ㅇ)) =>
        Vector(L, M)
      case (Final.ㄼ, Some(Initial.ㄷ)) =>
        Vector(L)
      case (Final.ㄼ, Some(Initial.ㅇ)) =>
        Vector(L, B)
      case (Final.ㄿ, Some(Initial.ㅇ)) =>
        Vector(L, P)
      case (Final.ㅀ, Some(Initial.ㅇ)) =>
        Vector(R)
      case (Final.ㅂ, Some(Initial.ㄴ) | Some(Initial.ㄹ) | Some(Initial.ㅁ)) =>
        Vector(M)
      case (Final.ㅂ, Some(Initial.ㅇ)) =>
        Vector(B)
      case (Final.ㅅ, Some(Initial.ㄴ) | Some(Initial.ㄹ) | Some(Initial.ㅁ)) =>
        Vector(N)
      case (Final.ㅅ, Some(Initial.ㅇ)) =>
        Vector(S)
      case (Final.ㅆ, Some(Initial.ㅇ)) =>
        Vector(S, S)
      case (Final.ㅈ, Some(Initial.ㄴ) | Some(Initial.ㄹ) | Some(Initial.ㅁ)) =>
        Vector(N)
      case (Final.ㅈ, Some(Initial.ㅇ)) =>
        Vector(J)
      case (Final.ㅊ, Some(Initial.ㄴ) | Some(Initial.ㄹ) | Some(Initial.ㅁ)) =>
        Vector(N)
      case (Final.ㅊ, Some(Initial.ㅇ)) =>
        Vector(C, H)
      case (Final.ㅌ, Some(Initial.ㄴ) | Some(Initial.ㄹ) | Some(Initial.ㅁ)) =>
        Vector(N)
      case (Final.ㅎ, Some(Initial.ㄱ) | Some(Initial.ㅇ) | Some(Initial.ㅈ)) =>
        Vector.empty
      case (Final.ㅎ, Some(Initial.ㄴ) | Some(Initial.ㄹ) | Some(Initial.ㅁ)) =>
        Vector(N)
      case _ =>
        transliterateFinal(_final)
    }

  private def transliterateInitialInContext(
    maybePrecedingFinal: Option[Final],
    initial: Initial
  ): Vector[RomanLetter] =
    (maybePrecedingFinal, initial) match {
      case (Some(Final.ㅎ), Initial.ㄱ) =>
        Vector(K)
      case (Some(Final.ㄹ), Initial.ㄴ) =>
        Vector(L) // Also sometimes NN instead of LL
      case (Some(Final.ㄶ) | Some(Final.ㅀ), Initial.ㄷ) =>
        Vector(T)
      case (Some(Final.ㅎ), Initial.ㄷ) =>
        Vector.empty
      case (Some(Final.ㄱ) | Some(Final.ㄷ) | Some(Final.ㅁ) | Some(Final.ㅂ) | Some(Final.ㅅ) | Some(Final.ㅇ) |
            Some(Final.ㅈ) | Some(Final.ㅊ) | Some(Final.ㅌ) | Some(Final.ㅎ), Initial.ㄹ) =>
        Vector(N)
      case (Some(Final.ㄴ) | Some(Final.ㄹ), Initial.ㄹ) =>
        Vector(L)
      case (Some(Final.ㅂ), Initial.ㅁ) =>
        Vector(M)
      case (Some(Final.ㅎ), Initial.ㅈ) =>
        Vector(C, H)
      case (Some(Final.ㄲ), Initial.ㅇ) =>
        Vector(K)
      case (Some(Final.ㄳ), Initial.ㅇ) =>
        Vector(S)
      case (Some(Final.ㄵ), Initial.ㅇ) =>
        Vector(J)
      case (Some(Final.ㄾ), Initial.ㅇ) =>
        Vector(T)
      case (Some(Final.ㅄ), Initial.ㅇ) =>
        Vector(S)
      case (Some(Final.ㅎ), Initial.ㅎ) =>
        Vector.empty
      case _ =>
        transliterateInitial(initial)
    }

}
