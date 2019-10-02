package com.github.sophiecollard.hangeul4s.util.typeclasses

trait Apply[F[_]] extends Functor[F] with Semigroupal[F] {

  def ap[A, B](ff: F[A => B])(fa: F[A]): F[B]

  final def product[A, B](fa: F[A], fb: F[B]): F[(A, B)] =
    ap(map[A, B => (A, B)](a => (a, _))(fa))(fb)

}

object Apply {

  def apply[F[_]](implicit ev: Apply[F]): Apply[F] =
    ev

  def ap[F[_], A, B](ff: F[A => B])(fa: F[A])(implicit ev: Apply[F]): F[B] =
    ev.ap(ff)(fa)

  def map[F[_], A, B](f: A => B)(fa: F[A])(implicit ev: Apply[F]): F[B] =
    ev.map(f)(fa)

  def product[F[_], A, B](fa: F[A], fb: F[B])(implicit ev: Apply[F]): F[(A, B)] =
    ev.product(fa, fb)

}
