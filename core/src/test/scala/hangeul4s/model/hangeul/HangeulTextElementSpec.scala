package hangeul4s.model.hangeul

import cats.data.NonEmptyVector
import cats.instances.vector._
import hangeul4s.error.ParsingFailure
import hangeul4s.model.hangeul.HangeulJamo._
import hangeul4s.model.hangeul.HangeulSyllable._
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

      val expectedOutput: HangeulTextElement = HangeulTextElement.Captured.fromSyllables(
        ThreeJamo(Initial.ㅇ, Medial.ㅏ, Final.ㄴ),
        ThreeJamo(Initial.ㄴ, Medial.ㅕ, Final.ㅇ),
        TwoJamo(Initial.ㅎ, Medial.ㅏ),
        TwoJamo(Initial.ㅅ, Medial.ㅔ),
        TwoJamo(Initial.ㅇ, Medial.ㅛ)
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
        HangeulTextElement.Captured.fromSyllables(
          TwoJamo(Initial.ㅅ, Medial.ㅣ),
          ThreeJamo(Initial.ㅊ, Medial.ㅓ, Final.ㅇ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFromString(" "),
        HangeulTextElement.Captured.fromSyllables(
          TwoJamo(Initial.ㅅ, Medial.ㅗ),
          TwoJamo(Initial.ㅈ, Medial.ㅐ),
          TwoJamo(Initial.ㅈ, Medial.ㅣ),
          ThreeJamo(Initial.ㄴ, Medial.ㅡ, Final.ㄴ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFromString(" "),
        HangeulTextElement.Captured.fromSyllables(
          ThreeJamo(Initial.ㅈ, Medial.ㅜ, Final.ㅇ),
          TwoJamo(Initial.ㄱ, Medial.ㅜ),
          TwoJamo(Initial.ㅇ, Medial.ㅣ),
          TwoJamo(Initial.ㅁ, Medial.ㅕ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFromString(", 25"),
        HangeulTextElement.Captured.fromSyllables(
          TwoJamo(Initial.ㄱ, Medial.ㅐ),
          TwoJamo(Initial.ㅇ, Medial.ㅢ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFromString(" "),
        HangeulTextElement.Captured.fromSyllables(
          TwoJamo(Initial.ㅈ, Medial.ㅏ),
          TwoJamo(Initial.ㅊ, Medial.ㅣ),
          TwoJamo(Initial.ㄱ, Medial.ㅜ),
          TwoJamo(Initial.ㄹ, Medial.ㅗ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFromString(" "),
        HangeulTextElement.Captured.fromSyllables(
          TwoJamo(Initial.ㅇ, Medial.ㅣ),
          TwoJamo(Initial.ㄹ, Medial.ㅜ),
          TwoJamo(Initial.ㅇ, Medial.ㅓ),
          TwoJamo(Initial.ㅈ, Medial.ㅕ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFromString(" "),
        HangeulTextElement.Captured.fromSyllables(
          ThreeJamo(Initial.ㅇ, Medial.ㅣ, Final.ㅆ),
          TwoJamo(Initial.ㄷ, Medial.ㅏ)
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

      val expectedOutput: HangeulTextElement = HangeulTextElement.Captured.fromSyllables(
        ThreeJamo(Initial.ㅇ, Medial.ㅏ, Final.ㄴ),
        ThreeJamo(Initial.ㄴ, Medial.ㅕ, Final.ㅇ),
        TwoJamo(Initial.ㅎ, Medial.ㅏ),
        TwoJamo(Initial.ㅅ, Medial.ㅔ),
        TwoJamo(Initial.ㅇ, Medial.ㅛ)
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
        HangeulTextElement.Captured.fromSyllables(
          TwoJamo(Initial.ㅅ, Medial.ㅣ),
          ThreeJamo(Initial.ㅊ, Medial.ㅓ, Final.ㅇ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFromString(" "),
        HangeulTextElement.Captured.fromSyllables(
          TwoJamo(Initial.ㅅ, Medial.ㅗ),
          TwoJamo(Initial.ㅈ, Medial.ㅐ),
          TwoJamo(Initial.ㅈ, Medial.ㅣ),
          ThreeJamo(Initial.ㄴ, Medial.ㅡ, Final.ㄴ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFromString(" "),
        HangeulTextElement.Captured.fromSyllables(
          ThreeJamo(Initial.ㅈ, Medial.ㅜ, Final.ㅇ),
          TwoJamo(Initial.ㄱ, Medial.ㅜ),
          TwoJamo(Initial.ㅇ, Medial.ㅣ),
          TwoJamo(Initial.ㅁ, Medial.ㅕ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFromString(", 25"),
        HangeulTextElement.Captured.fromSyllables(
          TwoJamo(Initial.ㄱ, Medial.ㅐ),
          TwoJamo(Initial.ㅇ, Medial.ㅢ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFromString(" "),
        HangeulTextElement.Captured.fromSyllables(
          TwoJamo(Initial.ㅈ, Medial.ㅏ),
          TwoJamo(Initial.ㅊ, Medial.ㅣ),
          TwoJamo(Initial.ㄱ, Medial.ㅜ),
          TwoJamo(Initial.ㄹ, Medial.ㅗ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFromString(" "),
        HangeulTextElement.Captured.fromSyllables(
          TwoJamo(Initial.ㅇ, Medial.ㅣ),
          TwoJamo(Initial.ㄹ, Medial.ㅜ),
          TwoJamo(Initial.ㅇ, Medial.ㅓ),
          TwoJamo(Initial.ㅈ, Medial.ㅕ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFromString(" "),
        HangeulTextElement.Captured.fromSyllables(
          ThreeJamo(Initial.ㅇ, Medial.ㅣ, Final.ㅆ),
          TwoJamo(Initial.ㄷ, Medial.ㅏ)
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
      val input = HangeulTextElement.Captured.fromSyllables(
        ThreeJamo(Initial.ㅇ, Medial.ㅏ, Final.ㄴ),
        ThreeJamo(Initial.ㄴ, Medial.ㅕ, Final.ㅇ),
        TwoJamo(Initial.ㅎ, Medial.ㅏ),
        TwoJamo(Initial.ㅅ, Medial.ㅔ),
        TwoJamo(Initial.ㅇ, Medial.ㅛ)
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
