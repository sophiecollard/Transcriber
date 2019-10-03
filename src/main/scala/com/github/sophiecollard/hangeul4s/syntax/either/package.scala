package com.github.sophiecollard.hangeul4s.syntax

import cats.data.NonEmptyVector
import cats.data.Validated.{Invalid, Valid}
import com.github.sophiecollard.hangeul4s.util.types.ValidatedNev

package object either {

  implicit class EitherOps[E, A](either: Either[E, A]) {
    def toValidatedNev: ValidatedNev[E, A] =
      either match {
        case Left(e)  => Invalid(NonEmptyVector.one(e))
        case Right(a) => Valid(a)
      }
  }

}
