package transliterator4s.syntax

import cats.data.NonEmptyVector
import cats.data.Validated.{Invalid, Valid}
import transliterator4s.util.ValidatedNev

package object option {

  implicit class OptionOps[A](option: Option[A]) {
    def toValid[E](error: E): ValidatedNev[E, A] =
      option match {
        case Some(a) => Valid(a)
        case None    => Invalid(NonEmptyVector.one(error))
      }

    def toInvalid[B](value: B): ValidatedNev[A, B] =
      option match {
        case Some(error) => Invalid(NonEmptyVector.one(error))
        case None        => Valid(value)
      }
  }

}
