package hangeul4s.transliteration

trait Transliterator[I, O] {

  def transliterate(input: I): TransliterationResult[O]

}

object Transliterator {

  def apply[I, O](implicit ev: Transliterator[I, O]): Transliterator[I, O] =
    ev

  def instance[I, O](f: I => TransliterationResult[O]): Transliterator[I, O] =
    new Transliterator[I, O] {
      override def transliterate(input: I): TransliterationResult[O] =
        f(input)
    }

}
