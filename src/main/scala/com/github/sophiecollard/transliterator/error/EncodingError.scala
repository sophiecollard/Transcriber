package com.github.sophiecollard.transliterator.error

import com.github.sophiecollard.transliterator.model.hangeul.HangeulSyllabicBlock

sealed abstract class EncodingError(message: String)

object EncodingError {

  final case class FailedToEncodeHangeulSyllabicBlock(block: HangeulSyllabicBlock)
    extends EncodingError(s"Failed to encode Hangeul syllabic block $block to Char")

}
