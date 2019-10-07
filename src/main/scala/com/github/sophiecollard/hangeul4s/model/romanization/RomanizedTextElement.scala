package com.github.sophiecollard.hangeul4s.model.romanization

import com.github.sophiecollard.hangeul4s.model.hangeul.HangeulTextElement
import com.github.sophiecollard.hangeul4s.parsing.{Unparser, Untokenizer}

sealed trait RomanizedTextElement

object RomanizedTextElement {

  // TODO make non-empty vector
  final case class Captured(letters: Vector[RomanLetter]) extends RomanizedTextElement

  object Captured {
    def fromLetters(l: RomanLetter, ls: RomanLetter*): Captured =
      Captured(l +: ls.toVector)
  }

  sealed abstract case class NotCaptured(contents: String) extends RomanizedTextElement

  object NotCaptured {
    private [romanization] def unvalidatedFrom(input: String): NotCaptured =
      new NotCaptured(input) {}

    def fromHangeul(hangeul: HangeulTextElement.NotCaptured): NotCaptured =
      new NotCaptured(hangeul.contents) {}
  }

  implicit val unparser: Unparser[RomanizedTextElement, String] =
    Unparser.instance {
      case Captured(letters)     => letters.map(_.char).mkString
      case NotCaptured(contents) => contents
    }

  implicit val vectorUntokenizer: Untokenizer[Vector] =
    Untokenizer.instance(_.mkString)

}
