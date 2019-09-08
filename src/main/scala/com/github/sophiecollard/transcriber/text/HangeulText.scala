package com.github.sophiecollard.transcriber.text

import com.github.sophiecollard.transcriber.charset.HangeulSyllabicBlock
import com.github.sophiecollard.transcriber.error.ParsingError

final case class HangeulText(chars: Vector[HangeulSyllabicBlock]) extends Text[HangeulSyllabicBlock]

object HangeulText {

  def parse(string: String): Either[ParsingError, HangeulText] =
    throw new RuntimeException("not implemented")

}
