package com.github.sophiecollard.transliterator.encoding

import com.github.sophiecollard.transliterator.error.{DecodingError, EncodingError}

package object syntax {

  implicit class DecoderOps[E](value: E) {
    def decode[D](implicit ev: Decoder[E, D]): Either[DecodingError, D] =
      ev.decode(value)
  }

  implicit class EncoderOps[D](value: D) {
    def encode[E](implicit ev: Encoder[E, D]): Either[EncodingError, E] =
      ev.encode(value)
  }

}
