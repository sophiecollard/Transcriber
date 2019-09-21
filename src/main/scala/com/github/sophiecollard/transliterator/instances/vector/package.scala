package com.github.sophiecollard.transliterator.instances

import com.github.sophiecollard.transliterator.util.typeclasses.{Applicative, Monoid, Semigroupal, Traverse}
import com.github.sophiecollard.transliterator.syntax.applicative._
import com.github.sophiecollard.transliterator.syntax.functor._

package object vector {

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
          Semigroupal.product(gbs, f(a)).map { case (bs, b) => bs :+ b }
        }
    }

}
