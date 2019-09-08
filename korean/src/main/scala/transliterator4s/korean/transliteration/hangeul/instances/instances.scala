package transliterator4s.korean.transliteration.hangeul

import transliterator4s.korean.model.hangeul.HangeulTextElement
import transliterator4s.korean.model.romanization.RomanizedTextElement
import transliterator4s.transliteration.Transliterator

package object instances {

  implicit val romanizer: Transliterator[HangeulTextElement, RomanizedTextElement] =
    HangeulRomanizer

}
