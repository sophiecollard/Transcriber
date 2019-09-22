package com.github.sophiecollard.hangeul4s

import com.github.sophiecollard.hangeul4s.error.ParsingError
import com.github.sophiecollard.hangeul4s.util.types.ValidatedNev
import com.github.sophiecollard.hangeul4s.util.types.ValidatedNev.Valid

package object parsing {

  type ParsingResult[A] = Either[ParsingError, A]

  object ParsingResult {
    def success[A](value: A): ParsingResult[A] =
      Right(value)
  }

  type ParallelParsingResult[A] = ValidatedNev[ParsingError, A]

  object ParallelParsingResult {
    def success[A](value: A): ParallelParsingResult[A] =
      Valid(value)
  }

}
