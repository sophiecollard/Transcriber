package hangeul4s.parsing.generics

import cats.arrow.FunctionK
import cats.instances.either._
import cats.syntax.functor._
import cats.syntax.traverse._
import cats.{Functor, Traverse}
import hangeul4s.parsing._

trait ParsingGenerics {

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
    tokenParser: Parser[Token[A], A]
  ): Parser[String, F[A]] =
    Parser.instance { input =>
      tokenizer.tokenize(input).map(tokenParser.parse).sequence
    }

  implicit def tokenizerAccumulativeParser[F[_]: Traverse, A](
    implicit tokenizer: Tokenizer[F, A],
    tokenParser: AccumulativeParser[Token[A], A]
  ): AccumulativeParser[String, F[A]] =
    AccumulativeParser.instance { input =>
      tokenizer.tokenize(input).map(tokenParser.parse).sequence
    }

  implicit def unparserUntokenizer[F[_]: Functor, A](
    implicit tokenUnparser: Unparser[A, Token[A]],
    untokenizer: Untokenizer[F, A]
  ): Unparser[F[A], String] =
    Unparser.instance { input =>
      untokenizer.untokenize(input.map(tokenUnparser.unparse))
    }

  implicit val iteratorToList: FunctionK[Iterator, List] =
    λ[FunctionK[Iterator, List]](_.toList)

  implicit val iteratorToVector: FunctionK[Iterator, Vector] =
    λ[FunctionK[Iterator, Vector]](_.toVector)

  implicit val listToIterator: FunctionK[List, Iterator] =
    λ[FunctionK[List, Iterator]](_.iterator)

  implicit val vectorToIterator: FunctionK[Vector, Iterator] =
    λ[FunctionK[Vector, Iterator]](_.iterator)

  implicit def tokenizerG[F[_], G[_], A](
    implicit tokenizerF: Tokenizer[F, A],
    f: FunctionK[F, G]
  ): Tokenizer[G, A] =
    tokenizerF.mapK(f)

  implicit def untokenizerG[F[_], G[_], A](
    implicit untokenizerF: Untokenizer[F, A],
    f: FunctionK[G, F]
  ): Untokenizer[G, A] =
    untokenizerF.contramapK(f)

}
