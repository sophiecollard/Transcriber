package com.github.sophiecollard.hangeul4s.encoding

import com.github.sophiecollard.hangeul4s.error.{DecodingError, EncodingError}

trait Codec[E, D] extends Encoder[E, D] with Decoder[E, D]

object Codec {

  def apply[E, D](implicit ev: Codec[E, D]): Codec[E, D] =
    ev

  def apply[E, D](implicit ev1: Encoder[E, D], ev2: Decoder[E, D]): Codec[E, D] =
    Codec.instance(ev1.encode, ev2.decode)

  def instance[E, D](f: D => Either[EncodingError, E], g: E => Either[DecodingError, D]): Codec[E, D] =
    new Codec[E, D] {
      override def encode(decoded: D): Either[EncodingError, E] =
        f(decoded)

      override def decode(encoded: E): Either[DecodingError, D] =
        g(encoded)
    }

}
