package com.github.sophiecollard.transliterator.model

import com.github.sophiecollard.transliterator.error.ValidationError
import com.github.sophiecollard.transliterator.instances.vector.vectorTraverse
import com.github.sophiecollard.transliterator.syntax.traverse.TraverseOps
import com.github.sophiecollard.transliterator.util.types.ValidatedNev.{Invalid, Valid}
import com.github.sophiecollard.transliterator.validation.ValidationResult

sealed abstract class UnicodeBlock(
  val name: String,
  val firstCodePoint: Int,
  val lastCodePoint: Int
)

object UnicodeBlock {

  final case object BasicLatin extends UnicodeBlock("Basic Latin", 0, 127)

  final case object ASCIIPunctuation extends UnicodeBlock("ASCII punctuation and symbols", 32, 47)

  final case object ASCIIDigits extends UnicodeBlock("ASCII digits", 48, 57)

  final case object HangeulSyllables extends UnicodeBlock("Hangeul Syllables", 44032, 55215)

  /**
    * Validates that a Char instance belongs to the specified Unicode block.
    */
  def validateChar(char: Char, block: UnicodeBlock): ValidationResult[Char] = {
    val codePoint: Int = char.toInt
    if (block.firstCodePoint <= codePoint && codePoint <= block.lastCodePoint)
      Valid(char)
    else
      Invalid.one(ValidationError.CharOutsideUnicodeBlockRange(char, block))
  }

  /**
    * Validates that all characters in a String instance belong to the specified Unicode block.
    */
  def validateString(string: String, block: UnicodeBlock): ValidationResult[String] =
    string
      .toCharArray.toVector
      .map(validateChar(_, block))
      .traverse[ValidationResult, Char](identity)
      .map(_.mkString)

}
