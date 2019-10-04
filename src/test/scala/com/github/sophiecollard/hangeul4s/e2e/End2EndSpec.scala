package com.github.sophiecollard.hangeul4s.e2e

import cats.instances.vector._
import com.github.sophiecollard.hangeul4s.model.hangeul.HangeulTextElement
import com.github.sophiecollard.hangeul4s.model.hangeul.HangeulTextElement.vectorTokenizer
import com.github.sophiecollard.hangeul4s.model.romanization.RomanizedTextElement
import com.github.sophiecollard.hangeul4s.model.romanization.RomanizedTextElement.vectorUntokenizer
import com.github.sophiecollard.hangeul4s.parsing.implicits._
import com.github.sophiecollard.hangeul4s.parsing.syntax._
import com.github.sophiecollard.hangeul4s.transliteration.Transliterator
import com.github.sophiecollard.hangeul4s.transliteration.hangeul.HangeulRomanizer
import com.github.sophiecollard.hangeul4s.transliteration.implicits._
import com.github.sophiecollard.hangeul4s.transliteration.syntax._
import org.specs2.mutable.Specification

class End2EndSpec extends Specification {

  "This library" should {

    implicit val hangeulRomanizer: Transliterator[HangeulTextElement, RomanizedTextElement] = HangeulRomanizer

    "tokenize, parse, transliterate, unparse, untokenize" in {
      val input = "안녕하세요"

      val output = for {
        parsed <- input.parseF[Vector, HangeulTextElement]
        transliterated <- parsed.transliterateF[Vector, RomanizedTextElement]
      } yield transliterated.unparse

      output must beRight("annyeonghaseyo")
    }

  }

}
