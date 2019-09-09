package com.github.sophiecollard.transcriber

import com.github.sophiecollard.transcriber.util.Monoid

package object instances {

  implicit def vectorMonoid[A]: Monoid[Vector[A]] =
    new Monoid[Vector[A]] {
      override def empty: Vector[A] =
        Vector.empty[A]

      override def combine(x: Vector[A], y: Vector[A]): Vector[A] =
        x ++ y
    }

}
