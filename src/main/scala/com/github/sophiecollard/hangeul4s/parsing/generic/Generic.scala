package com.github.sophiecollard.hangeul4s.parsing.generic

import cats.{Functor, Traverse}
import cats.instances.either._
import cats.syntax.functor._
import cats.syntax.traverse._
import com.github.sophiecollard.hangeul4s.parsing._

trait Generic {

  implicit def tokenizerParser[F[_]: Traverse, A](
    implicit tokenizer: Tokenizer[F],
    parser: Parser[String, A]
  ): Parser[String, F[A]] =
    Parser.instance { input =>
      tokenizer.tokenize(input).map(parser.parse).sequence
    }

  implicit def tokenizerAccumulativeParser[F[_]: Traverse, A](
    implicit tokenizer: Tokenizer[F],
    parser: AccumulativeParser[String, A]
  ): AccumulativeParser[String, F[A]] =
    AccumulativeParser.instance { input =>
      tokenizer.tokenize(input).map(parser.parse).sequence
    }

  implicit def unparserUntokenizer[F[_]: Functor, A](
    implicit untokenizer: Untokenizer[F],
    unparser: Unparser[A, String]
  ): Unparser[F[A], String] =
    Unparser.instance { input =>
      untokenizer.untokenize(input.map(unparser.unparse))
    }

}
