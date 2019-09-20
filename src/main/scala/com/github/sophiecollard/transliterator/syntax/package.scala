package com.github.sophiecollard.transliterator

import com.github.sophiecollard.transliterator.util.{Applicative, Functor, Traverse}

import scala.util.Try

package object syntax {

  implicit class FunctorOps[F[_], A](fa: F[A])(implicit ev: Functor[F]) {
    def map[B](f: A => B): F[B] =
      ev.map(fa, f)
  }

  implicit class ApplicativeOps[A](value: A) {
    def pure[F[_]](implicit ev: Applicative[F]): F[A] =
      ev.pure(value)
  }

  implicit class TraverseOps[F[_], A](fa: F[A])(implicit ev: Traverse[F]) {
    def traverse[G[_]: Applicative, B](f: A => G[B]): G[F[B]] =
      ev.traverse(fa)(f)
  }

  implicit class RichString(string: String) {
    def safeCharAt(index: Int): Option[Char] =
      Try(string.charAt(index)).toOption
  }

  implicit class RichVector[A](vector: Vector[A]) {

    private type Neighbors[A] = (Option[A], A, Option[A])

    def zipWithNeighbors: Vector[Neighbors[A]] = {
      @scala.annotation.tailrec
      def loop(rem: List[A], prev: Option[A], acc: Vector[Neighbors[A]]): Vector[Neighbors[A]] =
        rem match {
          case Nil =>
            acc
          case fst :: Nil =>
            acc :+ (prev, fst, None)
          case fst :: snd :: tail =>
            loop(snd :: tail, Some(fst), acc :+ (prev, fst, Some(snd)))
        }

      loop(vector.toList, prev = None, acc = Vector.empty)
    }

  }

}
