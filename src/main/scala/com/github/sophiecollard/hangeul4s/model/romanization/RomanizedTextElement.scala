package com.github.sophiecollard.hangeul4s.model.romanization

import com.github.sophiecollard.hangeul4s.model.hangeul.HangeulTextElement

sealed trait RomanizedTextElement {

  import RomanizedTextElement._

  override def toString: String = this match {
    case Captured(letters)     => letters.map(_.char).mkString
    case NotCaptured(contents) => contents
  }

}

object RomanizedTextElement {

  // TODO make non-empty vector
  final case class Captured(letters: Vector[RomanLetter]) extends RomanizedTextElement

  object Captured {
    def fromLetters(l: RomanLetter, ls: RomanLetter*): Captured =
      Captured(l +: ls.toVector)
  }

  sealed abstract case class NotCaptured(contents: String) extends RomanizedTextElement

  object NotCaptured {
    def fromHangeul(hangeul: HangeulTextElement.NotCaptured): NotCaptured =
      new NotCaptured(hangeul.contents) {}
  }

}
