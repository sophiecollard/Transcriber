package com.github.sophiecollard.transliterator.util

trait Functor[F[_]] {

  def map[A, B](fa: F[A], f: A => B): F[B]

}

object Functor {

  def map[F[_], A, B](fa: F[A], f: A => B)(implicit ev: Functor[F]): F[B] =
    ev.map(fa, f)

}
