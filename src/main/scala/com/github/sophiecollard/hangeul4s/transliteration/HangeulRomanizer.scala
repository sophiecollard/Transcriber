package com.github.sophiecollard.hangeul4s.transliteration

import com.github.sophiecollard.hangeul4s.error.TransliterationError
import com.github.sophiecollard.hangeul4s.instances.either._
import com.github.sophiecollard.hangeul4s.instances.vector._
import com.github.sophiecollard.hangeul4s.model.hangeul.{HangeulText, HangeulTextElement}
import com.github.sophiecollard.hangeul4s.model.romanization.{RomanLetter, RomanizedText, RomanizedTextElement}
import com.github.sophiecollard.hangeul4s.syntax.either.EitherConstructors
import com.github.sophiecollard.hangeul4s.syntax.traverse._
import com.github.sophiecollard.hangeul4s.syntax.vector._
import com.github.sophiecollard.hangeul4s.transliteration.HangeulSyllabicBlockRomanizer._

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
