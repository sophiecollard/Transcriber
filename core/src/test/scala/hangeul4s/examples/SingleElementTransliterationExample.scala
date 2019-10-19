package hangeul4s.examples

import cats.syntax.either._ // required to avoid ambiguous implicit conversion in Scala 2.11
import hangeul4s.error.{Hangeul4sError, ParsingFailure}
import hangeul4s.implicits._
import hangeul4s.model.hangeul.HangeulTextElement
import hangeul4s.model.romanization.RomanizedTextElement
import org.specs2.mutable.Specification

class SingleElementTransliterationExample extends Specification {

  "This library" should {

    "parse, transliterate and unparse a single Hangeul text element" in {
      val result = for {
        parsed <- "안녕하세요".parseTo[HangeulTextElement]
        transliterated <- parsed.transliterateTo[RomanizedTextElement]
      } yield transliterated.unparseTo[String]

      result must beRight("annyeonghaseyo")
    }

    "fail to process an empty string as a single Hangeul text element" in {
      val result = for {
        parsed <- "".parseTo[HangeulTextElement]
        transliterated <- parsed.transliterateTo[RomanizedTextElement]
      } yield transliterated.unparseTo[String]

      result must beLeft[Hangeul4sError](ParsingFailure.FailedToMatchRegex("", "[^\uAC00-\uD7AF]+".r))
    }

  }

}
