package hangeul4s.transliteration.hangeul

import cats.data.NonEmptyVector
import cats.instances.either._
import cats.instances.vector._
import cats.syntax.either._
import cats.syntax.traverse._
import hangeul4s.error.TransliterationFailure
import hangeul4s.model.hangeul.HangeulTextElement
import hangeul4s.model.romanization.RomanizedTextElement
import hangeul4s.syntax.vector._
import hangeul4s.transliteration.{TransliterationResult, Transliterator}

object RevisedRomanization extends  {

  implicit val transliterator: Transliterator[HangeulTextElement, RomanizedTextElement] =
    new Transliterator[HangeulTextElement, RomanizedTextElement] {
      override def transliterate(input: HangeulTextElement): TransliterationResult[RomanizedTextElement] =
        input match {
          case HangeulTextElement.Captured(syllables) =>
            syllables.toVector
              .zipWithNeighbors
              .map { case (maybePrevSyllable, syllable, maybeNextSyllable) =>
                val maybePrecedingFinal = maybePrevSyllable.flatMap(_.maybeFinal)
                val maybeFollowingInitial = maybeNextSyllable.map(_.initial)
                HangeulSyllableRomanizer.transliterateSyllable(maybePrecedingFinal, syllable, maybeFollowingInitial)
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

}
