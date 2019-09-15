package com.github.sophiecollard.transliterator.transliteration

import com.github.sophiecollard.transliterator.error.TransliterationError
import com.github.sophiecollard.transliterator.instances._
import com.github.sophiecollard.transliterator.model._
import com.github.sophiecollard.transliterator.model.HangeulSyllabicBlock._
import com.github.sophiecollard.transliterator.model.RomanLetter._
import com.github.sophiecollard.transliterator.syntax._
import com.github.sophiecollard.transliterator.util.Monoid

object HangeulRomanizer extends Transliterator[HangeulText, RomanizedText] {

  import HangeulLetterRomanizer._

  override def transliterate(text: HangeulText): Either[TransliterationError, RomanizedText] =
    text.words
      .map { word =>
        word.blocks
          .zipWithNeighbors
          .map { case (maybePrevBlock, block, maybeNextBlock) =>
            val maybePrecedingFinal = maybePrevBlock.flatMap(_.getFinal)
            val maybeFollowingInitial = maybeNextBlock.map(_.getInitial)
            transliterateBlock(maybePrecedingFinal, block, maybeFollowingInitial)
          }
          .traverse[Either[TransliterationError, ?], Vector[RomanLetter]](identity)
          .map(_.flatten)
          .map(RomanizedWord)
      }
      .traverse[Either[TransliterationError, ?], RomanizedWord](identity)
      .map(RomanizedText.apply _)

  private def transliterateBlock(
    maybePrecedingFinal: Option[HangeulLetter.Consonant],
    block: HangeulSyllabicBlock,
    maybeFollowingInitial: Option[HangeulLetter.Consonant]
  ): Either[TransliterationError, Vector[RomanLetter]] =
    block match {
      case IM(initial, medial) =>
        Right(
          Monoid.combine(
            transliterateInitialInContext(maybePrecedingFinal, initial),
            transliterateMedial(medial)
          )
        )
      case IMM(initial, medial, secondMedial) =>
        Right(
          Monoid.combineAll(
            transliterateInitialInContext(maybePrecedingFinal, initial),
            transliterateMedial(medial),
            transliterateMedial(secondMedial)
          )
        )
      case IMF(initial, medial, final_) =>
        Right(
          Monoid.combineAll(
            transliterateInitialInContext(maybePrecedingFinal, initial),
            transliterateMedial(medial),
            transliterateFinalInContext(final_, maybeFollowingInitial)
          )
        )
      case IMMF(initial, medial, secondMedial, final_) =>
        Right(
          Monoid.combineAll(
            transliterateInitialInContext(maybePrecedingFinal, initial),
            transliterateMedial(medial),
            transliterateMedial(secondMedial),
            transliterateFinalInContext(final_, maybeFollowingInitial)
          )
        )
      case _ =>
        Left(TransliterationError.NotImplemented("support for syllabic blocks with two final consonants"))
    }

  private def transliterateFinalInContext(
    `final`: HangeulLetter.Consonant,
    maybeFollowingInitial: Option[HangeulLetter.Consonant]
  ): Vector[RomanLetter] =
    (`final`, maybeFollowingInitial) match {
      case (HangeulLetter.ㄱ, Some(HangeulLetter.ㅇ)) =>
        Vector(G)
      case (HangeulLetter.ㄱ, Some(HangeulLetter.ㄴ) | Some(HangeulLetter.ㄹ) | Some(HangeulLetter.ㅁ)) =>
        Vector(N, G)
      case (HangeulLetter.ㄷ, Some(HangeulLetter.ㅇ)) =>
        Vector(D) // This can also sometimes be J
      case (HangeulLetter.ㄷ, Some(HangeulLetter.ㄴ) | Some(HangeulLetter.ㄹ) | Some(HangeulLetter.ㅁ)) =>
        Vector(N)
      case (HangeulLetter.ㄹ, Some(HangeulLetter.ㅇ)) =>
        Vector(R)
      case (HangeulLetter.ㅂ, Some(HangeulLetter.ㅇ)) =>
        Vector(B)
      case (HangeulLetter.ㅂ, Some(HangeulLetter.ㄴ) | Some(HangeulLetter.ㄹ) | Some(HangeulLetter.ㅁ)) =>
        Vector(M)
      case (HangeulLetter.ㅅ, Some(HangeulLetter.ㅇ)) =>
        Vector(S)
      case (HangeulLetter.ㅅ, Some(HangeulLetter.ㄴ) | Some(HangeulLetter.ㄹ) | Some(HangeulLetter.ㅁ)) =>
        Vector(N)
      case _ =>
        transliterateFinal(`final`)
    }

  private def transliterateInitialInContext(
    maybePrecedingFinal: Option[HangeulLetter.Consonant],
    initial: HangeulLetter.Consonant
  ): Vector[RomanLetter] =
    (maybePrecedingFinal, initial) match {
      case (Some(HangeulLetter.ㄱ) | Some(HangeulLetter.ㄷ) | Some(HangeulLetter.ㅂ), HangeulLetter.ㄹ) =>
        Vector(N)
      case (Some(HangeulLetter.ㄹ), HangeulLetter.ㄴ) =>
        Vector(L) // This could also sometimes be NN instead of LL
      case (Some(HangeulLetter.ㄹ), HangeulLetter.ㄹ) =>
        Vector(L)
      case (Some(HangeulLetter.ㅅ) | Some(HangeulLetter.ㅇ), HangeulLetter.ㄹ) =>
        Vector(N)
      case (Some(HangeulLetter.ㅂ), HangeulLetter.ㅁ) =>
        Vector(M)
      case _ =>
        transliterateInitial(initial)
    }

}
