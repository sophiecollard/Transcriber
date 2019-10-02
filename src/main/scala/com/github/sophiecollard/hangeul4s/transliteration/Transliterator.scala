package com.github.sophiecollard.hangeul4s.transliteration

trait Transliterator[I, O] {

  def transliterate(input: I): TransliterationResult[O]

}
