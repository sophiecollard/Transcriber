package com.github.sophiecollard.transliterator.transliteration

import com.github.sophiecollard.transliterator.error.TransliterationError
import com.github.sophiecollard.transliterator.instances._
import com.github.sophiecollard.transliterator.model._
import com.github.sophiecollard.transliterator.model.HangeulSyllabicBlock._
import com.github.sophiecollard.transliterator.model.RomanLetter._
import com.github.sophiecollard.transliterator.syntax.RichVector
import com.github.sophiecollard.transliterator.util.Monoid

object HangeulRomanizer extends Transliterator[HangeulText, RomanizedText] {

  import HangeulLetterRomanizer._

  override def transliterate(text: HangeulText): Either[TransliterationError, RomanizedText] =
    Right(
      RomanizedText(
        text.words.map { w =>
          RomanizedWord(
            w.blocks.zipWithNeighbors.flatMap { case (maybePrevBlock, block, maybeNextBlock) =>
              val maybePrecedingFinal = maybePrevBlock.flatMap(_.getFinal)
              val maybeFollowingInitial = maybeNextBlock.map(_.getInitial)
              transliterateBlock(maybePrecedingFinal, block, maybeFollowingInitial)
            }
          )
        }
      )
    )

  private def transliterateBlock(
    maybePrecedingFinal: Option[HangeulLetter.Consonant],
    block: HangeulSyllabicBlock,
    maybeFollowingInitial: Option[HangeulLetter.Consonant]
  ): Vector[RomanLetter] =
    block match {
      case IM(initial, medial) =>
        Monoid.combine(
          transliterateInitialInContext(maybePrecedingFinal, initial),
          transliterateMedial(medial)
        )
      case IMF(initial, medial, final_) =>
        Monoid.combineAll(
          transliterateInitialInContext(maybePrecedingFinal, initial),
          transliterateMedial(medial),
          transliterateFinalInContext(final_, maybeFollowingInitial)
        )
      case _ =>
        throw new RuntimeException("not implemented")
    }

  private def transliterateInitialInContext(
    maybePrecedingFinal: Option[HangeulLetter.Consonant],
    initial: HangeulLetter.Consonant
  ): Vector[RomanLetter] =
    (maybePrecedingFinal, initial) match {
      case (Some(HangeulLetter.ㄹ), HangeulLetter.ㄹ) =>
        Vector(L)
      case _ =>
        transliterateInitial(initial)
    }

  private def transliterateFinalInContext(
    `final`: HangeulLetter.Consonant,
    maybeFollowingInitial: Option[HangeulLetter.Consonant]
  ): Vector[RomanLetter] =
    (`final`, maybeFollowingInitial) match {
      case (HangeulLetter.ㄹ, Some(HangeulLetter.ㅇ)) =>
        Vector(R)
      case _ =>
        transliterateFinal(`final`)
    }

}
