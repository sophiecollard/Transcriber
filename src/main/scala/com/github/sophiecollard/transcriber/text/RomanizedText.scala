package com.github.sophiecollard.transcriber.text

import com.github.sophiecollard.transcriber.charset.RomanChar
import com.github.sophiecollard.transcriber.error.ParsingError

final case class RomanizedText(chars: List[RomanChar]) extends Text[RomanChar]

object RomanizedText {

  def parse(string: String): Either[ParsingError, RomanizedText] =
    throw new RuntimeException("not implemented")

}
