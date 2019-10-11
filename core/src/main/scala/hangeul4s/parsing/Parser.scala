package hangeul4s.parsing

import cats.Traverse
import cats.instances.either._
import cats.syntax.functor._
import cats.syntax.traverse._

trait Parser[A, B] {

  def parse(input: A): ParsingResult[B]

  final def contramap[C](f: C => A): Parser[C, B] =
    Parser.instance { input =>
      parse(f(input))
    }

  final def map[C](f: B => C): Parser[A, C] =
    Parser.instance { input =>
      parse(input).map(f)
    }

  final def lift[F[_]: Traverse]: Parser[F[A], F[B]] =
    Parser.instance { input =>
      input.map(parse).sequence
    }

}

object Parser {

  def apply[A, B](implicit ev: Parser[A, B]): Parser[A, B] =
    ev

  def instance[A, B](f: A => ParsingResult[B]): Parser[A, B] =
    new Parser[A, B] {
      override def parse(input: A): ParsingResult[B] =
        f(input)
    }

}
