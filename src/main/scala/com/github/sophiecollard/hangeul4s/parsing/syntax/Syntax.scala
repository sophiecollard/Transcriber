package com.github.sophiecollard.hangeul4s.parsing.syntax

import com.github.sophiecollard.hangeul4s.parsing._

trait Syntax {

  implicit class TokenizingOps(input: String) {
    def tokenize[F[_]](implicit tokenizer: Tokenizer[F]): F[String] =
      tokenizer.tokenize(input)
  }

  implicit class UntokenizingOps[F[_]](input: F[String]) {
    def untokenize(implicit untokenizer: Untokenizer[F]): String =
      untokenizer.untokenize(input)
  }

  implicit class ParsingOps(input: String) {
    def parse[A](implicit parser: Parser[A]): ParsingResult[A] =
      parser.parse(input)

    def parseF[F[_], A](implicit parserF: Parser[F[A]]): ParsingResult[F[A]] =
      parserF.parse(input)
  }

  implicit class UnparsingOps[A](input: A) {
    def unparse(implicit unparser: Unparser[A]): String =
      unparser.unparse(input)
  }

}
