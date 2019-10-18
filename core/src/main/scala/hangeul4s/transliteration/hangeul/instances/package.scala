package hangeul4s.transliteration.hangeul

import hangeul4s.model.hangeul.HangeulTextElement
import hangeul4s.model.romanization.RomanizedTextElement
import hangeul4s.transliteration.Transliterator

package object instances {

  implicit val revisedRomanizationTransliterator: Transliterator[HangeulTextElement, RomanizedTextElement] =
    RevisedRomanization.transliterator

}
