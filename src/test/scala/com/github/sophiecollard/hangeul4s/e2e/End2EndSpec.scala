package com.github.sophiecollard.hangeul4s.e2e

import cats.instances.vector._
import cats.syntax.either._
import com.github.sophiecollard.hangeul4s.error.ParsingFailure
import com.github.sophiecollard.hangeul4s.model.hangeul.HangeulTextElement
import com.github.sophiecollard.hangeul4s.model.hangeul.HangeulTextElement._
import com.github.sophiecollard.hangeul4s.model.romanization.RomanizedTextElement
import com.github.sophiecollard.hangeul4s.model.romanization.RomanizedTextElement._
import com.github.sophiecollard.hangeul4s.parsing.implicits._
import com.github.sophiecollard.hangeul4s.transliteration.hangeul.instances._
import com.github.sophiecollard.hangeul4s.transliteration.implicits._
import org.specs2.mutable.Specification

class End2EndSpec extends Specification {

  "This library" should {

    "tokenize, parse, transliterate, unparse and untokenize a single Hangeul text element" in {
      val input = "안녕하세요"

      val output = for {
        parsed <- input.parse[HangeulTextElement]
        transliterated <- parsed.transliterate[RomanizedTextElement]
      } yield transliterated.unparse[String]

      output must beRight("annyeonghaseyo")
    }

    "fail to process an empty string as a single Hangeul text element" in {
      val output = for {
        parsed <- "".parse[HangeulTextElement]
        transliterated <- parsed.transliterate[RomanizedTextElement]
      } yield transliterated.unparse[String]

      output.leftMap(_.message) must beLeft(ParsingFailure.FailedToMatchRegex("", "[^\uAC00-\uD7AF]+".r).message)
    }

    "tokenize, parse, transliterate, unparse and untokenize Hangeul text" in {
      // first sentence of second paragraph of the Korean Wikipedia article on Seoul (retrieved 2019-09-22)
      // See https://ko.wikipedia.org/wiki/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C
      val input = "시청 소재지는 중구이며, 25개의 자치구로 이루어져 있다."

      val output = for {
        parsed <- input.parseF[Vector, HangeulTextElement]
        transliterated <- parsed.transliterateF[Vector, RomanizedTextElement]
      } yield transliterated.unparse[String] // OR transliterated.unparseF[Vector, Token[RomanizedTextElement]].untokenize

      output must beRight("sicheong sojaejineun jungguimyeo, 25gaeui jachiguro irueojyeo itda.")
    }

    "return an empty string when passed an empty string as Hangeul text" in {
      val output = for {
        parsed <- "".parseF[Vector, HangeulTextElement]
        transliterated <- parsed.transliterateF[Vector, RomanizedTextElement]
      } yield transliterated.unparse[String] // OR transliterated.unparseF[Vector, Token[RomanizedTextElement]].untokenize

      output must beRight("")
    }

  }

}
