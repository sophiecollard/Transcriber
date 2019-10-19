package hangeul4s.parsing

import cats.data.NonEmptyVector
import hangeul4s.error.ParsingFailure
import org.specs2.mutable.Specification

class AccumulativeParsingResultSpec extends Specification {

  "AccumulativeParsingResult#success" should {

    "return an AccumulativeParsingResult instance" in {
      AccumulativeParsingResult.success(1917).toEither must beRight(1917)
    }

  }

  "AccumulativeParsingResult#failure" should {

    "return an AccumulativeParsingResult instance" in {
      AccumulativeParsingResult
        .failure(ParsingFailure.EmptyInput, ParsingFailure.FailedToMatchRegex("1917", "\\D+".r))
        .toEither must beLeft[NonEmptyVector[ParsingFailure]]
    }

  }

}
