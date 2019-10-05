package com.github.sophiecollard.hangeul4s

import com.github.sophiecollard.hangeul4s.error.TransliterationFailure

package object transliteration {

  type TransliterationResult[A] = Either[TransliterationFailure, A]

}
