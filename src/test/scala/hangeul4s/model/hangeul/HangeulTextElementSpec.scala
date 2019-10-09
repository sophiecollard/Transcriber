package hangeul4s.model.hangeul

import cats.data.NonEmptyVector
import cats.instances.vector._
import hangeul4s.error.ParsingFailure
import hangeul4s.model.hangeul.HangeulJamo._
import hangeul4s.model.hangeul.HangeulSyllabicBlock._
import hangeul4s.model.hangeul.HangeulTextElement._
import hangeul4s.parsing._
import hangeul4s.parsing.implicits._
import org.specs2.mutable.Specification

class HangeulTextElementSpec extends Specification {

  "HangeulTextElement#NotCaptured#fromString" should {

    "successfully construct a NotCaptured instance from characters outside of the Hangeul Syllables Unicode block" in {
      val input = "ABCdef !#&0123456789"

      val expectedOutput = HangeulTextElement.NotCaptured.unvalidatedFromString(input)

      HangeulTextElement.NotCaptured.fromString(input) must beSome(expectedOutput)
    }

    "fail to construct a NotCaptured instance from characters within the Hangeul Syllables Unicode block" in {
      val input = "Hello! 안녕하세요!"

      HangeulTextElement.NotCaptured.fromString(input) must beNone
    }

    "fail to construct a NotCaptured instance from an empty string" in {
      HangeulTextElement.NotCaptured.fromString("") must beNone
    }

  }

  "HangeulTextElement#failFastParser" should {

    "parse a single element made of characters from the Hangeul Syllables Unicode block" in {
      val input = "안녕하세요"

      val expectedOutput: HangeulTextElement = HangeulTextElement.Captured.fromSyllabicBlocks(
        ThreeLetter(Initial.ㅇ, Medial.ㅏ, Final.ㄴ),
        ThreeLetter(Initial.ㄴ, Medial.ㅕ, Final.ㅇ),
        TwoLetter(Initial.ㅎ, Medial.ㅏ),
        TwoLetter(Initial.ㅅ, Medial.ㅔ),
        TwoLetter(Initial.ㅇ, Medial.ㅛ)
      )

      Parser[String, HangeulTextElement].parse(input) must beRight(expectedOutput)
    }

    "parse a single element made of other characters" in {
      val input = " !#&0123456789"

      val expectedOutput: HangeulTextElement = HangeulTextElement.NotCaptured.unvalidatedFromString(input)

      Parser[String, HangeulTextElement].parse(input) must beRight(expectedOutput)
    }

    "fail to parse a single element from an empty string" in {
      Parser[String, HangeulTextElement].parse("") must beLeft[ParsingFailure]
    }

    "parse whitespace-separated Hangeul text elements" in {
      // first sentence of second paragraph of the Korean Wikipedia article on Seoul (retrieved 2019-09-22)
      // See https://ko.wikipedia.org/wiki/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C
      val input = "시청 소재지는 중구이며, 25개의 자치구로 이루어져 있다."

      val expectedOutput = Vector[HangeulTextElement](
        HangeulTextElement.Captured.fromSyllabicBlocks(
          TwoLetter(Initial.ㅅ, Medial.ㅣ),
          ThreeLetter(Initial.ㅊ, Medial.ㅓ, Final.ㅇ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFromString(" "),
        HangeulTextElement.Captured.fromSyllabicBlocks(
          TwoLetter(Initial.ㅅ, Medial.ㅗ),
          TwoLetter(Initial.ㅈ, Medial.ㅐ),
          TwoLetter(Initial.ㅈ, Medial.ㅣ),
          ThreeLetter(Initial.ㄴ, Medial.ㅡ, Final.ㄴ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFromString(" "),
        HangeulTextElement.Captured.fromSyllabicBlocks(
          ThreeLetter(Initial.ㅈ, Medial.ㅜ, Final.ㅇ),
          TwoLetter(Initial.ㄱ, Medial.ㅜ),
          TwoLetter(Initial.ㅇ, Medial.ㅣ),
          TwoLetter(Initial.ㅁ, Medial.ㅕ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFromString(", 25"),
        HangeulTextElement.Captured.fromSyllabicBlocks(
          TwoLetter(Initial.ㄱ, Medial.ㅐ),
          TwoLetter(Initial.ㅇ, Medial.ㅢ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFromString(" "),
        HangeulTextElement.Captured.fromSyllabicBlocks(
          TwoLetter(Initial.ㅈ, Medial.ㅏ),
          TwoLetter(Initial.ㅊ, Medial.ㅣ),
          TwoLetter(Initial.ㄱ, Medial.ㅜ),
          TwoLetter(Initial.ㄹ, Medial.ㅗ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFromString(" "),
        HangeulTextElement.Captured.fromSyllabicBlocks(
          TwoLetter(Initial.ㅇ, Medial.ㅣ),
          TwoLetter(Initial.ㄹ, Medial.ㅜ),
          TwoLetter(Initial.ㅇ, Medial.ㅓ),
          TwoLetter(Initial.ㅈ, Medial.ㅕ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFromString(" "),
        HangeulTextElement.Captured.fromSyllabicBlocks(
          ThreeLetter(Initial.ㅇ, Medial.ㅣ, Final.ㅆ),
          TwoLetter(Initial.ㄷ, Medial.ㅏ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFromString(".")
      )

      Parser[String, Vector[HangeulTextElement]].parse(input) must beRight(expectedOutput)
    }

    "return an empty vector when given an empty string" in {
      Parser[String, Vector[HangeulTextElement]].parse("") must beRight(Vector.empty[HangeulTextElement])
    }

  }

  "HangeulTextElement#accumulativeParser" should {

    "parse a single element made of characters from the Hangeul Syllables Unicode block" in {
      val input = "안녕하세요"

      val expectedOutput: HangeulTextElement = HangeulTextElement.Captured.fromSyllabicBlocks(
        ThreeLetter(Initial.ㅇ, Medial.ㅏ, Final.ㄴ),
        ThreeLetter(Initial.ㄴ, Medial.ㅕ, Final.ㅇ),
        TwoLetter(Initial.ㅎ, Medial.ㅏ),
        TwoLetter(Initial.ㅅ, Medial.ㅔ),
        TwoLetter(Initial.ㅇ, Medial.ㅛ)
      )

      AccumulativeParser[String, HangeulTextElement].parse(input).toEither must beRight(expectedOutput)
    }

    "parse a single element made of other characters" in {
      val input = " !#&0123456789"

      val expectedOutput: HangeulTextElement = HangeulTextElement.NotCaptured.unvalidatedFromString(input)

      AccumulativeParser[String, HangeulTextElement].parse(input).toEither must beRight(expectedOutput)
    }

    "fail to parse a single element from an empty string" in {
      AccumulativeParser[String, HangeulTextElement].parse("").toEither must beLeft[NonEmptyVector[ParsingFailure]]
    }

    "parse whitespace-separated Hangeul text elements" in {
      // first sentence of second paragraph of the Korean Wikipedia article on Seoul (retrieved 2019-09-22)
      // See https://ko.wikipedia.org/wiki/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C
      val input = "시청 소재지는 중구이며, 25개의 자치구로 이루어져 있다."

      val expectedOutput = Vector[HangeulTextElement](
        HangeulTextElement.Captured.fromSyllabicBlocks(
          TwoLetter(Initial.ㅅ, Medial.ㅣ),
          ThreeLetter(Initial.ㅊ, Medial.ㅓ, Final.ㅇ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFromString(" "),
        HangeulTextElement.Captured.fromSyllabicBlocks(
          TwoLetter(Initial.ㅅ, Medial.ㅗ),
          TwoLetter(Initial.ㅈ, Medial.ㅐ),
          TwoLetter(Initial.ㅈ, Medial.ㅣ),
          ThreeLetter(Initial.ㄴ, Medial.ㅡ, Final.ㄴ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFromString(" "),
        HangeulTextElement.Captured.fromSyllabicBlocks(
          ThreeLetter(Initial.ㅈ, Medial.ㅜ, Final.ㅇ),
          TwoLetter(Initial.ㄱ, Medial.ㅜ),
          TwoLetter(Initial.ㅇ, Medial.ㅣ),
          TwoLetter(Initial.ㅁ, Medial.ㅕ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFromString(", 25"),
        HangeulTextElement.Captured.fromSyllabicBlocks(
          TwoLetter(Initial.ㄱ, Medial.ㅐ),
          TwoLetter(Initial.ㅇ, Medial.ㅢ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFromString(" "),
        HangeulTextElement.Captured.fromSyllabicBlocks(
          TwoLetter(Initial.ㅈ, Medial.ㅏ),
          TwoLetter(Initial.ㅊ, Medial.ㅣ),
          TwoLetter(Initial.ㄱ, Medial.ㅜ),
          TwoLetter(Initial.ㄹ, Medial.ㅗ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFromString(" "),
        HangeulTextElement.Captured.fromSyllabicBlocks(
          TwoLetter(Initial.ㅇ, Medial.ㅣ),
          TwoLetter(Initial.ㄹ, Medial.ㅜ),
          TwoLetter(Initial.ㅇ, Medial.ㅓ),
          TwoLetter(Initial.ㅈ, Medial.ㅕ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFromString(" "),
        HangeulTextElement.Captured.fromSyllabicBlocks(
          ThreeLetter(Initial.ㅇ, Medial.ㅣ, Final.ㅆ),
          TwoLetter(Initial.ㄷ, Medial.ㅏ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFromString(".")
      )

      AccumulativeParser[String, Vector[HangeulTextElement]].parse(input).toEither must beRight(expectedOutput)
    }

    "return an empty vector when given an empty string" in {
      AccumulativeParser[String, Vector[HangeulTextElement]].parse("").toEither must beRight(Vector.empty[HangeulTextElement])
    }

  }

  "HangeulTextElement#unparser" should {

    "convert a Captured instance to a string" in {
      val input = HangeulTextElement.Captured.fromSyllabicBlocks(
        ThreeLetter(Initial.ㅇ, Medial.ㅏ, Final.ㄴ),
        ThreeLetter(Initial.ㄴ, Medial.ㅕ, Final.ㅇ),
        TwoLetter(Initial.ㅎ, Medial.ㅏ),
        TwoLetter(Initial.ㅅ, Medial.ㅔ),
        TwoLetter(Initial.ㅇ, Medial.ㅛ)
      )

      Unparser[HangeulTextElement, String].unparse(input) ==== "안녕하세요"
    }

    "convert a NotCaptured instance to a string" in {
      val input = HangeulTextElement.NotCaptured.unvalidatedFromString(" !#&0123456789")

      Unparser[HangeulTextElement, String].unparse(input) ==== " !#&0123456789"
    }

  }

  "HangeulTextElement#vectorTokenizer" should {

    "split a string into tokens" in {
      val input = "Hello! 안녕하세요!"

      val expectedOutput = Vector[Token[HangeulTextElement]](
        Token("Hello! "), Token("안녕하세요"), Token("!")
      )

      Tokenizer[Vector, HangeulTextElement].tokenize(input) ==== expectedOutput
    }

    "return an empty vector when passed an empty string" in {
      Tokenizer[Vector, HangeulTextElement].tokenize("") must beEmpty[Vector[Token[HangeulTextElement]]]
    }

  }

  "HangeulTextElement#vectorUntokenizer" should {

    "aggregate tokens into a string" in {
      val input = Vector[Token[HangeulTextElement]](
        Token("Hello! "), Token("안녕하세요"), Token("!")
      )

      Untokenizer[Vector, HangeulTextElement].untokenize(input) ==== "Hello! 안녕하세요!"
    }

    "return an empty string when passed an empty vector" in {
      Untokenizer[Vector, HangeulTextElement].untokenize(Vector.empty) must beEmpty[String]
    }

  }

}
