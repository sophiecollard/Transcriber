package hangeul4s.syntax.either

import cats.data.NonEmptyVector
import cats.data.Validated.{Invalid, Valid}
import hangeul4s.util.ValidatedNev

trait EitherSyntax {

  implicit class EitherOps[E, A](either: Either[E, A]) {
    def toValidatedNev: ValidatedNev[E, A] =
      either match {
        case Left(e)  => Invalid(NonEmptyVector.one(e))
        case Right(a) => Valid(a)
      }
  }

}
