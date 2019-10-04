package com.github.sophiecollard.hangeul4s.transliteration

import cats.Traverse
import cats.instances.either._
import cats.syntax.functor._
import cats.syntax.traverse._

package object implicits {

  implicit def transliteratorF[F[_]: Traverse, I, O](
    implicit transliterator: Transliterator[I, O]
  ): Transliterator[F[I], F[O]] =
    Transliterator.instance { input =>
      input.map(transliterator.transliterate).sequence
    }

}
