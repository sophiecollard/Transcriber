package com.github.sophiecollard.hangeul4s.encoding

import com.github.sophiecollard.hangeul4s.error.DecodingError

trait Decoder[A, B] {

  def decode(encoded: A): Either[DecodingError, B]

}

object Decoder {

  def apply[A, B](implicit ev: Decoder[A, B]): Decoder[A, B] =
    ev

  def instance[A, B](g: A => Either[DecodingError, B]): Decoder[A, B] =
    new Decoder[A, B] {
      override def decode(encoded: A): Either[DecodingError, B] =
        g(encoded)
    }

}
