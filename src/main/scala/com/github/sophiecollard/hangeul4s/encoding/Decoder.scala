package com.github.sophiecollard.hangeul4s.encoding

import com.github.sophiecollard.hangeul4s.error.DecodingError

trait Decoder[E, D] {

  def decode(encoded: E): Either[DecodingError, D]

}

object Decoder {

  def instance[E, D](g: E => Either[DecodingError, D]): Decoder[E, D] =
    new Decoder[E, D] {
      override def decode(encoded: E): Either[DecodingError, D] =
        g(encoded)
    }

}
