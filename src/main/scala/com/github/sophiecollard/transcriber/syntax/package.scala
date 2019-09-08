package com.github.sophiecollard.transcriber

import com.github.sophiecollard.transcriber.util.Monoid

package object syntax {

  implicit class RichTraversable[A](value: Traversable[A]) {
    def combineAll(implicit ev: Monoid[A]): A =
      value.foldRight(ev.empty)(ev.combine)
  }

}
