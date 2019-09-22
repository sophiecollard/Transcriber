package com.github.sophiecollard.hangeul4s.error

import com.github.sophiecollard.hangeul4s.model.UnicodeBlock

sealed abstract class ValidationError(val message: String)

object ValidationError {

  final case class CharOutsideUnicodeBlockRange(char: Char, unicodeBlock: UnicodeBlock)
    extends ValidationError(
      s"'$char' is outside Unicode block ${unicodeBlock.name} with range [${unicodeBlock.firstCodePoint.toHexString}-${unicodeBlock.lastCodePoint.toHexString}]"
    )

}
