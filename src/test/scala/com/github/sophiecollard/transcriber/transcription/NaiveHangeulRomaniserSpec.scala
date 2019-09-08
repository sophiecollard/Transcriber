package com.github.sophiecollard.transcriber.transcription

import com.github.sophiecollard.transcriber.charset.{HangeulLetter, HangeulSyllabicBlock, RomanLetter}
import com.github.sophiecollard.transcriber.text.{HangeulText, RomanizedText}
import org.specs2.mutable.Specification

class NaiveHangeulRomaniserSpec extends Specification {

  "Hangeul" should {

    "be romanised, albeit in a naive fashion" in {
      // Spelling of "제주도"
      val input = HangeulText(
        Vector(
          HangeulSyllabicBlock.TwoLetter(
            HangeulLetter.ㅈ,
            HangeulLetter.ㅔ
          ),
          HangeulSyllabicBlock.TwoLetter(
            HangeulLetter.ㅈ,
            HangeulLetter.ㅜ
          ),
          HangeulSyllabicBlock.TwoLetter(
            HangeulLetter.ㄷ,
            HangeulLetter.ㅗ
          )
        )
      )

      val expectedOutput = RomanizedText(
        Vector(
          RomanLetter.J,
          RomanLetter.E,
          RomanLetter.J,
          RomanLetter.U,
          RomanLetter.D,
          RomanLetter.O
        )
      )

      NaiveHangeulRomaniser.transcribe(input) must beRight(expectedOutput)
    }

  }

}
