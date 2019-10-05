package com.github.sophiecollard.hangeul4s.model.romanization

import com.github.sophiecollard.hangeul4s.model.romanization.RomanLetter._
import com.github.sophiecollard.hangeul4s.parsing.{Token, Unparser, Untokenizer}
import org.specs2.mutable.Specification

class RomanizedTextElementSpec extends Specification {

  "RomanizedTextElement#unparser" should {

    "convert a Captured instance to a string" in {
      val input = RomanizedTextElement.Captured.fromLetters(H, E, L, L, O)

      Unparser[RomanizedTextElement, String].unparse(input) ==== "hello"
    }

    "convert a NotCaptured instance to a string" in {
      val input = RomanizedTextElement.NotCaptured.unvalidatedFrom("!#&0123456789")

      Unparser[RomanizedTextElement, String].unparse(input) ==== "!#&0123456789"
    }

  }

  "RomanizedTextElement#vectorUntokenizer" should {

    "aggregate tokens into a string" in {
      val input = Vector[Token[RomanizedTextElement]](
        Token("hello"), Token(", "), Token("world"), Token("!")
      )

      Untokenizer[Vector, RomanizedTextElement].untokenize(input) ==== "hello, world!"
    }

  }

}
