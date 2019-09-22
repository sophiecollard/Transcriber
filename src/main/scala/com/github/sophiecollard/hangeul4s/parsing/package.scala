package com.github.sophiecollard.hangeul4s

import com.github.sophiecollard.hangeul4s.error.ParsingError
import com.github.sophiecollard.hangeul4s.util.types.ValidatedNev
import com.github.sophiecollard.hangeul4s.util.types.ValidatedNev.Valid

package object parsing {

  type SequentialParsingResult[A] = Either[ParsingError, A]

  object SequentialParsingResult {
    def success[A](value: A): SequentialParsingResult[A] =
      Right(value)
  }

  type ParallelParsingResult[A] = ValidatedNev[ParsingError, A]

  object ParallelParsingResult {
    def success[A](value: A): ParallelParsingResult[A] =
      Valid(value)
  }

}
