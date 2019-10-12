package hangeul4s.model.romanization

import hangeul4s.model.romanization.RomanLetter._
import hangeul4s.parsing.{Token, Tokenizer, Unparser, Untokenizer}
import org.specs2.mutable.Specification

class RomanizedTextElementSpec extends Specification {

  "RomanizedTextElement#Captured#regex" should {

    "only match characters within the range [A-EG-PR-UWYa-eg-pr-uwy]" in {
      val input = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"

      val expectedOutput = List("ABCDE", "GHIJKLMNOP", "RSTU", "W", "Y", "abcde", "ghijklmnop", "rstu", "w", "y")

      RomanizedTextElement.Captured.regex.findAllIn(input).toList must
        containTheSameElementsAs(expectedOutput)
    }

  }

  "RomanizedTextElement#NotCaptured#regex" should {

    "only match characters outside the range [A-EG-PR-UWYa-eg-pr-uwy]" in {
      val input = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"

      val expectedOutput = List("F", "Q", "V", "X", "Z", "f", "q", "v", "x", "z")

      RomanizedTextElement.NotCaptured.regex.findAllIn(input).toList must
        containTheSameElementsAs(expectedOutput)
    }

  }

  "RomanizedTextElement#NotCaptured#fromString" should {

    "successfully construct a NotCaptured instance from characters not matching the [A-EG-PR-UWYa-eg-pr-uwy] regex" in {
      val input = " !#&0123456789"

      val expectedOutput = RomanizedTextElement.NotCaptured.unvalidatedFromString(input)

      RomanizedTextElement.NotCaptured.fromString(input) must beSome(expectedOutput)
    }

    "fail to construct a NotCaptured instance from characters matching the [A-EG-PR-UWYa-eg-pr-uwy] regex" in {
      val input = "ABCdef !#&0123456789"

      RomanizedTextElement.NotCaptured.fromString(input) must beNone
    }

    "fail to construct a NotCaptured instance from an empty string" in {
      RomanizedTextElement.NotCaptured.fromString("") must beNone
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

    "return an empty vector when passed an empty string" in {
      Tokenizer[Vector, RomanizedTextElement].tokenize("") must beEmpty[Vector[Token[RomanizedTextElement]]]
    }

  }

  "RomanizedTextElement#vectorUntokenizer" should {

    "aggregate tokens into a string" in {
      val input = Vector[Token[RomanizedTextElement]](
        Token("Hello"), Token("! 안녕하세요!")
      )

      Untokenizer[Vector, RomanizedTextElement].untokenize(input) ==== "Hello! 안녕하세요!"
    }

    "return an empty string when passed an empty vector" in {
      Untokenizer[Vector, RomanizedTextElement].untokenize(Vector.empty) must beEmpty[String]
    }

  }

}
