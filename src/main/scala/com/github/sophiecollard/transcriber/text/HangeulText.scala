package com.github.sophiecollard.transcriber.text

import com.github.sophiecollard.transcriber.charset.HangeulSyllabicBlock

final case class HangeulText(chars: Vector[HangeulSyllabicBlock]) extends Text[HangeulSyllabicBlock]

object HangeulText {

  implicit val parser: Parser[HangeulText] =
    Parser.instance(_ => throw new RuntimeException("not implemented"))

}
