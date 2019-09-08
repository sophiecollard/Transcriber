package com.github.sophiecollard.transcriber.text

import com.github.sophiecollard.transcriber.error.ParsingError

trait Parser[T] {

  def parse(string: String): Either[ParsingError, T]

}

object Parser {

  def instance[T](f: String => Either[ParsingError, T]): Parser[T] =
    new Parser[T] {
      override def parse(string: String): Either[ParsingError, T] =
        f(string)
    }

}
