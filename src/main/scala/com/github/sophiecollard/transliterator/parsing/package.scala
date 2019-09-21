package com.github.sophiecollard.transliterator

import com.github.sophiecollard.transliterator.error.ParsingError

package object parsing {

  type ParsingResult[A] = Either[ParsingError, A]

  object ParsingResult {

    def success[A](value: A): ParsingResult[A] =
      Right(value)

  }

}
