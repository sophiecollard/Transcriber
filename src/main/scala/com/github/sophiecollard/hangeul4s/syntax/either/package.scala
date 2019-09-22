package com.github.sophiecollard.hangeul4s.syntax

import com.github.sophiecollard.hangeul4s.util.types.ValidatedNev
import com.github.sophiecollard.hangeul4s.util.types.ValidatedNev.{Invalid, Valid}

package object either {

  implicit class EitherConstructors[A](value: A) {
    def left[AA >: A, B]: Either[AA, B] =
      Left(value)

    def right[B, AA >: A]: Either[B, AA] =
      Right(value)
  }

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

    def toValidatedNev: ValidatedNev[E, A] =
      either match {
        case Left(e)  => Invalid.one(e)
        case Right(a) => Valid(a)
      }
  }

}
