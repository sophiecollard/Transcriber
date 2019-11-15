package hangeul4s.parsing

import cats.Traverse
import cats.syntax.functor._
import cats.syntax.traverse._

trait AccumulativeParser[-A, +B] {

  def parse(input: A): AccumulativeParsingResult[B]

  final def contramap[C](f: C => A): AccumulativeParser[C, B] =
    AccumulativeParser.instance { input =>
      parse(f(input))
    }

  final def map[D](f: B => D): AccumulativeParser[A, D] =
    AccumulativeParser.instance { input =>
      parse(input).map(f)
    }

  final def lift[F[_]: Traverse, AA <: A, BB >: B]: AccumulativeParser[F[AA], F[BB]] =
    AccumulativeParser.instance { input =>
      input.traverse(parse(_).widen[BB])
    }

}

object AccumulativeParser {

  def apply[A, B](implicit parser: AccumulativeParser[A, B]): AccumulativeParser[A, B] =
    parser

  def instance[A, B](f: A => AccumulativeParsingResult[B]): AccumulativeParser[A, B] =
    new AccumulativeParser[A, B] {
      override def parse(input: A): AccumulativeParsingResult[B] =
        f(input)
    }

}
