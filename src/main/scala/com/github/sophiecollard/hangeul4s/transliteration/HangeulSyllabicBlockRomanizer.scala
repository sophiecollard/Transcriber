package com.github.sophiecollard.hangeul4s.transliteration

import com.github.sophiecollard.hangeul4s.error.TransliterationError
import com.github.sophiecollard.hangeul4s.instances.vector._
import com.github.sophiecollard.hangeul4s.model.hangeul.{HangeulJamo, HangeulSyllabicBlock}
import com.github.sophiecollard.hangeul4s.model.hangeul.HangeulSyllabicBlock.{ThreeLetter, TwoLetter}
import com.github.sophiecollard.hangeul4s.model.romanization.RomanLetter
import com.github.sophiecollard.hangeul4s.model.romanization.RomanLetter._
import com.github.sophiecollard.hangeul4s.transliteration.HangeulJamoRomanizer._
import com.github.sophiecollard.hangeul4s.util.typeclasses.Monoid

private [transliteration] object HangeulSyllabicBlockRomanizer {

  def transliterateSyllabicBlock(
    maybePrecedingFinal: Option[HangeulJamo.Final],
    block: HangeulSyllabicBlock,
    maybeFollowingInitial: Option[HangeulJamo.Initial]
  ): Either[TransliterationError, Vector[RomanLetter]] =
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
            transliterateInitialInContext(maybePrecedingFinal, initial),
            transliterateMedial(medial),
            transliterateFinalInContext(final_, maybeFollowingInitial)
          )
        )
    }

  private def transliterateFinalInContext(
    `final`: HangeulJamo.Final,
    maybeFollowingInitial: Option[HangeulJamo.Initial]
  ): Vector[RomanLetter] =
    (`final`, maybeFollowingInitial) match {
      case (HangeulJamo.Final.ㄱ, Some(HangeulJamo.Initial.ㅇ)) =>
        Vector(G)
      case (HangeulJamo.Final.ㄱ, Some(HangeulJamo.Initial.ㄴ) | Some(HangeulJamo.Initial.ㄹ) | Some(HangeulJamo.Initial.ㅁ)) =>
        Vector(N, G)
      case (HangeulJamo.Final.ㄷ, Some(HangeulJamo.Initial.ㅇ)) =>
        Vector(D) // Also sometimes J
      case (HangeulJamo.Final.ㄷ, Some(HangeulJamo.Initial.ㄴ) | Some(HangeulJamo.Initial.ㄹ) | Some(HangeulJamo.Initial.ㅁ)) =>
        Vector(N)
      case (HangeulJamo.Final.ㄹ, Some(HangeulJamo.Initial.ㅇ)) =>
        Vector(R)
      case (HangeulJamo.Final.ㅂ, Some(HangeulJamo.Initial.ㅇ)) =>
        Vector(B)
      case (HangeulJamo.Final.ㅂ, Some(HangeulJamo.Initial.ㄴ) | Some(HangeulJamo.Initial.ㄹ) | Some(HangeulJamo.Initial.ㅁ)) =>
        Vector(M)
      case (HangeulJamo.Final.ㅅ, Some(HangeulJamo.Initial.ㅇ)) =>
        Vector(S)
      case (HangeulJamo.Final.ㅅ, Some(HangeulJamo.Initial.ㄴ) | Some(HangeulJamo.Initial.ㄹ) | Some(HangeulJamo.Initial.ㅁ)) =>
        Vector(N)
      case _ =>
        transliterateFinal(`final`)
    }

  private def transliterateInitialInContext(
    maybePrecedingFinal: Option[HangeulJamo.Final],
    initial: HangeulJamo.Initial
  ): Vector[RomanLetter] =
    (maybePrecedingFinal, initial) match {
      case (Some(HangeulJamo.Final.ㄱ) | Some(HangeulJamo.Final.ㄷ) | Some(HangeulJamo.Final.ㅂ), HangeulJamo.Initial.ㄹ) =>
        Vector(N)
      case (Some(HangeulJamo.Final.ㄹ), HangeulJamo.Initial.ㄴ) =>
        Vector(L) // Also sometimes NN instead of LL
      case (Some(HangeulJamo.Final.ㄹ), HangeulJamo.Initial.ㄹ) =>
        Vector(L)
      case (Some(HangeulJamo.Final.ㅅ) | Some(HangeulJamo.Final.ㅇ), HangeulJamo.Initial.ㄹ) =>
        Vector(N)
      case (Some(HangeulJamo.Final.ㅂ), HangeulJamo.Initial.ㅁ) =>
        Vector(M)
      case _ =>
        transliterateInitial(initial)
    }

}
