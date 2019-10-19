package hangeul4s.parsing

import hangeul4s.error.ParsingFailure
import org.specs2.mutable.Specification

class ParsingResultSpec extends Specification {

  "ParsingResult#success" should {

    "return a ParsingResult instance" in {
      ParsingResult.success(1917) must beRight(1917)
    }

  }

  "ParsingResult#failure" should {

    "return a ParsingResult instance" in {
      ParsingResult.failure[Int](ParsingFailure.EmptyInput) must beLeft[ParsingFailure]
    }

  }

}
