package com.github.sophiecollard.hangeul4s.instances

import com.github.sophiecollard.hangeul4s.util.typeclasses.{Applicative, Functor, Semigroupal}

package object either {

  implicit def eitherFunctor[E]: Functor[Either[E, ?]] =
    new Functor[Either[E, ?]] {
      override def map[A, B](f: A => B)(fa: Either[E, A]): Either[E, B] =
        fa.map(f)
    }

  implicit def eitherSemigroupal[E]: Semigroupal[Either[E, ?]] =
    new Semigroupal[Either[E, ?]] {
      override def product[A, B](fa: Either[E, A], fb: Either[E, B]): Either[E, (A, B)] =
        fa.flatMap(a => fb.map(b => (a, b)))
    }

  implicit def eitherApplicative[E]: Applicative[Either[E, ?]] =
    new Applicative[Either[E, ?]] {
      override def pure[A](a: A): Either[E, A] =
        Right(a)

      override def map[A, B](f: A => B)(fa: Either[E, A]): Either[E, B] =
        eitherFunctor.map(f)(fa)

      override def product[A, B](fa: Either[E, A], fb: Either[E, B]): Either[E, (A, B)] =
        eitherSemigroupal.product(fa, fb)
    }

}
