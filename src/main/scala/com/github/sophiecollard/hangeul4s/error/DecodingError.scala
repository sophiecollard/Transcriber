package com.github.sophiecollard.hangeul4s.error

sealed abstract class DecodingError(val message: String) extends Error

object DecodingError {

  final case class FailedToDecodeHangeulSyllabicBlock(char: Char)
    extends DecodingError(
      s"Failed to decode Hangeul syllabic block from Char '$char' with decimal code point ${char.toInt}"
    )

}
