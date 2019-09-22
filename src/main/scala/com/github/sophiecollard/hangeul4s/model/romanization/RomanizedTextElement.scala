package com.github.sophiecollard.hangeul4s.model.romanization

import com.github.sophiecollard.hangeul4s.model.UnicodeBlock
import com.github.sophiecollard.hangeul4s.model.hangeul.HangeulTextElement
import com.github.sophiecollard.hangeul4s.validation.SequentialValidationResult

sealed trait RomanizedTextElement

object RomanizedTextElement {

  // TODO make non-empty vector
  final case class Word(letters: Vector[RomanLetter]) extends RomanizedTextElement

  object Word {
    def fromLetters(l: RomanLetter, ls: RomanLetter*): Word =
      Word(l +: ls.toVector)
  }

  sealed abstract case class Punctuation(contents: String) extends RomanizedTextElement

  object Punctuation {
    def from(input: String): SequentialValidationResult[Punctuation] =
      UnicodeBlock
        .validateString(input, UnicodeBlock.ASCIIPunctuation)
        .toEither
        .map(new Punctuation(_) {})

    def fromHangeul(hangeul: HangeulTextElement.Punctuation): Punctuation =
      new Punctuation(hangeul.contents) {}
  }

  sealed abstract case class Digits(contents: String) extends RomanizedTextElement

  object Digits {
    def from(input: String): SequentialValidationResult[Digits] =
      UnicodeBlock
        .validateString(input, UnicodeBlock.ASCIIDigits)
        .toEither
        .map(new Digits(_) {})

    def fromHangeul(hangeul: HangeulTextElement.Digits): Digits =
      new Digits(hangeul.contents) {}
  }

}
