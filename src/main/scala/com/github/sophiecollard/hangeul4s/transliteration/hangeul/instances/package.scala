package com.github.sophiecollard.hangeul4s.transliteration.hangeul

import com.github.sophiecollard.hangeul4s.model.hangeul.HangeulTextElement
import com.github.sophiecollard.hangeul4s.model.romanization.RomanizedTextElement
import com.github.sophiecollard.hangeul4s.transliteration.Transliterator

package object instances {

  implicit val romanizer: Transliterator[HangeulTextElement, RomanizedTextElement] =
    HangeulRomanizer

}
