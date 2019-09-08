package transliterator4s.korean.transliteration.hangeul

import cats.data.NonEmptyVector
import cats.instances.either._
import cats.instances.vector._
import cats.syntax.either._
import cats.syntax.traverse._
import transliterator4s.error.TransliterationFailure
import transliterator4s.korean.model.hangeul.HangeulTextElement
import transliterator4s.korean.model.romanization.RomanizedTextElement
import transliterator4s.syntax.vector._
import transliterator4s.transliteration.{TransliterationResult, Transliterator}

object HangeulRomanizer extends Transliterator[HangeulTextElement, RomanizedTextElement] {

  override def transliterate(input: HangeulTextElement): TransliterationResult[RomanizedTextElement] =
    input match {
      case HangeulTextElement.Captured(blocks) =>
        blocks.toVector
          .zipWithNeighbors
          .map { case (maybePrevBlock, block, maybeNextBlock) =>
            val maybePrecedingFinal = maybePrevBlock.flatMap(_.maybeFinal)
            val maybeFollowingInitial = maybeNextBlock.map(_.initial)
            HangeulSyllabicBlockRomanizer.transliterateSyllabicBlock(maybePrecedingFinal, block, maybeFollowingInitial)
          }
          .sequence
          .map(_.flatten)
          .flatMap(NonEmptyVector.fromVector(_).toRight[TransliterationFailure](TransliterationFailure.EmptyResult))
          .map(RomanizedTextElement.Captured(_))
      case notCaptured: HangeulTextElement.NotCaptured =>
        RomanizedTextElement
          .NotCaptured.fromHangeul(notCaptured)
          .asRight[TransliterationFailure]
      }

}
