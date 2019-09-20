package com.github.sophiecollard.transliterator.syntax

import com.github.sophiecollard.transliterator.util.typeclasses.{Applicative, Traverse}

package object traverse {

  implicit class TraverseOps[F[_], A](fa: F[A])(implicit ev: Traverse[F]) {
    def traverse[G[_]: Applicative, B](f: A => G[B]): G[F[B]] =
      ev.traverse(fa)(f)
  }

}
