package com.github.sophiecollard.transcriber.text

import com.github.sophiecollard.transcriber.charset.HangeulChar
import com.github.sophiecollard.transcriber.error.ParsingError

final case class HangeulText(chars: List[HangeulChar]) extends Text[HangeulChar]

object HangeulText {

  def parse(string: String): Either[ParsingError, HangeulText] =
    throw new RuntimeException("not implemented")

}
