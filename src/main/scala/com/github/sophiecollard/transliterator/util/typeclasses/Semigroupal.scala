package com.github.sophiecollard.transliterator.util.typeclasses

trait Semigroupal[F[_]] {

  def product[A, B](fa: F[A], fb: F[B]): F[(A, B)]

}

object Semigroupal {

  def product[F[_], A, B](fa: F[A], fb: F[B])(implicit ev: Semigroupal[F]): F[(A, B)] =
    ev.product(fa, fb)

}
