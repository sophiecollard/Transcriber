package com.github.sophiecollard.hangeul4s

import com.github.sophiecollard.hangeul4s.error.ParsingError

package object parsing {

  type ParsingResult[A] = Either[ParsingError, A]

  object ParsingResult {

    def success[A](value: A): ParsingResult[A] =
      Right(value)

  }

}
