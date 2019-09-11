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
      case twoLetter: TwoLetter =>
        Monoid.combine(
          transliterateInitialConsonantInContext(maybePrevBlock, twoLetter),
          transliterateVowel(twoLetter.vowel)
        )
      case threeLetter: ThreeLetter =>
        Monoid.combineAll(
          transliterateInitialConsonantInContext(maybePrevBlock, threeLetter),
          transliterateVowel(threeLetter.vowel),
          transliterateFinalConsonantInContext(threeLetter, maybeNextBlock)
        )
    }

  private def transliterateInitialConsonantInContext(
    maybePrevBlock: Option[HangeulSyllabicBlock],
    block: TwoLetter
  ): Vector[RomanLetter] =
    (maybePrevBlock, block) match {
      case (
        Some(ThreeLetter(_, _, HangeulLetter.ㄹ)),
        TwoLetter(HangeulLetter.ㄹ, _)
        ) =>
        transliterateFinalConsonant(HangeulLetter.ㄹ)
      case _ =>
        transliterateInitialConsonant(block.consonant)
    }

  private def transliterateInitialConsonantInContext(
    maybePrevBlock: Option[HangeulSyllabicBlock],
    block: ThreeLetter
  ): Vector[RomanLetter] =
    (maybePrevBlock, block) match {
      case (
        Some(ThreeLetter(_, _, HangeulLetter.ㄹ)),
        ThreeLetter(HangeulLetter.ㄹ, _, _)
        ) =>
        transliterateFinalConsonant(HangeulLetter.ㄹ)
      case _ =>
        transliterateInitialConsonant(block.initialConsonant)
    }

  private def transliterateFinalConsonantInContext(
    block: ThreeLetter,
    maybeNextBlock: Option[HangeulSyllabicBlock]
  ): Vector[RomanLetter] =
    (block, maybeNextBlock) match {
      case (
        ThreeLetter(_, _, HangeulLetter.ㄹ),
        Some(TwoLetter(HangeulLetter.ㅇ, _)) |
        Some(ThreeLetter(HangeulLetter.ㅇ, _, _))
        ) =>
        transliterateInitialConsonant(HangeulLetter.ㄹ)
      case _ =>
        transliterateFinalConsonant(block.finalConsonant)
    }

}
