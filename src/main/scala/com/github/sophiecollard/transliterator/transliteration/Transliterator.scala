package com.github.sophiecollard.transliterator.transliteration

import com.github.sophiecollard.transliterator.error.TransliterationError

trait Transliterator[I, O] {

  def transliterate(text: I): Either[TransliterationError, O]

}
