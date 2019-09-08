package transliterator4s.transliteration.syntax

import transliterator4s.transliteration.{TransliterationResult, Transliterator}

trait Syntax {

  implicit class TransliterationOps[I](input: I) {
    def transliterate[O](implicit transliterator: Transliterator[I, O]): TransliterationResult[O] =
      transliterator.transliterate(input)
  }

  implicit class TransliterationFOps[F[_], I](input: F[I]) {
    def transliterateF[G[_], O](implicit transliteratorF: Transliterator[F[I], G[O]]): TransliterationResult[G[O]] =
      transliteratorF.transliterate(input)
  }

}
