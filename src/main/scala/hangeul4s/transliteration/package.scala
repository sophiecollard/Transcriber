package hangeul4s

import hangeul4s.error.TransliterationFailure

package object transliteration {

  type TransliterationResult[A] = Either[TransliterationFailure, A]

}
