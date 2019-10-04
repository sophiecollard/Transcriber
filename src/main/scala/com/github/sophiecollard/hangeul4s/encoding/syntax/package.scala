package com.github.sophiecollard.hangeul4s.encoding

package object syntax {

  implicit class DecoderOps[E](value: E) {
    def decode[D](implicit ev: Decoder[E, D]): DecodingResult[D] =
      ev.decode(value)
  }

  implicit class EncoderOps[D](value: D) {
    def encode[E](implicit ev: Encoder[E, D]): E =
      ev.encode(value)
  }

}
