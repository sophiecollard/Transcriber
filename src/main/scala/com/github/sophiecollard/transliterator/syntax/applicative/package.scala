package com.github.sophiecollard.transliterator.syntax

import com.github.sophiecollard.transliterator.util.typeclasses.Applicative

package object applicative {

  implicit class ApplicativeOps[A](value: A) {
    def pure[F[_]](implicit ev: Applicative[F]): F[A] =
      ev.pure(value)
  }

}
