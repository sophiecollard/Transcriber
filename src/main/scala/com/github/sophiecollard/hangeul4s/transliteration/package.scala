package com.github.sophiecollard.hangeul4s

import com.github.sophiecollard.hangeul4s.error.TransliterationError

package object transliteration {

  type TransliterationResult[A] = Either[TransliterationError, A]

}
