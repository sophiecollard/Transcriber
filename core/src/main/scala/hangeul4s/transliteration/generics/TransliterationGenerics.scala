package hangeul4s.transliteration.generics

import cats.Traverse
import cats.instances.either._
import cats.syntax.functor._
import cats.syntax.traverse._
import hangeul4s.transliteration.Transliterator

trait TransliterationGenerics {

  implicit def transliteratorF[F[_]: Traverse, A, B](
    implicit transliterator: Transliterator[A, B]
  ): Transliterator[F[A], F[B]] =
    Transliterator.instance { input =>
      input.traverse(transliterator.transliterate)
    }

}
