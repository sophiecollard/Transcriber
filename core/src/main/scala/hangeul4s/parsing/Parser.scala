package hangeul4s.parsing

import cats.Traverse
import cats.instances.either._
import cats.syntax.functor._
import cats.syntax.traverse._

trait Parser[-A, +B] {

  def parse(input: A): ParsingResult[B]

  final def contramap[C](f: C => A): Parser[C, B] =
    Parser.instance { input =>
      parse(f(input))
    }

  final def map[D](f: B => D): Parser[A, D] =
    Parser.instance { input =>
      parse(input).map(f)
    }

  final def lift[F[_]: Traverse, AA <: A, BB >: B]: Parser[F[AA], F[BB]] =
    Parser.instance { input =>
      input.traverse(parse(_).widen[BB])
    }

}

object Parser {

  def apply[A, B](implicit parser: Parser[A, B]): Parser[A, B] =
    parser

  def instance[A, B](f: A => ParsingResult[B]): Parser[A, B] =
    new Parser[A, B] {
      override def parse(input: A): ParsingResult[B] =
        f(input)
    }

}
