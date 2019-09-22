package com.github.sophiecollard.hangeul4s.parsing

trait ParallelParser[A] {

  def parse(input: String): ParallelParsingResult[A]

}

object ParallelParser {

  def instance[A](f: String => ParallelParsingResult[A]): ParallelParser[A] =
    new ParallelParser[A] {
      override def parse(input: String): ParallelParsingResult[A] =
        f(input)
    }

}
