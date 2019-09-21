package com.github.sophiecollard.transliterator

import com.github.sophiecollard.transliterator.error.TransliterationError

package object transliteration {

  type TransliterationResult[A] = Either[TransliterationError, A]

}
