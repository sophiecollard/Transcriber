package com.github.sophiecollard.hangeul4s.parsing

trait SequentialParser[A] {

  def parse(input: String): SequentialParsingResult[A]

}

object SequentialParser {

  def instance[A](f: String => SequentialParsingResult[A]): SequentialParser[A] =
    new SequentialParser[A] {
      override def parse(input: String): SequentialParsingResult[A] =
        f(input)
    }

}
