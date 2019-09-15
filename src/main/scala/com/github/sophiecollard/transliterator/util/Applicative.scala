package com.github.sophiecollard.transliterator.util

trait Applicative[F[_]] extends Functor[F] with Semigroupal[F] {

  def pure[A](a: A): F[A]

}

object Applicative {

  def pure[F[_], A](a: A)(implicit ev: Applicative[F]): F[A] =
    ev.pure(a)

}
