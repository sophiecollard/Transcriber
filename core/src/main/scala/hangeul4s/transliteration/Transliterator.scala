package hangeul4s.transliteration

trait Transliterator[-A, +B] {

  def transliterate(input: A): TransliterationResult[B]

}

object Transliterator {

  def apply[A, B](implicit transliterator: Transliterator[A, B]): Transliterator[A, B] =
    transliterator

  def instance[A, B](f: A => TransliterationResult[B]): Transliterator[A, B] =
    new Transliterator[A, B] {
      override def transliterate(input: A): TransliterationResult[B] =
        f(input)
    }

}
