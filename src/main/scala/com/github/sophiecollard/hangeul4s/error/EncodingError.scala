package com.github.sophiecollard.hangeul4s.error

import com.github.sophiecollard.hangeul4s.model.hangeul.HangeulSyllabicBlock

sealed abstract class EncodingError(val message: String)

object EncodingError {

  final case class FailedToEncodeHangeulSyllabicBlock(block: HangeulSyllabicBlock)
    extends EncodingError(s"Failed to encode Hangeul syllabic block $block to Char")

}
