package com.github.sophiecollard.transliterator.util.types

import com.github.sophiecollard.transliterator.util.typeclasses.{Applicative, Functor, Semigroupal}

sealed abstract class ValidatedNev[+E, +A] {

  def map[B](f: A => B): ValidatedNev[E, B] =
    ValidatedNev.functor.map(this, f)

  def product[EE >: E, B](fb: ValidatedNev[EE, B]): ValidatedNev[EE, (A, B)] =
    ValidatedNev.semigroupal.product(this, fb)

}

object ValidatedNev {

  final case class Valid[A](value: A) extends ValidatedNev[Nothing, A]

  final case class Invalid[E](errors: NonEmptyVector[E]) extends ValidatedNev[E, Nothing]

  def valid[E, A](value: A): ValidatedNev[E, A] =
    Valid(value)

  def invalid[E, A](errors: NonEmptyVector[E]): ValidatedNev[E, A] =
    Invalid(errors)

  implicit def functor[E]: Functor[ValidatedNev[E, ?]] =
    new Functor[ValidatedNev[E, ?]] {
      override def map[A, B](fa: ValidatedNev[E, A], f: A => B): ValidatedNev[E, B] =
        fa match {
          case Valid(a)    => Valid(f(a))
          case Invalid(es) => Invalid(es)
        }
    }

  implicit def semigroupal[E]: Semigroupal[ValidatedNev[E, ?]] =
    new Semigroupal[ValidatedNev[E, ?]] {
      override def product[A, B](fa: ValidatedNev[E, A], fb: ValidatedNev[E, B]): ValidatedNev[E, (A, B)] =
        (fa, fb) match {
          case (Valid(a), Valid(b)) =>
            Valid((a, b))
          case (Invalid(es1), Invalid(es2)) =>
            Invalid(es1 append es2)
          case (Invalid(es), Valid(_)) =>
            Invalid(es)
          case (Valid(_), Invalid(es)) =>
            Invalid(es)
        }
    }

  implicit def applicative[E]: Applicative[ValidatedNev[E, ?]] =
    new Applicative[ValidatedNev[E, ?]] {
      override def pure[A](value: A): ValidatedNev[E, A] =
        Valid(value)

      override def map[A, B](fa: ValidatedNev[E, A], f: A => B): ValidatedNev[E, B] =
        functor.map(fa, f)

      override def product[A, B](fa: ValidatedNev[E, A], fb: ValidatedNev[E, B]): ValidatedNev[E, (A, B)] =
        semigroupal.product(fa, fb)
    }

}
