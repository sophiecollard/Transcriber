package com.github.sophiecollard.hangeul4s.syntax

import com.github.sophiecollard.hangeul4s.util.types.ValidatedNev
import com.github.sophiecollard.hangeul4s.util.types.ValidatedNev.{Invalid, Valid}

package object either {

  implicit class EitherOps[E, A](either: Either[E, A]) {
    def toValidatedNev: ValidatedNev[E, A] =
      either match {
        case Left(e)  => Invalid.one(e)
        case Right(a) => Valid(a)
      }
  }

}
