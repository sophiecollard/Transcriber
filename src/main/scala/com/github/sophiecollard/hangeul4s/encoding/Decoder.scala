package com.github.sophiecollard.hangeul4s.encoding

trait Decoder[E, D] {

  def decode(encoded: E): DecodingResult[D]

}

object Decoder {

  def apply[E, D](implicit ev: Decoder[E, D]): Decoder[E, D] =
    ev

  def instance[E, D](g: E => DecodingResult[D]): Decoder[E, D] =
    new Decoder[E, D] {
      override def decode(encoded: E): DecodingResult[D] =
        g(encoded)
    }

}
