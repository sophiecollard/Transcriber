package hangeul4s.transliteration.hangeul.instances

import hangeul4s.model.hangeul.HangeulTextElement
import hangeul4s.model.romanization.RomanizedTextElement
import hangeul4s.transliteration.Transliterator
import hangeul4s.transliteration.hangeul.RevisedRomanization

trait HangeulInstances {

  implicit val hangeulRevisedRomanizationTransliterator: Transliterator[HangeulTextElement, RomanizedTextElement] =
    RevisedRomanization.transliterator

}
