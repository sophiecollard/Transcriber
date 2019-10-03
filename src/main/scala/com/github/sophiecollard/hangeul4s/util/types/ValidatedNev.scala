package com.github.sophiecollard.hangeul4s.util.types

import cats.{Applicative, Apply, Functor, Semigroupal}
import cats.data.NonEmptyVector

sealed abstract class ValidatedNev[+E, +A] {

  import ValidatedNev._

  def map[B](f: A => B): ValidatedNev[E, B] =
    this match {
      case Valid(a)    => Valid(f(a))
      case Invalid(es) => Invalid(es)
    }

  def flatMap[EE >: E, B](f: A => ValidatedNev[EE, B]): ValidatedNev[EE, B] =
    this match {
      case Valid(a)    => f(a)
      case Invalid(es) => Invalid(es)
    }

  def product[EE >: E, B](fb: ValidatedNev[EE, B]): ValidatedNev[EE, (A, B)] =
    (this, fb) match {
      case (Valid(a), Valid(b))         => Valid((a, b))
      case (Invalid(es), Valid(_))      => Invalid(es)
      case (Valid(_), Invalid(es))      => Invalid(es)
      case (Invalid(es1), Invalid(es2)) => Invalid(es1 ++: es2)
    }

  def ap[EE >: E, B](ff: ValidatedNev[EE, A => B]): ValidatedNev[EE, B] =
    (this, ff) match {
      case (Valid(a), Valid(f))         => Valid(f(a))
      case (Invalid(es), Valid(_))      => Invalid(es)
      case (Valid(_), Invalid(es))      => Invalid(es)
      case (Invalid(es1), Invalid(es2)) => Invalid(es1 ++: es2)
    }

  def toEither: Either[NonEmptyVector[E], A] = this match {
    case Invalid(es) => Left(es)
    case Valid(a)    => Right(a)
  }

}

object ValidatedNev {

  final case class Valid[A](value: A) extends ValidatedNev[Nothing, A]

  final case class Invalid[E](errors: NonEmptyVector[E]) extends ValidatedNev[E, Nothing]

  object Invalid {
    def one[E, A](error: E): ValidatedNev[E, A] =
      Invalid(NonEmptyVector.one(error))
  }

  def valid[E, A](value: A): ValidatedNev[E, A] =
    Valid(value)

  def invalid[E, A](errors: NonEmptyVector[E]): ValidatedNev[E, A] =
    Invalid(errors)

  implicit def functor[E]: Functor[ValidatedNev[E, ?]] =
    new Functor[ValidatedNev[E, ?]] {
      def map[A, B](fa: ValidatedNev[E, A])(f: A => B): ValidatedNev[E, B] =
        fa.map(f)
    }

  implicit def semigroupal[E]: Semigroupal[ValidatedNev[E, ?]] =
    new Semigroupal[ValidatedNev[E, ?]] {
      def product[A, B](fa: ValidatedNev[E, A], fb: ValidatedNev[E, B]): ValidatedNev[E, (A, B)] =
        fa.product(fb)
    }

  implicit def apply_[E]: Apply[ValidatedNev[E, ?]] =
    new Apply[ValidatedNev[E, ?]] {
      def ap[A, B](ff: ValidatedNev[E, A => B])(fa: ValidatedNev[E, A]): ValidatedNev[E, B] =
        fa.ap(ff)

      def map[A, B](fa: ValidatedNev[E, A])(f: A => B): ValidatedNev[E, B] =
        functor.map(fa)(f)
    }

  implicit def applicative[E]: Applicative[ValidatedNev[E, ?]] =
    new Applicative[ValidatedNev[E, ?]] {
      def pure[A](value: A): ValidatedNev[E, A] =
        Valid(value)

      def ap[A, B](ff: ValidatedNev[E, A => B])(fa: ValidatedNev[E, A]): ValidatedNev[E, B] =
        apply_.ap(ff)(fa)
    }

}
