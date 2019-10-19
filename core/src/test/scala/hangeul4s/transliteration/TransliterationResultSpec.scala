package hangeul4s.transliteration

import hangeul4s.error.TransliterationFailure
import org.specs2.mutable.Specification

class TransliterationResultSpec extends Specification {

  "TransliterationResult#success" should {

    "return a TransliterationResult instance" in {
      TransliterationResult.success("안녕하세요") must beRight("안녕하세요")
    }

  }

  "TransliterationResult#failure" should {

    "return a TransliterationResult instance" in {
      TransliterationResult.failure[String](TransliterationFailure.EmptyResult) must beLeft[TransliterationFailure]
    }

  }

}
