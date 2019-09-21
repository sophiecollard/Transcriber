package com.github.sophiecollard.transliterator.model.romanization

import com.github.sophiecollard.transliterator.util.types.NonEmptyVector

final case class RomanizedText(words: NonEmptyVector[RomanizedWord])
