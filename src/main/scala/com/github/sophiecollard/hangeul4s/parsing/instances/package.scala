package com.github.sophiecollard.hangeul4s.parsing

import cats.{Functor, Traverse}
import cats.instances.either._
import cats.syntax.functor._
import cats.syntax.traverse._

package object instances {

  implicit def parserF[F[_]: Traverse, A](
    implicit tokenizer: Tokenizer[F],
    parser: Parser[A]
  ): Parser[F[A]] =
    Parser.instance { input =>
      tokenizer.tokenize(input).map(parser.parse).sequence
    }

  implicit def accumulativeParserF[F[_]: Traverse, A](
    implicit tokenizer: Tokenizer[F],
    parser: AccumulativeParser[A]
  ): AccumulativeParser[F[A]] =
    AccumulativeParser.instance { input =>
      tokenizer.tokenize(input).map(parser.parse).sequence
    }

  implicit def unparserF[F[_]: Functor, A](
    implicit untokenizer: Untokenizer[F],
    unparser: Unparser[A]
  ): Unparser[F[A]] =
    Unparser.instance { input =>
      untokenizer.untokenize(input.map(unparser.unparse))
    }

}
