package com.github.sophiecollard.hangeul4s.parsing.syntax

import com.github.sophiecollard.hangeul4s.parsing._

trait Syntax {

  implicit class TokenizingOps(input: String) {
    def tokenize[F[_], A](implicit tokenizer: Tokenizer[F, A]): F[Token[A]] =
      tokenizer.tokenize(input)
  }

  implicit class UntokenizingOps[F[_], A](input: F[Token[A]]) {
    def untokenize(implicit untokenizer: Untokenizer[F, A]): String =
      untokenizer.untokenize(input)
  }

  implicit class ParsingOps[A](input: A) {
    def parse[B](implicit parser: Parser[A, B]): ParsingResult[B] =
      parser.parse(input)

    def parseF[F[_], B](implicit parserF: Parser[A, F[B]]): ParsingResult[F[B]] =
      parserF.parse(input)
  }

  implicit class UnparsingOps[B](input: B) {
    def unparse[A](implicit unparser: Unparser[B, A]): A =
      unparser.unparse(input)

    def unparseF[F[_], A](implicit unparserF: Unparser[B, F[A]]): F[A] =
      unparserF.unparse(input)
  }

}
