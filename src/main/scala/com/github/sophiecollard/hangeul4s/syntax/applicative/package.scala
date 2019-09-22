package com.github.sophiecollard.hangeul4s.syntax

import com.github.sophiecollard.hangeul4s.util.typeclasses.Applicative

package object applicative {

  implicit class ApplicativeOps[A](value: A) {
    def pure[F[_]](implicit ev: Applicative[F]): F[A] =
      ev.pure(value)
  }

}
