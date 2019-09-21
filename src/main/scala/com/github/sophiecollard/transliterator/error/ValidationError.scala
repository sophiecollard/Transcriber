package com.github.sophiecollard.transliterator.error

import com.github.sophiecollard.transliterator.model.UnicodeBlock

sealed abstract class ValidationError(val message: String)

object ValidationError {

  final case class CharOutsideUnicodeBlockRange(char: Char, unicodeBlock: UnicodeBlock)
    extends ValidationError(
      s"'$char' is outside Unicode block ${unicodeBlock.name} with range [${unicodeBlock.firstCodePoint.toHexString}-${unicodeBlock.lastCodePoint.toHexString}]"
    )

}
