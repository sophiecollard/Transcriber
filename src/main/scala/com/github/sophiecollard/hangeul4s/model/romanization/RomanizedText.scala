package com.github.sophiecollard.hangeul4s.model.romanization

import com.github.sophiecollard.hangeul4s.util.types.NonEmptyVector

final case class RomanizedText(elements: NonEmptyVector[RomanizedTextElement])
