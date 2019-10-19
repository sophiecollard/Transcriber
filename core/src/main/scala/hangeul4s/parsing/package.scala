package hangeul4s

import cats.data.NonEmptyVector
import cats.data.Validated.{Invalid, Valid}
import hangeul4s.error.ParsingFailure
import hangeul4s.util.ValidatedNev

package object parsing {

  type ParsingResult[+A] = Either[ParsingFailure, A]

  object ParsingResult {
    def success[A](value: A): ParsingResult[A] =
      Right(value)

    def failure[A](e: ParsingFailure): ParsingResult[A] =
      Left(e)
  }

  type AccumulativeParsingResult[+A] = ValidatedNev[ParsingFailure, A]

  object AccumulativeParsingResult {
    def success[A](value: A): AccumulativeParsingResult[A] =
      Valid(value)

    def failure[A](e: ParsingFailure, es: ParsingFailure*): AccumulativeParsingResult[A] =
      Invalid(NonEmptyVector(e, es.toVector))
  }

}
