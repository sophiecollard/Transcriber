package com.github.sophiecollard.transliterator.parsing

import com.github.sophiecollard.transliterator.error.ParsingError

trait Parser[A] {

  def parse(input: String): ParsingResult[A]

}

object Parser {

  def instance[A](f: String => Either[ParsingError, A]): Parser[A] =
    new Parser[A] {
      override def parse(input: String): Either[ParsingError, A] =
        f(input)
    }

}
