package hangeul4s.parsing.generic

import cats.arrow.FunctionK
import cats.instances.either._
import cats.syntax.functor._
import cats.syntax.traverse._
import cats.{Functor, Traverse}
import hangeul4s.parsing._

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

  implicit def tokenizerParser[F[_]: Traverse, A](
    implicit tokenizer: Tokenizer[F, A],
    parser: Parser[Token[A], A]
  ): Parser[String, F[A]] =
    Parser.instance { input =>
      tokenizer.tokenize(input).map(parser.parse).sequence
    }

  implicit def tokenizerAccumulativeParser[F[_]: Traverse, A](
    implicit tokenizer: Tokenizer[F, A],
    parser: AccumulativeParser[Token[A], A]
  ): AccumulativeParser[String, F[A]] =
    AccumulativeParser.instance { input =>
      tokenizer.tokenize(input).map(parser.parse).sequence
    }

  implicit def unparserUntokenizer[F[_]: Functor, A](
    implicit unparser: Unparser[A, Token[A]],
    untokenizer: Untokenizer[F, A]
  ): Unparser[F[A], String] =
    Unparser.instance { input =>
      untokenizer.untokenize(input.map(unparser.unparse))
    }

  implicit val iteratorToList: FunctionK[Iterator, List] =
    λ[FunctionK[Iterator, List]](_.toList)

  implicit val iteratorToVector: FunctionK[Iterator, Vector] =
    λ[FunctionK[Iterator, Vector]](_.toVector)

  implicit val listToIterator: FunctionK[List, Iterator] =
    λ[FunctionK[List, Iterator]](_.iterator)

  implicit val vectorToIterator: FunctionK[Vector, Iterator] =
    λ[FunctionK[Vector, Iterator]](_.iterator)

  implicit def derivedTokenizer[F[_], G[_], A](
    implicit tokenizer: Tokenizer[F, A],
    f: FunctionK[F, G]
  ): Tokenizer[G, A] =
    tokenizer.mapK(f)

  implicit def derivedUntokenizer[F[_], G[_], A](
    implicit untokenizer: Untokenizer[F, A],
    f: FunctionK[G, F]
  ): Untokenizer[G, A] =
    untokenizer.contramapK(f)

}
