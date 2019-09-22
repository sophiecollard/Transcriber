package com.github.sophiecollard.hangeul4s.model.hangeul

import com.github.sophiecollard.hangeul4s.error.ParsingError
import com.github.sophiecollard.hangeul4s.util.types.NonEmptyVector
import org.specs2.mutable.Specification

class HangeulTextElementSpec extends Specification {

  "HangeulTextElement#parser" should {

    "parse words made of characters from the Hangeul Syllables Unicode block" in {
      val input = "안녕하세요"

      val expectedOutput: HangeulTextElement = HangeulTextElement.Word(
        NonEmptyVector.of(
          HangeulSyllabicBlock.ThreeLetter(
            HangeulJamo.Initial.ㅇ,
            HangeulJamo.Medial.ㅏ,
            HangeulJamo.Final.ㄴ
          ),
          HangeulSyllabicBlock.ThreeLetter(
            HangeulJamo.Initial.ㄴ,
            HangeulJamo.Medial.ㅕ,
            HangeulJamo.Final.ㅇ
          ),
          HangeulSyllabicBlock.TwoLetter(
            HangeulJamo.Initial.ㅎ,
            HangeulJamo.Medial.ㅏ
          ),
          HangeulSyllabicBlock.TwoLetter(
            HangeulJamo.Initial.ㅅ,
            HangeulJamo.Medial.ㅔ
          ),
          HangeulSyllabicBlock.TwoLetter(
            HangeulJamo.Initial.ㅇ,
            HangeulJamo.Medial.ㅛ
          )
        )
      )

      HangeulTextElement.parser.parse(input) must beRight(expectedOutput)
    }

    "parse ASCII punctuation and symbols" in {
      val input = "!#&"

      val expectedOutput: HangeulTextElement = HangeulTextElement.Punctuation.unvalidatedFrom(input)

      HangeulTextElement.parser.parse(input) must beRight(expectedOutput)
    }

    "parse ASCII digits" in {
      val input = "0123456789"

      val expectedOutput: HangeulTextElement = HangeulTextElement.Digits.unvalidatedFrom(input)

      HangeulTextElement.parser.parse(input) must beRight(expectedOutput)
    }

    "fail to parse an input with characters outside the supported Unicode blocks" in {
      val input = ":;<=>?@"

      val expectedOutput: HangeulTextElement = HangeulTextElement.Punctuation.unvalidatedFrom(input)

      HangeulTextElement.parser.parse(input) must beLeft[ParsingError]
    }

  }

}
