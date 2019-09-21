package com.github.sophiecollard.transliterator

import com.github.sophiecollard.transliterator.error.ValidationError
import com.github.sophiecollard.transliterator.util.types.{NonEmptyVector, ValidatedNev}

package object validation {

  type ValidationResult[A] = ValidatedNev[ValidationError, A]

  type SequentialValidationResult[A] = Either[NonEmptyVector[ValidationError], A]

}
