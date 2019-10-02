package com.github.sophiecollard.hangeul4s.model.hangeul

import com.github.sophiecollard.hangeul4s.model.hangeul.HangeulJamo._
import com.github.sophiecollard.hangeul4s.model.hangeul.HangeulSyllabicBlock._
import org.specs2.mutable.Specification

class HangeulTextElementSpec extends Specification {

  "HangeulTextElement#failFastParser" should {

    "parse a single element made of characters from the Hangeul Syllables Unicode block" in {
      val input = " 안녕하세요"

      val expectedOutput = Vector[HangeulTextElement](
        HangeulTextElement.Captured.fromSyllabicBlocks(
          ThreeLetter(Initial.ㅇ, Medial.ㅏ, Final.ㄴ),
          ThreeLetter(Initial.ㄴ, Medial.ㅕ, Final.ㅇ),
          TwoLetter(Initial.ㅎ, Medial.ㅏ),
          TwoLetter(Initial.ㅅ, Medial.ㅔ),
          TwoLetter(Initial.ㅇ, Medial.ㅛ)
        )
      )

      HangeulTextElement.failFastParser.parse(input) must beRight(expectedOutput)
    }

    "parse a single element made of other characters" in {
      val input = " !#&0123456789"

      val expectedOutput = Vector[HangeulTextElement](HangeulTextElement.NotCaptured.unvalidatedFrom(input.trim))

      HangeulTextElement.failFastParser.parse(input) must beRight(expectedOutput)
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
        HangeulTextElement.Captured.fromSyllabicBlocks(
          TwoLetter(Initial.ㅅ, Medial.ㅗ),
          TwoLetter(Initial.ㅈ, Medial.ㅐ),
          TwoLetter(Initial.ㅈ, Medial.ㅣ),
          ThreeLetter(Initial.ㄴ, Medial.ㅡ, Final.ㄴ)
        ),
        HangeulTextElement.Captured.fromSyllabicBlocks(
          ThreeLetter(Initial.ㅈ, Medial.ㅜ, Final.ㅇ),
          TwoLetter(Initial.ㄱ, Medial.ㅜ),
          TwoLetter(Initial.ㅇ, Medial.ㅣ),
          TwoLetter(Initial.ㅁ, Medial.ㅕ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFrom(","),
        HangeulTextElement.NotCaptured.unvalidatedFrom("25"),
        HangeulTextElement.Captured.fromSyllabicBlocks(
          TwoLetter(Initial.ㄱ, Medial.ㅐ),
          TwoLetter(Initial.ㅇ, Medial.ㅢ)
        ),
        HangeulTextElement.Captured.fromSyllabicBlocks(
          TwoLetter(Initial.ㅈ, Medial.ㅏ),
          TwoLetter(Initial.ㅊ, Medial.ㅣ),
          TwoLetter(Initial.ㄱ, Medial.ㅜ),
          TwoLetter(Initial.ㄹ, Medial.ㅗ)
        ),
        HangeulTextElement.Captured.fromSyllabicBlocks(
          TwoLetter(Initial.ㅇ, Medial.ㅣ),
          TwoLetter(Initial.ㄹ, Medial.ㅜ),
          TwoLetter(Initial.ㅇ, Medial.ㅓ),
          TwoLetter(Initial.ㅈ, Medial.ㅕ)
        ),
        HangeulTextElement.Captured.fromSyllabicBlocks(
          ThreeLetter(Initial.ㅇ, Medial.ㅣ, Final.ㅆ),
          TwoLetter(Initial.ㄷ, Medial.ㅏ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFrom(".")
      )

      HangeulTextElement.failFastParser.parse(input) must beRight(expectedOutput)
    }

  }

  "HangeulTextElement#accumulativeParser" should {

    "parse a single element made of characters from the Hangeul Syllables Unicode block" in {
      val input = " 안녕하세요"

      val expectedOutput = Vector[HangeulTextElement](
        HangeulTextElement.Captured.fromSyllabicBlocks(
          ThreeLetter(Initial.ㅇ, Medial.ㅏ, Final.ㄴ),
          ThreeLetter(Initial.ㄴ, Medial.ㅕ, Final.ㅇ),
          TwoLetter(Initial.ㅎ, Medial.ㅏ),
          TwoLetter(Initial.ㅅ, Medial.ㅔ),
          TwoLetter(Initial.ㅇ, Medial.ㅛ)
        )
      )

      HangeulTextElement.accumulativeParser.parse(input).toEither must beRight(expectedOutput)
    }

    "parse a single element made of other characters" in {
      val input = " !#&0123456789"

      val expectedOutput = Vector[HangeulTextElement](HangeulTextElement.NotCaptured.unvalidatedFrom(input.trim))

      HangeulTextElement.accumulativeParser.parse(input).toEither must beRight(expectedOutput)
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
        HangeulTextElement.Captured.fromSyllabicBlocks(
          TwoLetter(Initial.ㅅ, Medial.ㅗ),
          TwoLetter(Initial.ㅈ, Medial.ㅐ),
          TwoLetter(Initial.ㅈ, Medial.ㅣ),
          ThreeLetter(Initial.ㄴ, Medial.ㅡ, Final.ㄴ)
        ),
        HangeulTextElement.Captured.fromSyllabicBlocks(
          ThreeLetter(Initial.ㅈ, Medial.ㅜ, Final.ㅇ),
          TwoLetter(Initial.ㄱ, Medial.ㅜ),
          TwoLetter(Initial.ㅇ, Medial.ㅣ),
          TwoLetter(Initial.ㅁ, Medial.ㅕ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFrom(","),
        HangeulTextElement.NotCaptured.unvalidatedFrom("25"),
        HangeulTextElement.Captured.fromSyllabicBlocks(
          TwoLetter(Initial.ㄱ, Medial.ㅐ),
          TwoLetter(Initial.ㅇ, Medial.ㅢ)
        ),
        HangeulTextElement.Captured.fromSyllabicBlocks(
          TwoLetter(Initial.ㅈ, Medial.ㅏ),
          TwoLetter(Initial.ㅊ, Medial.ㅣ),
          TwoLetter(Initial.ㄱ, Medial.ㅜ),
          TwoLetter(Initial.ㄹ, Medial.ㅗ)
        ),
        HangeulTextElement.Captured.fromSyllabicBlocks(
          TwoLetter(Initial.ㅇ, Medial.ㅣ),
          TwoLetter(Initial.ㄹ, Medial.ㅜ),
          TwoLetter(Initial.ㅇ, Medial.ㅓ),
          TwoLetter(Initial.ㅈ, Medial.ㅕ)
        ),
        HangeulTextElement.Captured.fromSyllabicBlocks(
          ThreeLetter(Initial.ㅇ, Medial.ㅣ, Final.ㅆ),
          TwoLetter(Initial.ㄷ, Medial.ㅏ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFrom(".")
      )

      HangeulTextElement.accumulativeParser.parse(input).toEither must beRight(expectedOutput)
    }

  }

  "HangeulTextElement#regexParser" should {

    "parse a single element made of characters from the Hangeul Syllables Unicode block" in {
      val input = " 안녕하세요"

      val expectedOutput = Vector[HangeulTextElement](
        HangeulTextElement.Captured.fromSyllabicBlocks(
          ThreeLetter(Initial.ㅇ, Medial.ㅏ, Final.ㄴ),
          ThreeLetter(Initial.ㄴ, Medial.ㅕ, Final.ㅇ),
          TwoLetter(Initial.ㅎ, Medial.ㅏ),
          TwoLetter(Initial.ㅅ, Medial.ㅔ),
          TwoLetter(Initial.ㅇ, Medial.ㅛ)
        )
      )

      HangeulTextElement.regexParser.parse(input) must beRight(expectedOutput)
    }

    "parse a single element made of other characters" in {
      val input = " !#&0123456789"

      val expectedOutput = Vector[HangeulTextElement](HangeulTextElement.NotCaptured.unvalidatedFrom(input.trim))

      HangeulTextElement.regexParser.parse(input) must beRight(expectedOutput)
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
        HangeulTextElement.Captured.fromSyllabicBlocks(
          TwoLetter(Initial.ㅅ, Medial.ㅗ),
          TwoLetter(Initial.ㅈ, Medial.ㅐ),
          TwoLetter(Initial.ㅈ, Medial.ㅣ),
          ThreeLetter(Initial.ㄴ, Medial.ㅡ, Final.ㄴ)
        ),
        HangeulTextElement.Captured.fromSyllabicBlocks(
          ThreeLetter(Initial.ㅈ, Medial.ㅜ, Final.ㅇ),
          TwoLetter(Initial.ㄱ, Medial.ㅜ),
          TwoLetter(Initial.ㅇ, Medial.ㅣ),
          TwoLetter(Initial.ㅁ, Medial.ㅕ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFrom(", 25"), // TODO fix
        HangeulTextElement.Captured.fromSyllabicBlocks(
          TwoLetter(Initial.ㄱ, Medial.ㅐ),
          TwoLetter(Initial.ㅇ, Medial.ㅢ)
        ),
        HangeulTextElement.Captured.fromSyllabicBlocks(
          TwoLetter(Initial.ㅈ, Medial.ㅏ),
          TwoLetter(Initial.ㅊ, Medial.ㅣ),
          TwoLetter(Initial.ㄱ, Medial.ㅜ),
          TwoLetter(Initial.ㄹ, Medial.ㅗ)
        ),
        HangeulTextElement.Captured.fromSyllabicBlocks(
          TwoLetter(Initial.ㅇ, Medial.ㅣ),
          TwoLetter(Initial.ㄹ, Medial.ㅜ),
          TwoLetter(Initial.ㅇ, Medial.ㅓ),
          TwoLetter(Initial.ㅈ, Medial.ㅕ)
        ),
        HangeulTextElement.Captured.fromSyllabicBlocks(
          ThreeLetter(Initial.ㅇ, Medial.ㅣ, Final.ㅆ),
          TwoLetter(Initial.ㄷ, Medial.ㅏ)
        ),
        HangeulTextElement.NotCaptured.unvalidatedFrom(".")
      )

      HangeulTextElement.regexParser.parse(input) must beRight(expectedOutput)
    }

  }

}
