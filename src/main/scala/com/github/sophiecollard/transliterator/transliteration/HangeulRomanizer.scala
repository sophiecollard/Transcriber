package com.github.sophiecollard.transliterator.transliteration

import com.github.sophiecollard.transliterator.error.TransliterationError
import com.github.sophiecollard.transliterator.instances._
import com.github.sophiecollard.transliterator.model._
import com.github.sophiecollard.transliterator.model.HangeulSyllabicBlock._
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
              transliterateBlock(maybePrevBlock, block, maybeNextBlock)
            }
          )
        }
      )
    )

  private def transliterateBlock(
    maybePrevBlock: Option[HangeulSyllabicBlock],
    block: HangeulSyllabicBlock,
    maybeNextBlock: Option[HangeulSyllabicBlock]
  ): Vector[RomanLetter] =
    block match {
      case im @ IM(_, medial) =>
        Monoid.combine(
          transliterateInitialConsonantInContext(maybePrevBlock, im),
          transliterateVowel(medial)
        )
      case imf @ IMF(_, medial, _) =>
        Monoid.combineAll(
          transliterateInitialConsonantInContext(maybePrevBlock, imf),
          transliterateVowel(medial),
          transliterateFinalConsonantInContext(imf, maybeNextBlock)
        )
      case _ =>
        throw new RuntimeException("not implemented")
    }

  private def transliterateInitialConsonantInContext(
    maybePrevBlock: Option[HangeulSyllabicBlock],
    block: IM
  ): Vector[RomanLetter] =
    (maybePrevBlock, block) match {
      case (
        Some(IMF(_, _, HangeulLetter.ㄹ)),
        IM(HangeulLetter.ㄹ, _)
        ) =>
        transliterateFinalConsonant(HangeulLetter.ㄹ)
      case _ =>
        transliterateInitialConsonant(block.initial)
    }

  private def transliterateInitialConsonantInContext(
    maybePrevBlock: Option[HangeulSyllabicBlock],
    block: IMF
  ): Vector[RomanLetter] =
    (maybePrevBlock, block) match {
      case (
        Some(IMF(_, _, HangeulLetter.ㄹ)),
        IMF(HangeulLetter.ㄹ, _, _)
        ) =>
        transliterateFinalConsonant(HangeulLetter.ㄹ)
      case _ =>
        transliterateInitialConsonant(block.initial)
    }

  private def transliterateFinalConsonantInContext(
    block: IMF,
    maybeNextBlock: Option[HangeulSyllabicBlock]
  ): Vector[RomanLetter] =
    (block, maybeNextBlock) match {
      case (
        IMF(_, _, HangeulLetter.ㄹ),
        Some(IM(HangeulLetter.ㅇ, _)) |
        Some(IMF(HangeulLetter.ㅇ, _, _))
        ) =>
        transliterateInitialConsonant(HangeulLetter.ㄹ)
      case _ =>
        transliterateFinalConsonant(block.`final`)
    }

}
