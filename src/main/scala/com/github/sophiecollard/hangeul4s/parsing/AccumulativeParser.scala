package com.github.sophiecollard.hangeul4s.parsing

trait AccumulativeParser[A] {

  def parse(input: String): AccumulativeParsingResult[A]

}

object AccumulativeParser {

  def instance[A](f: String => AccumulativeParsingResult[A]): AccumulativeParser[A] =
    new AccumulativeParser[A] {
      override def parse(input: String): AccumulativeParsingResult[A] =
        f(input)
    }

}
