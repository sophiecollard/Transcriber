package com.github.sophiecollard.hangeul4s.model.hangeul

import com.github.sophiecollard.hangeul4s.model.hangeul.HangeulJamo._
import com.github.sophiecollard.hangeul4s.model.hangeul.HangeulSyllabicBlock._
import com.github.sophiecollard.hangeul4s.error.ParsingError
import org.specs2.mutable.Specification

class HangeulTextElementSpec extends Specification {

  "HangeulTextElement#parser" should {

    "parse elements made of characters from the Hangeul Syllables Unicode block" in {
      val input = "안녕하세요"

      val expectedOutput: HangeulTextElement = HangeulTextElement.Captured.fromSyllabicBlocks(
        ThreeLetter(Initial.ㅇ, Medial.ㅏ, Final.ㄴ),
        ThreeLetter(Initial.ㄴ, Medial.ㅕ, Final.ㅇ),
        TwoLetter(Initial.ㅎ, Medial.ㅏ),
        TwoLetter(Initial.ㅅ, Medial.ㅔ),
        TwoLetter(Initial.ㅇ, Medial.ㅛ)
      )

      HangeulTextElement.parser.parse(input) must beRight(expectedOutput)
    }

    "parse elements made of other characters" in {
      val input = "!#&0123456789"

      val expectedOutput: HangeulTextElement = HangeulTextElement.NotCaptured.unvalidatedFrom(input)

      HangeulTextElement.parser.parse(input) must beRight(expectedOutput)
    }

  }

}
