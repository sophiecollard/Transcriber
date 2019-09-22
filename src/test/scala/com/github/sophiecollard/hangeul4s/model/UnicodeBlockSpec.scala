package com.github.sophiecollard.hangeul4s.model

import com.github.sophiecollard.hangeul4s.error.ValidationError
import com.github.sophiecollard.hangeul4s.util.types.NonEmptyVector
import com.github.sophiecollard.hangeul4s.util.types.ValidatedNev.{Invalid, Valid}
import org.specs2.mutable.Specification

class UnicodeBlockSpec extends Specification {

  "UnicodeBlock#validateChar" should {

    "return a valid result when given a char which belongs to the specified Unicode block" in {
      val char = '5'
      val block = UnicodeBlock.ASCIIDigits
      UnicodeBlock.validateChar(char, block) === Valid(char)
    }

    "return a validation error when given a char which does not belong to the specified unicode block" in {
      val char = '?'
      val block = UnicodeBlock.ASCIIDigits
      UnicodeBlock.validateChar(char, block) ===
        Invalid.one(ValidationError.CharOutsideUnicodeBlockRange(char, block))
    }

  }

  "UnicodeBlock#validateString" should {

    "return a valid result when given a string whose characters all belong to the specified Unicode block" in {
      val string = "0123456789"
      val block = UnicodeBlock.ASCIIDigits
      UnicodeBlock.validateString(string, block) === Valid(string)
    }

    "return validation errors when given a string containing characters that do not belong to the specified Unicode block" in {
      val string = "012E4567#9"
      val block = UnicodeBlock.ASCIIDigits
      UnicodeBlock.validateString(string, block) ===
        Invalid(
          NonEmptyVector.of(
            ValidationError.CharOutsideUnicodeBlockRange('E', UnicodeBlock.ASCIIDigits),
            ValidationError.CharOutsideUnicodeBlockRange('#', UnicodeBlock.ASCIIDigits)
          )
        )
    }

  }

}
