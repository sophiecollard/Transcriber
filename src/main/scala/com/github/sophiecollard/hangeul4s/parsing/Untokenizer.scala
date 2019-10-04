package com.github.sophiecollard.hangeul4s.parsing

trait Untokenizer[F[_]] {

  def untokenize(input: F[String]): String

}

object Untokenizer {

  def apply[F[_]](implicit ev: Untokenizer[F]): Untokenizer[F] =
    ev

  def instance[F[_]](f: F[String] => String): Untokenizer[F] =
    new Untokenizer[F] {
      override def untokenize(input: F[String]): String =
        f(input)
    }

}
