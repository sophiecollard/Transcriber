package com.github.sophiecollard.transliterator

import com.github.sophiecollard.transliterator.error.ValidationError
import com.github.sophiecollard.transliterator.util.types.ValidatedNev

package object validation {

  type ValidationResult[A] = ValidatedNev[ValidationError, A]

}
