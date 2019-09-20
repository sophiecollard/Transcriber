package com.github.sophiecollard.transliterator

import com.github.sophiecollard.transliterator.syntax._
import com.github.sophiecollard.transliterator.util.typeclasses.{Applicative, Monoid, Semigroupal, Traverse}

package object instances {

  implicit def eitherApplicative[E]: Applicative[Either[E, ?]] =
    new Applicative[Either[E, ?]] {
      override def pure[A](a: A): Either[E, A] =
        Right(a)

      override def map[A, B](fa: Either[E, A], f: A => B): Either[E, B] =
        fa.map(f)

      override def product[A, B](fa: Either[E, A], fb: Either[E, B]): Either[E, (A, B)] =
        fa.flatMap(a => fb.map(b => (a, b)))
    }

  implicit def vectorMonoid[A]: Monoid[Vector[A]] =
    new Monoid[Vector[A]] {
      override def empty: Vector[A] =
        Vector.empty[A]

      override def combine(x: Vector[A], y: Vector[A]): Vector[A] =
        x ++ y
    }

  implicit val vectorTraverse: Traverse[Vector] =
    new Traverse[Vector] {
      override def traverse[G[_]: Applicative, A, B](fa: Vector[A])(f: A => G[B]): G[Vector[B]] =
        fa.foldLeft(Vector.empty[B].pure[G]) { (gbs, a) =>
          Semigroupal.product(gbs, f(a)).map { case (acc, b) => acc :+ b }
        }
    }

}
