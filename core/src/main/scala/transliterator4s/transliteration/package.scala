package transliterator4s

import transliterator4s.error.TransliterationFailure

package object transliteration {

  type TransliterationResult[A] = Either[TransliterationFailure, A]

}
