package com.github.sophiecollard.transcriber.text

import com.github.sophiecollard.transcriber.charset.RomanLetter

final case class RomanizedText(chars: Vector[RomanLetter]) extends Text[RomanLetter]

object RomanizedText {

  implicit val parser: Parser[RomanizedText] =
    Parser.instance(_ => throw new RuntimeException("not implemented"))

}
