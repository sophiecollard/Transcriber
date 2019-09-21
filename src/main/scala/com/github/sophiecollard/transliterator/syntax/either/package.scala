package com.github.sophiecollard.transliterator.syntax

package object either {

  implicit class EitherOps[E, A](either: Either[E, A]) {
    def leftMap[B](f: E => B): Either[B, A] =
      either match {
        case Left(e)  => Left(f(e))
        case Right(a) => Right(a)
      }

    def bimap[B, C](f: E => B, g: A => C): Either[B, C] =
      either match {
        case Left(e)  => Left(f(e))
        case Right(a) => Right(g(a))
      }
  }

}
