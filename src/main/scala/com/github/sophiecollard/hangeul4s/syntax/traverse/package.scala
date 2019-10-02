package com.github.sophiecollard.hangeul4s.syntax

import com.github.sophiecollard.hangeul4s.util.typeclasses.{Applicative, Traverse}

package object traverse {

  implicit class TraverseOps[F[_], A](fa: F[A])(implicit ev: Traverse[F]) {
    def traverse[G[_]: Applicative, B](f: A => G[B]): G[F[B]] =
      ev.traverse(fa)(f)
  }

  implicit class SequenceOps[F[_], G[_], A](fga: F[G[A]])(implicit ev1: Traverse[F], ev2: Applicative[G]) {
    def sequence: G[F[A]] =
      ev1.sequence[G, A](fga)
  }

}
