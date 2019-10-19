package hangeul4s

import hangeul4s.error.TransliterationFailure

package object transliteration {

  type TransliterationResult[+A] = Either[TransliterationFailure, A]

  object TransliterationResult {
    def success[A](value: A): TransliterationResult[A] =
      Right(value)

    def failure[A](e: TransliterationFailure): TransliterationResult[A] =
      Left(e)
  }

}
