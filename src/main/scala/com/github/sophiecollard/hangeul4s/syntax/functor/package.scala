package com.github.sophiecollard.hangeul4s.syntax

import com.github.sophiecollard.hangeul4s.util.typeclasses.Functor

package object functor {

  implicit class FunctorOps[F[_], A](fa: F[A])(implicit ev: Functor[F]) {
    def map[B](f: A => B): F[B] =
      ev.map(f)(fa)
  }

}
