package com.github.sophiecollard.hangeul4s.model.hangeul

import com.github.sophiecollard.hangeul4s.model.hangeul.HangeulJamo.{Final, Initial, Medial}
import com.github.sophiecollard.hangeul4s.model.hangeul.HangeulSyllabicBlock.{ThreeLetter, TwoLetter}
import org.specs2.mutable.Specification

class HangeulTextSpec extends Specification {

  "HangeulText#parser" should {

    "parse whitespace-separated Hangeul text elements" in {
      // first sentence of second paragraph of the Korean Wikipedia article on Seoul (retrieved 2019-09-22)
      // See https://ko.wikipedia.org/wiki/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C
      // val input = "시청 소재지는 중구이며, 25개의 자치구로 이루어져 있다."
      val input = "시청 소재지는 중구이며 , 25 개의 자치구로 이루어져 있다 ."

      val expectedOutput = HangeulText.fromElements(
        HangeulTextElement.Word.fromSyllabicBlocks(
          TwoLetter(Initial.ㅅ, Medial.ㅣ),
          ThreeLetter(Initial.ㅊ, Medial.ㅓ, Final.ㅇ)
        ),
        HangeulTextElement.Word.fromSyllabicBlocks(
          TwoLetter(Initial.ㅅ, Medial.ㅗ),
          TwoLetter(Initial.ㅈ, Medial.ㅐ),
          TwoLetter(Initial.ㅈ, Medial.ㅣ),
          ThreeLetter(Initial.ㄴ, Medial.ㅡ, Final.ㄴ)
        ),
        HangeulTextElement.Word.fromSyllabicBlocks(
          ThreeLetter(Initial.ㅈ, Medial.ㅜ, Final.ㅇ),
          TwoLetter(Initial.ㄱ, Medial.ㅜ),
          TwoLetter(Initial.ㅇ, Medial.ㅣ),
          TwoLetter(Initial.ㅁ, Medial.ㅕ)
        ),
        HangeulTextElement.Punctuation.unvalidatedFrom(","),
        HangeulTextElement.Digits.unvalidatedFrom("25"),
        HangeulTextElement.Word.fromSyllabicBlocks(
          TwoLetter(Initial.ㄱ, Medial.ㅐ),
          TwoLetter(Initial.ㅇ, Medial.ㅢ)
        ),
        HangeulTextElement.Word.fromSyllabicBlocks(
          TwoLetter(Initial.ㅈ, Medial.ㅏ),
          TwoLetter(Initial.ㅊ, Medial.ㅣ),
          TwoLetter(Initial.ㄱ, Medial.ㅜ),
          TwoLetter(Initial.ㄹ, Medial.ㅗ)
        ),
        HangeulTextElement.Word.fromSyllabicBlocks(
          TwoLetter(Initial.ㅇ, Medial.ㅣ),
          TwoLetter(Initial.ㄹ, Medial.ㅜ),
          TwoLetter(Initial.ㅇ, Medial.ㅓ),
          TwoLetter(Initial.ㅈ, Medial.ㅕ)
        ),
        HangeulTextElement.Word.fromSyllabicBlocks(
          ThreeLetter(Initial.ㅇ, Medial.ㅣ, Final.ㅆ),
          TwoLetter(Initial.ㄷ, Medial.ㅏ)
        ),
        HangeulTextElement.Punctuation.unvalidatedFrom(".")
      )

      HangeulText.parser.parse(input).toEither must beRight(expectedOutput)
    }

  }

}
