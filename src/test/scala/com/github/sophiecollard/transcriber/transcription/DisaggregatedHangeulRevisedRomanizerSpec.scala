package com.github.sophiecollard.transcriber.transcription

import com.github.sophiecollard.transcriber.charset.{HangeulChar, RomanChar}
import com.github.sophiecollard.transcriber.text.{HangeulText, RomanizedText}
import org.specs2.mutable.Specification

class DisaggregatedHangeulRevisedRomanizerSpec extends Specification {

  "Disaggregated Hangeul" should {

    "be romanized" in {
      // Disaggregated spelling of "제주도"
      val input = HangeulText(
        List(
          HangeulChar.ㅈ,
          HangeulChar.ㅔ,
          HangeulChar.ㅈ,
          HangeulChar.ㅜ,
          HangeulChar.ㄷ,
          HangeulChar.ㅗ
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

      DisaggregatedHangeulRevisedRomanizer.transcribe(input) must beRight(expectedOutput)
    }

  }

}
