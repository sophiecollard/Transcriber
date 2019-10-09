package hangeul4s

import cats.data.Validated.Valid
import hangeul4s.error.ParsingFailure
import hangeul4s.util.ValidatedNev

package object parsing {

  type ParsingResult[A] = Either[ParsingFailure, A]

  object ParsingResult {
    def success[A](value: A): ParsingResult[A] =
      Right(value)
  }

  type AccumulativeParsingResult[A] = ValidatedNev[ParsingFailure, A]

  object AccumulativeParsingResult {
    def success[A](value: A): AccumulativeParsingResult[A] =
      Valid(value)
  }

}
