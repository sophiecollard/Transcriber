package hangeul4s.transliteration.syntax

import hangeul4s.transliteration.{TransliterationResult, Transliterator}

trait TransliterationSyntax {

  implicit class TransliterationOps[A](input: A) {
    def transliterateTo[B](implicit transliterator: Transliterator[A, B]): TransliterationResult[B] =
      transliterator.transliterate(input)
  }

  implicit class TransliterationFOps[F[_], A](input: F[A]) {
    def transliterateToF[G[_], B](implicit transliteratorF: Transliterator[F[A], G[B]]): TransliterationResult[G[B]] =
      transliteratorF.transliterate(input)
  }

}
