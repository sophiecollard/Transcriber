package hangeul4s.transliteration.hangeul

import cats.Monoid
import cats.instances.vector._
import hangeul4s.error.TransliterationFailure
import hangeul4s.model.hangeul.HangeulJamo.{Final, Initial}
import hangeul4s.model.hangeul.HangeulSyllabicBlock
import hangeul4s.model.hangeul.HangeulSyllabicBlock.{ThreeLetter, TwoLetter}
import hangeul4s.model.romanization.RomanLetter
import hangeul4s.model.romanization.RomanLetter._
import hangeul4s.transliteration.hangeul.HangeulJamoRomanizer._

private [transliteration] object HangeulSyllabicBlockRomanizer {

  def transliterateSyllabicBlock(
    maybePrecedingFinal: Option[Final],
    block: HangeulSyllabicBlock,
    maybeFollowingInitial: Option[Initial]
  ): Either[TransliterationFailure, Vector[RomanLetter]] =
    block match {
      case TwoLetter(initial, medial) =>
        Right(
          Monoid.combine(
            transliterateInitialInContext(maybePrecedingFinal, initial),
            transliterateMedial(medial)
          )
        )
      case ThreeLetter(initial, medial, final_) =>
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
      case (Final.ㄱ, Some(Initial.ㅇ)) =>
        Vector(G)
      case (Final.ㄱ, Some(Initial.ㄴ) | Some(Initial.ㄹ) | Some(Initial.ㅁ)) =>
        Vector(N, G)
      case (Final.ㄷ, Some(Initial.ㅇ)) =>
        Vector(D) // Also sometimes J
      case (Final.ㄷ, Some(Initial.ㄴ) | Some(Initial.ㄹ) | Some(Initial.ㅁ)) =>
        Vector(N)
      case (Final.ㄹ, Some(Initial.ㅇ)) =>
        Vector(R)
      case (Final.ㅂ, Some(Initial.ㅇ)) =>
        Vector(B)
      case (Final.ㅂ, Some(Initial.ㄴ) | Some(Initial.ㄹ) | Some(Initial.ㅁ)) =>
        Vector(M)
      case (Final.ㅅ, Some(Initial.ㅇ)) =>
        Vector(S)
      case (Final.ㅅ, Some(Initial.ㄴ) | Some(Initial.ㄹ) | Some(Initial.ㅁ)) =>
        Vector(N)
      case _ =>
        transliterateFinal(_final)
    }

  private def transliterateInitialInContext(
    maybePrecedingFinal: Option[Final],
    initial: Initial
  ): Vector[RomanLetter] =
    (maybePrecedingFinal, initial) match {
      case (Some(Final.ㄱ) | Some(Final.ㄷ) | Some(Final.ㅂ), Initial.ㄹ) =>
        Vector(N)
      case (Some(Final.ㄹ), Initial.ㄴ) =>
        Vector(L) // Also sometimes NN instead of LL
      case (Some(Final.ㄹ), Initial.ㄹ) =>
        Vector(L)
      case (Some(Final.ㅅ) | Some(Final.ㅇ), Initial.ㄹ) =>
        Vector(N)
      case (Some(Final.ㅂ), Initial.ㅁ) =>
        Vector(M)
      case _ =>
        transliterateInitial(initial)
    }

}
