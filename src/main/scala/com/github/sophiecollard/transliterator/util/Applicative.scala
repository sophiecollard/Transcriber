package com.github.sophiecollard.transliterator.util

trait Applicative[F[_]] extends Functor[F] with Semigroupal[F] {

  def pure[A](value: A): F[A]

}

object Applicative {

  def pure[F[_], A](value: A)(implicit ev: Applicative[F]): F[A] =
    ev.pure(value)

}
