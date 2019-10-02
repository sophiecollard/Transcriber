package com.github.sophiecollard.hangeul4s.util.typeclasses

trait Applicative[F[_]] extends Apply[F] {

  def pure[A](value: A): F[A]

  final def map[A, B](f: A => B)(fa: F[A]): F[B] =
    ap(pure(f))(fa)

}

object Applicative {

  def apply[F[_]](implicit ev: Applicative[F]): Applicative[F] =
    ev

  def pure[F[_], A](value: A)(implicit ev: Applicative[F]): F[A] =
    ev.pure(value)

  def ap[F[_], A, B](ff: F[A => B])(fa: F[A])(implicit ev: Applicative[F]): F[B] =
    ev.ap(ff)(fa)

  def map[F[_], A, B](f: A => B)(fa: F[A])(implicit ev: Applicative[F]): F[B] =
    ev.map(f)(fa)

  def product[F[_], A, B](fa: F[A], fb: F[B])(implicit ev: Applicative[F]): F[(A, B)] =
    ev.product(fa, fb)

}
