package com.github.sophiecollard.hangeul4s.util.types

import com.github.sophiecollard.hangeul4s.syntax.functor._
import com.github.sophiecollard.hangeul4s.util.typeclasses.{Applicative, Semigroupal, Traverse}

import scala.util.Try

final case class NonEmptyVector[+A](head: A, tail: Vector[A]) {

  def map[B](f: A => B): NonEmptyVector[B] =
    NonEmptyVector(f(head), tail.map(f))

  def foldLeft[B](z: B)(f: (B, A) => B): B = {
    @scala.annotation.tailrec
    def loop(rem: Vector[A], acc: B): B = {
      if (rem.isEmpty) acc
      else loop(rem.drop(1), f(acc, rem(0)))
    }
    loop(tail, z)
  }

  def toVector: Vector[A] = head +: tail

  def prepend[AA >: A](value: AA): NonEmptyVector[AA] =
    NonEmptyVector(value, head +: tail)

  def append[AA >: A](value: AA): NonEmptyVector[AA] =
    NonEmptyVector(head, tail :+ value)

  def concat[AA >: A](vector: Vector[AA]): NonEmptyVector[AA] =
    NonEmptyVector(head, tail ++ vector)

  def concat[AA >: A](nev: NonEmptyVector[AA]): NonEmptyVector[AA] =
    concat(nev.toVector)

}

object NonEmptyVector {

  def one[A](value: A): NonEmptyVector[A] =
    NonEmptyVector(value, Vector.empty[A])

  def of[A](head: A, tail: A*): NonEmptyVector[A] =
    NonEmptyVector(head, tail.toVector)

  def fromVector[A](vector: Vector[A]): Option[NonEmptyVector[A]] =
    Try(NonEmptyVector(vector(0), vector.drop(1))).toOption

  implicit val traverse: Traverse[NonEmptyVector] =
    new Traverse[NonEmptyVector] {
      override def traverse[G[_]: Applicative, A, B](fa: NonEmptyVector[A])(f: A => G[B]): G[NonEmptyVector[B]] =
        fa.foldLeft(f(fa.head).map(NonEmptyVector.one)) { (gbs, a) =>
          Semigroupal.product(gbs, f(a)).map { case (bs, b) => bs append b }
        }
    }

}
