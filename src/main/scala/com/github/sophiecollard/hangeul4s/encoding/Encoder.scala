package com.github.sophiecollard.hangeul4s.encoding

import com.github.sophiecollard.hangeul4s.error.EncodingError

trait Encoder[B, A] {

  def encode(decoded: B): Either[EncodingError, A]

}

object Encoder {

  def apply[B, A](implicit ev: Encoder[B, A]): Encoder[B, A] =
    ev

  def instance[B, A](f: B => Either[EncodingError, A]): Encoder[B, A] =
    new Encoder[B, A] {
      override def encode(decoded: B): Either[EncodingError, A] =
        f(decoded)
    }

}
