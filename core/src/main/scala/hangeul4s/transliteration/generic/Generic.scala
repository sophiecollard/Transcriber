package hangeul4s.transliteration.generic

import cats.Traverse
import cats.instances.either._
import cats.syntax.functor._
import cats.syntax.traverse._
import hangeul4s.transliteration.Transliterator

trait Generic {

  implicit def transliteratorF[F[_]: Traverse, A, B](
    implicit transliterator: Transliterator[A, B]
  ): Transliterator[F[A], F[B]] =
    Transliterator.instance { input =>
      input.map(transliterator.transliterate).sequence
    }

}
