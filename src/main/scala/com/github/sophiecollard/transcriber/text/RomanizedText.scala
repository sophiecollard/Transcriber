package com.github.sophiecollard.transcriber.text

import com.github.sophiecollard.transcriber.charset.RomanLetter
import com.github.sophiecollard.transcriber.util.Monoid

final case class RomanizedText(chars: Vector[RomanLetter]) extends Text[RomanLetter]

object RomanizedText {

  implicit val monoid: Monoid[RomanizedText] = new Monoid[RomanizedText] {
    override def empty: RomanizedText =
      RomanizedText(Vector.empty)

    override def combine(x: RomanizedText, y: RomanizedText): RomanizedText =
      RomanizedText(x.chars ++ y.chars)
  }

  implicit val parser: Parser[RomanizedText] =
    Parser.instance(_ => throw new RuntimeException("not implemented"))

}
