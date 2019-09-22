package com.github.sophiecollard.hangeul4s.transliteration

import com.github.sophiecollard.hangeul4s.error.TransliterationError

trait Transliterator[I, O] {

  def transliterate(text: I): Either[TransliterationError, O]

}
