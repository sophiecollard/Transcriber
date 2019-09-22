package com.github.sophiecollard.hangeul4s.model.romanization

import com.github.sophiecollard.hangeul4s.util.types.NonEmptyVector

final case class RomanizedText(elements: NonEmptyVector[RomanizedTextElement])

object RomanizedText {

  def fromElements(e: RomanizedTextElement, es: RomanizedTextElement*): RomanizedText =
    RomanizedText(NonEmptyVector(e, es.toVector))

}
