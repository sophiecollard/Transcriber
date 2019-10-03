package com.github.sophiecollard.hangeul4s.transliteration

import cats.instances.either._
import cats.instances.vector._
import cats.syntax.either._
import cats.syntax.traverse._
import com.github.sophiecollard.hangeul4s.error.TransliterationError
import com.github.sophiecollard.hangeul4s.model.hangeul.HangeulTextElement
import com.github.sophiecollard.hangeul4s.model.romanization.RomanizedTextElement
import com.github.sophiecollard.hangeul4s.syntax.vector._
import com.github.sophiecollard.hangeul4s.transliteration.HangeulSyllabicBlockRomanizer._

object HangeulRomanizer extends Transliterator[HangeulTextElement, RomanizedTextElement] {

  override def transliterate(input: HangeulTextElement): TransliterationResult[RomanizedTextElement] =
    input match {
      case HangeulTextElement.Captured(blocks) =>
        blocks.toVector
          .zipWithNeighbors
          .map { case (maybePrevBlock, block, maybeNextBlock) =>
            val maybePrecedingFinal = maybePrevBlock.flatMap(_.maybeFinal)
            val maybeFollowingInitial = maybeNextBlock.map(_.initial)
            transliterateSyllabicBlock(maybePrecedingFinal, block, maybeFollowingInitial)
          }
          .sequence
          .map(_.flatten)
          .map(RomanizedTextElement.Captured.apply) // TODO use non-empty vector
      case notCaptured: HangeulTextElement.NotCaptured =>
        RomanizedTextElement
          .NotCaptured.fromHangeul(notCaptured)
          .asRight[TransliterationError]
      }

}
