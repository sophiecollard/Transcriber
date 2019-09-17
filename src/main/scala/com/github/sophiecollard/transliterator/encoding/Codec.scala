package com.github.sophiecollard.transliterator.encoding

import com.github.sophiecollard.transliterator.error.{DecodingError, EncodingError}

trait Codec[E, D] extends Encoder[E, D] with Decoder[E, D]

object Codec {

  def instance[E, D](f: D => Either[EncodingError, E], g: E => Either[DecodingError, D]): Codec[E, D] =
    new Codec[E, D] {
      override def encode(decoded: D): Either[EncodingError, E] =
        f(decoded)

      override def decode(encoded: E): Either[DecodingError, D] =
        g(encoded)
    }

}
