package com.github.sophiecollard.transliterator.transliteration

import com.github.sophiecollard.transliterator.error.TransliterationError
import com.github.sophiecollard.transliterator.instances.either._
import com.github.sophiecollard.transliterator.instances.vector._
import com.github.sophiecollard.transliterator.model.hangeul.{HangeulText, HangeulTextElement}
import com.github.sophiecollard.transliterator.model.romanization.{RomanLetter, RomanizedText, RomanizedTextElement}
import com.github.sophiecollard.transliterator.syntax.either.EitherConstructors
import com.github.sophiecollard.transliterator.syntax.traverse._
import com.github.sophiecollard.transliterator.syntax.vector._
import com.github.sophiecollard.transliterator.transliteration.HangeulSyllabicBlockRomanizer._

object HangeulRomanizer extends Transliterator[HangeulText, RomanizedText] {

  override def transliterate(text: HangeulText): Either[TransliterationError, RomanizedText] =
    text.elements
      .map {
        case HangeulTextElement.Word(blocks) =>
          blocks.toVector
            .zipWithNeighbors
            .map { case (maybePrevBlock, block, maybeNextBlock) =>
              val maybePrecedingFinal = maybePrevBlock.flatMap(_.maybeFinal)
              val maybeFollowingInitial = maybeNextBlock.map(_.initial)
              transliterateSyllabicBlock(maybePrecedingFinal, block, maybeFollowingInitial)
            }
            .traverse[TransliterationResult, Vector[RomanLetter]](identity)
            .map(_.flatten)
            .map(RomanizedTextElement.Word) // TODO use non-empty vector
        case punctuation: HangeulTextElement.Punctuation =>
          RomanizedTextElement
            .Punctuation.fromHangeul(punctuation)
            .right[TransliterationError, RomanizedTextElement]
        case digits: HangeulTextElement.Digits =>
          RomanizedTextElement
            .Digits.fromHangeul(digits)
            .right[TransliterationError, RomanizedTextElement]
      }
      .traverse[TransliterationResult, RomanizedTextElement](identity(_))
      .map(RomanizedText.apply(_))

}
