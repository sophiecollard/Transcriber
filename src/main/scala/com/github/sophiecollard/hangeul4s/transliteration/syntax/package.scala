package com.github.sophiecollard.hangeul4s.transliteration

package object syntax {

  implicit class TransliterationOps[I](input: I) {
    def transliterate[O](implicit transliterator: Transliterator[I, O]): TransliterationResult[O] =
      transliterator.transliterate(input)
  }

}
