package com.github.sophiecollard.transcriber.text

import com.github.sophiecollard.transcriber.charset.HangeulChar
import com.github.sophiecollard.transcriber.error.ParsingError

final case class DisaggregatedHangeulText(chars: List[HangeulChar]) extends Text[HangeulChar]

object DisaggregatedHangeulText {

  def parse(string: String): Either[ParsingError, DisaggregatedHangeulText] =
    throw new RuntimeException("not implemented")

}
