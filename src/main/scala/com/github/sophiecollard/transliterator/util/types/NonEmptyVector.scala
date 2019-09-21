package com.github.sophiecollard.transliterator.util.types

import scala.util.Try

final case class NonEmptyVector[+A](head: A, tail: Vector[A]) {

  def toVector: Vector[A] = head +: tail

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

}
