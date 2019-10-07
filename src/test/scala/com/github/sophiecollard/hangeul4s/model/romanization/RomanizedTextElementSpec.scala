package com.github.sophiecollard.hangeul4s.model.romanization

import com.github.sophiecollard.hangeul4s.model.romanization.RomanLetter._
import com.github.sophiecollard.hangeul4s.parsing.{Token, Tokenizer, Unparser, Untokenizer}
import org.specs2.mutable.Specification

class RomanizedTextElementSpec extends Specification {

  "RomanizedTextElement#NotCaptured#fromString" should {

    "successfully construct a NotCaptured instance from characters outside of the [A-Za-z] range" in {
      val input = " !#&0123456789"

      val expectedOutput = RomanizedTextElement.NotCaptured.unvalidatedFromString(input)

      RomanizedTextElement.NotCaptured.fromString(input) must beSome(expectedOutput)
    }

    "fail to construct a NotCaptured instance from characters within the [A-Za-z] range" in {
      val input = "ABCdef !#&0123456789"

      RomanizedTextElement.NotCaptured.fromString(input) must beNone
    }

  }

  "RomanizedTextElement#unparser" should {

    "convert a Captured instance to a string" in {
      val input = RomanizedTextElement.Captured.fromLetters(H, E, L, L, O)

      Unparser[RomanizedTextElement, String].unparse(input) ==== "hello"
    }

    "convert a NotCaptured instance to a string" in {
      val input = RomanizedTextElement.NotCaptured.unvalidatedFromString("!#&0123456789")

      Unparser[RomanizedTextElement, String].unparse(input) ==== "!#&0123456789"
    }

  }

  "RomanizedTextElement#vectorTokenizer" should {

    "split a string into tokens" in {
      val input = "Hello! 안녕하세요!"

      val expectedOutput = Vector[Token[RomanizedTextElement]](
        Token("Hello"), Token("! 안녕하세요!")
      )

      Tokenizer[Vector, RomanizedTextElement].tokenize(input) ==== expectedOutput
    }

  }

  "RomanizedTextElement#vectorUntokenizer" should {

    "aggregate tokens into a string" in {
      val input = Vector[Token[RomanizedTextElement]](
        Token("Hello"), Token("! 안녕하세요!")
      )

      Untokenizer[Vector, RomanizedTextElement].untokenize(input) ==== "Hello! 안녕하세요!"
    }

  }

}
