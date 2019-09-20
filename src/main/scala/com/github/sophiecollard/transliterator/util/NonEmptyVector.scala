package com.github.sophiecollard.transliterator.util

import scala.util.Try

final case class NonEmptyVector[+A](head: A, tail: Vector[A]) {

  def toVector: Vector[A] = head +: tail

  def appendVector[AA >: A](vector: Vector[AA]): NonEmptyVector[AA] =
    NonEmptyVector(head, tail ++ vector)

  def append[AA >: A](nev: NonEmptyVector[AA]): NonEmptyVector[AA] =
    appendVector(nev.toVector)

}

object NonEmptyVector {

  def fromVector[A](vector: Vector[A]): Option[NonEmptyVector[A]] =
    Try(NonEmptyVector(vector(0), vector.drop(1))).toOption

}
