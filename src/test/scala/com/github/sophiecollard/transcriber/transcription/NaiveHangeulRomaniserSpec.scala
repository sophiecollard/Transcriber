package com.github.sophiecollard.transcriber.transcription

import com.github.sophiecollard.transcriber.charset.{HangeulChar, HangeulSyllabicBlock, RomanChar}
import com.github.sophiecollard.transcriber.text.{HangeulText, RomanizedText}
import org.specs2.mutable.Specification

class NaiveHangeulRomaniserSpec extends Specification {

  "Hangeul" should {

    "be romanised, albeit in a naive fashion" in {
      // Spelling of "제주도"
      val input = HangeulText(
        List(
          HangeulSyllabicBlock.TwoLetter(
            HangeulChar.ㅈ,
            HangeulChar.ㅔ
          ),
          HangeulSyllabicBlock.TwoLetter(
            HangeulChar.ㅈ,
            HangeulChar.ㅜ
          ),
          HangeulSyllabicBlock.TwoLetter(
            HangeulChar.ㄷ,
            HangeulChar.ㅗ
          )
        )
      )

      val expectedOutput = RomanizedText(
        List(
          RomanChar.J,
          RomanChar.E,
          RomanChar.J,
          RomanChar.U,
          RomanChar.D,
          RomanChar.O
        )
      )

      NaiveHangeulRomaniser.transcribe(input) must beRight(expectedOutput)
    }

  }

}
