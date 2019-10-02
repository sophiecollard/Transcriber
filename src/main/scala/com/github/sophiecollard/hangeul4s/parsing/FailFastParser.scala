package com.github.sophiecollard.hangeul4s.parsing

trait FailFastParser[A] {

  def parse(input: String): FailFastParsingResult[A]

}

object FailFastParser {

  def instance[A](f: String => FailFastParsingResult[A]): FailFastParser[A] =
    new FailFastParser[A] {
      override def parse(input: String): FailFastParsingResult[A] =
        f(input)
    }

}
