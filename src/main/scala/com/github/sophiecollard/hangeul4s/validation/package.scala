package com.github.sophiecollard.hangeul4s

import com.github.sophiecollard.hangeul4s.error.ValidationError
import com.github.sophiecollard.hangeul4s.util.types.ValidatedNev

package object validation {

  type ValidationResult[A] = ValidatedNev[ValidationError, A]

}
