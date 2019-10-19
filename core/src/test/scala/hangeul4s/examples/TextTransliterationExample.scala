package hangeul4s.examples

import cats.implicits._
import hangeul4s.implicits._
import hangeul4s.model.hangeul.HangeulTextElement
import hangeul4s.model.romanization.RomanizedTextElement
import hangeul4s.parsing.Token
import org.specs2.mutable.Specification

class TextTransliterationExample extends Specification {

  /**
    * First sentence of second paragraph of the Korean Wikipedia article on Seoul (retrieved 2019-09-22)
    *
    * See https://ko.wikipedia.org/wiki/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C
    */
  private val input = "시청 소재지는 중구이며, 25개의 자치구로 이루어져 있다."

  private val expectedResult = "sicheong sojaejineun jungguimyeo, 25gaeui jachiguro irueojyeo itda."

  "This library" should {

    "tokenize/parse, transliterate and unparse/untokenize Hangeul text" in {
      val result = for {
        parsed <- input.parseToF[Vector, HangeulTextElement]
        transliterated <- parsed.transliterateToF[Vector, RomanizedTextElement]
      } yield transliterated.unparseTo[String]

      result must beRight(expectedResult)
    }

    "tokenize, parse, transliterate, unparse and untokenize Hangeul text" in {
      val tokens = input.tokenizeTo[Vector, HangeulTextElement]

      val result = for {
        // TODO improve low-level parsing API
        parsed <- tokens.traverse(_.parseTo[HangeulTextElement])
        transliterated <- parsed.transliterateToF[Vector, RomanizedTextElement]
        // TODO improve low-level unparsing API
        unparsed = transliterated.map(_.unparseTo[Token[RomanizedTextElement]])
      } yield unparsed.untokenize

      result must beRight(expectedResult)
    }

    "return an empty string when passed an empty string as Hangeul text" in {
      val result = for {
        parsed <- "".parseToF[Vector, HangeulTextElement]
        transliterated <- parsed.transliterateToF[Vector, RomanizedTextElement]
      } yield transliterated.unparseTo[String]

      result must beRight("")
    }

  }

}
