package hangeul4s.transliteration.syntax

import hangeul4s.transliteration.{TransliterationResult, Transliterator}

trait Syntax {

  implicit class TransliterationOps[I](input: I) {
    def transliterateTo[O](implicit transliterator: Transliterator[I, O]): TransliterationResult[O] =
      transliterator.transliterate(input)
  }

  implicit class TransliterationFOps[F[_], I](input: F[I]) {
    def transliterateToF[G[_], O](implicit transliteratorF: Transliterator[F[I], G[O]]): TransliterationResult[G[O]] =
      transliteratorF.transliterate(input)
  }

}
