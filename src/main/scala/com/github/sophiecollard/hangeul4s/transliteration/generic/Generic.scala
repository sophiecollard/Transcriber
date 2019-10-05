package com.github.sophiecollard.hangeul4s.transliteration.generic

import cats.Traverse
import cats.instances.either._
import cats.syntax.functor._
import cats.syntax.traverse._
import com.github.sophiecollard.hangeul4s.transliteration.Transliterator

trait Generic {

  implicit def transliteratorF[F[_]: Traverse, I, O](
    implicit transliterator: Transliterator[I, O]
  ): Transliterator[F[I], F[O]] =
    Transliterator.instance { input =>
      input.map(transliterator.transliterate).sequence
    }

}
