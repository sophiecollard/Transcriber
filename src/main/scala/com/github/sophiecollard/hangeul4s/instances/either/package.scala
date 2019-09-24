package com.github.sophiecollard.hangeul4s.instances

import com.github.sophiecollard.hangeul4s.util.typeclasses.{Applicative, Apply, Functor, Semigroupal}

package object either {

  implicit def eitherFunctor[E]: Functor[Either[E, ?]] =
    new Functor[Either[E, ?]] {
      def map[A, B](f: A => B)(fa: Either[E, A]): Either[E, B] =
        fa.map(f)
    }

  implicit def eitherSemigroupal[E]: Semigroupal[Either[E, ?]] =
    new Semigroupal[Either[E, ?]] {
      def product[A, B](fa: Either[E, A], fb: Either[E, B]): Either[E, (A, B)] =
        fa.flatMap(a => fb.map(b => (a, b)))
    }

  implicit def eitherApply[E]: Apply[Either[E, ?]] =
    new Apply[Either[E, ?]] {
      def ap[A, B](ff: Either[E, A => B])(fa: Either[E, A]): Either[E, B] =
        ff.flatMap(f => fa.map(a => f(a)))

      def map[A, B](f: A => B)(fa: Either[E, A]): Either[E, B] =
        eitherFunctor.map(f)(fa)
    }

  implicit def eitherApplicative[E]: Applicative[Either[E, ?]] =
    new Applicative[Either[E, ?]] {
      def pure[A](a: A): Either[E, A] =
        Right(a)

      def ap[A, B](ff: Either[E, A => B])(fa: Either[E, A]): Either[E, B] =
        eitherApply.ap(ff)(fa)
    }

}
