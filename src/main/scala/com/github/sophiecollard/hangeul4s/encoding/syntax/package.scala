package com.github.sophiecollard.hangeul4s.encoding

import com.github.sophiecollard.hangeul4s.error.{DecodingError, EncodingError}

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
