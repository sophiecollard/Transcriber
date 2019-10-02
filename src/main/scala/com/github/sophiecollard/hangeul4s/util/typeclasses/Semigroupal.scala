package com.github.sophiecollard.hangeul4s.util.typeclasses

trait Semigroupal[F[_]] {

  def product[A, B](fa: F[A], fb: F[B]): F[(A, B)]

}

object Semigroupal {

  def apply[F[_]](implicit ev: Semigroupal[F]): Semigroupal[F] =
    ev

  def product[F[_], A, B](fa: F[A], fb: F[B])(implicit ev: Semigroupal[F]): F[(A, B)] =
    ev.product(fa, fb)

}
