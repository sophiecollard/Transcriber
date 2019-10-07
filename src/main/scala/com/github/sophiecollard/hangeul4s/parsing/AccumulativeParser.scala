package com.github.sophiecollard.hangeul4s.parsing

import cats.Traverse
import cats.syntax.functor._
import cats.syntax.traverse._

trait AccumulativeParser[A, B] {

  def parse(input: A): AccumulativeParsingResult[B]

  final def contramap[C](f: C => A): AccumulativeParser[C, B] =
    AccumulativeParser.instance { input =>
      parse(f(input))
    }

  final def map[C](f: B => C): AccumulativeParser[A, C] =
    AccumulativeParser.instance { input =>
      parse(input).map(f)
    }

  final def lift[F[_]: Traverse]: AccumulativeParser[F[A], F[B]] =
    AccumulativeParser.instance { input =>
      input.map(parse).sequence
    }

}

object AccumulativeParser {

  def apply[A, B](implicit ev: AccumulativeParser[A, B]): AccumulativeParser[A, B] =
    ev

  def instance[A, B](f: A => AccumulativeParsingResult[B]): AccumulativeParser[A, B] =
    new AccumulativeParser[A, B] {
      override def parse(input: A): AccumulativeParsingResult[B] =
        f(input)
    }

}
