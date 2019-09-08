package com.github.sophiecollard.transcriber.text

import com.github.sophiecollard.transcriber.charset.RomanLetter
import com.github.sophiecollard.transcriber.error.ParsingError

final case class RomanizedText(chars: Vector[RomanLetter]) extends Text[RomanLetter]

object RomanizedText {

  def parse(string: String): Either[ParsingError, RomanizedText] =
    throw new RuntimeException("not implemented")

}
