package com.github.sophiecollard.hangeul4s.parsing

package object syntax {

  implicit class ParsingOps(input: String) {
    def tokenize[F[_]](implicit tokenizer: Tokenizer[F]): F[String] =
      tokenizer.tokenize(input)

    def parse[A](implicit parser: Parser[A]): ParsingResult[A] =
      parser.parse(input)

    def parseF[F[_], A](implicit parserF: Parser[F[A]]): ParsingResult[F[A]] =
      parserF.parse(input)
  }

}
