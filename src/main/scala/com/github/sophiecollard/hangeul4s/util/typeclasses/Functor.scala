package com.github.sophiecollard.hangeul4s.util.typeclasses

trait Functor[F[_]] {

  def map[A, B](f: A => B)(fa: F[A]): F[B]

}

object Functor {

  def map[F[_], A, B](f: A => B)(fa: F[A])(implicit ev: Functor[F]): F[B] =
    ev.map(f)(fa)

}
