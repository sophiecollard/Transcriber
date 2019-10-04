package com.github.sophiecollard.hangeul4s.model.romanization

import com.github.sophiecollard.hangeul4s.model.romanization.RomanLetter._
import org.specs2.mutable.Specification

class RomanizedTextElementSpec extends Specification {

  "RomanizedTextElement#toString" should {

    "convert a Captured instance to a string" in {
      val element = RomanizedTextElement.Captured.fromLetters(H, E, L, L, O)

      element.toString ==== "hello"
    }

    "convert a NotCaptured instance to a string" in {
      val element = RomanizedTextElement.NotCaptured.unvalidatedFrom("!#&0123456789")

      element.toString ==== "!#&0123456789"
    }

  }

}