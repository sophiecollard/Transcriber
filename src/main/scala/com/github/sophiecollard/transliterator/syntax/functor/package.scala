package com.github.sophiecollard.transliterator.syntax

import com.github.sophiecollard.transliterator.util.typeclasses.Functor

package object functor {

  implicit class FunctorOps[F[_], A](fa: F[A])(implicit ev: Functor[F]) {
    def map[B](f: A => B): F[B] =
      ev.map(fa, f)
  }

}
