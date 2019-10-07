package com.github.sophiecollard.hangeul4s.parsing.generic

import cats.{Functor, Traverse}
import cats.instances.either._
import cats.syntax.functor._
import cats.syntax.traverse._
import com.github.sophiecollard.hangeul4s.parsing._

trait Generic {

  implicit def tokenParser[A](
    implicit parser: Parser[String, A]
  ): Parser[Token[A], A] =
    parser.contramap(_.contents)

  implicit def tokenAccumulativeParser[A](
    implicit parser: AccumulativeParser[String, A]
  ): AccumulativeParser[Token[A], A] =
    parser.contramap(_.contents)

  implicit def tokenUnparser[A](
    implicit unparser: Unparser[A, String]
  ): Unparser[A, Token[A]] =
    unparser.map(Token.apply[A])

  implicit def tokenizerParser[F[_]: Traverse, A, B](
    implicit tokenizer: Tokenizer[F, A],
    parser: Parser[Token[A], B]
  ): Parser[String, F[B]] =
    Parser.instance { input =>
      tokenizer.tokenize(input).map(parser.parse).sequence
    }

  implicit def tokenizerAccumulativeParser[F[_]: Traverse, A, B](
    implicit tokenizer: Tokenizer[F, A],
    parser: AccumulativeParser[Token[A], B]
  ): AccumulativeParser[String, F[B]] =
    AccumulativeParser.instance { input =>
      tokenizer.tokenize(input).map(parser.parse).sequence
    }

  implicit def unparserUntokenizer[F[_]: Functor, B, A](
    implicit unparser: Unparser[B, Token[A]],
    untokenizer: Untokenizer[F, A]
  ): Unparser[F[B], String] =
    Unparser.instance { input =>
      untokenizer.untokenize(input.map(unparser.unparse))
    }

  implicit def liftedParser[F[_]: Traverse, A, B](
    implicit parser: Parser[A, B]
  ): Parser[F[A], F[B]] =
    parser.lift[F]

  implicit def liftedAccumulativeParser[F[_]: Traverse, A, B](
    implicit parser: AccumulativeParser[A, B]
  ): AccumulativeParser[F[A], F[B]] =
    parser.lift[F]

  implicit def liftedUnparser[F[_]: Functor, B, A](
    implicit unparser: Unparser[B, A]
  ): Unparser[F[B], F[A]] =
    unparser.lift[F]

}
