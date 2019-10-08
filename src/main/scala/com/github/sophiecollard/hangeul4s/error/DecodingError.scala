package com.github.sophiecollard.hangeul4s.error

sealed abstract class DecodingError(val message: String) extends Hangeul4sError

object DecodingError {

  final case object EmptyInput
    extends DecodingError("Failed to decode empty input")

  final case class FailedToDecodeHangeulSyllabicBlock(char: Char)
    extends DecodingError(s"Failed to decode HangeulSyllabicBlock from Char [$char] with decimal code point [${char.toInt}]")

}
