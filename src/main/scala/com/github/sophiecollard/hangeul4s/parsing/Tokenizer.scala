package com.github.sophiecollard.hangeul4s.parsing

trait Tokenizer[F[_]] {

  def tokenize(input: String): F[String]

}

object Tokenizer {

  def apply[F[_]](implicit ev: Tokenizer[F]): Tokenizer[F] =
    ev

  def instance[F[_]](f: String => F[String]): Tokenizer[F] =
    new Tokenizer[F] {
      override def tokenize(input: String): F[String] =
        f(input)
    }

}
