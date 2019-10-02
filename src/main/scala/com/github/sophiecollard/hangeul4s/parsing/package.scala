package com.github.sophiecollard.hangeul4s

import cats.data.NonEmptyVector
import cats.data.Validated.{Invalid, Valid}
import com.github.sophiecollard.hangeul4s.error.ParsingError
import com.github.sophiecollard.hangeul4s.util.types.ValidatedNev

package object parsing {

  type FailFastParsingResult[A] = Either[ParsingError, A]

  object FailFastParsingResult {
    def success[A](value: A): FailFastParsingResult[A] =
      Right(value)

    def failure[A](error: ParsingError): FailFastParsingResult[A] =
      Left(error)
  }

  type AccumulativeParsingResult[A] = ValidatedNev[ParsingError, A]

  object AccumulativeParsingResult {
    def success[A](value: A): AccumulativeParsingResult[A] =
      Valid(value)

    def failure[A](error: ParsingError, errors: ParsingError*): AccumulativeParsingResult[A] =
      Invalid(NonEmptyVector(error, errors.toVector))
  }

}
