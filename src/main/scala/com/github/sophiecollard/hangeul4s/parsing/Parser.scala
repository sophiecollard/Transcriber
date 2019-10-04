package com.github.sophiecollard.hangeul4s.parsing

trait Parser[A] {

  def parse(input: String): ParsingResult[A]

}

object Parser {

  def apply[A](implicit ev: Parser[A]): Parser[A] =
    ev

  def instance[A](f: String => ParsingResult[A]): Parser[A] =
    new Parser[A] {
      override def parse(input: String): ParsingResult[A] =
        f(input)
    }

}
