package hangeul4s.syntax

import cats.data.NonEmptyVector
import cats.data.Validated.{Invalid, Valid}
import hangeul4s.util.ValidatedNev

package object option {

  implicit class OptionOps[A](option: Option[A]) {
    def toValidatedNev[E](error: E): ValidatedNev[E, A] =
      option match {
        case Some(a) => Valid(a)
        case None    => Invalid(NonEmptyVector.one(error))
      }
  }

}
