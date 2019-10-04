package com.github.sophiecollard.hangeul4s.parsing

trait AccumulativeParser[A] {

  def parse(input: String): AccumulativeParsingResult[A]

}

object AccumulativeParser {

  def apply[A](implicit ev: AccumulativeParser[A]): AccumulativeParser[A] =
    ev

  def instance[A](f: String => AccumulativeParsingResult[A]): AccumulativeParser[A] =
    new AccumulativeParser[A] {
      override def parse(input: String): AccumulativeParsingResult[A] =
        f(input)
    }

}
