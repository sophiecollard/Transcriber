package com.github.sophiecollard.hangeul4s.encoding.syntax

import com.github.sophiecollard.hangeul4s.encoding.{Decoder, Encoder}
import com.github.sophiecollard.hangeul4s.error.DecodingError

trait Syntax {

  implicit class DecoderOps[A](value: A) {
    def decode[B](implicit ev: Decoder[A, B]): Either[DecodingError, B] =
      ev.decode(value)
  }

  implicit class EncoderOps[B](value: B) {
    def encode[A](implicit ev: Encoder[B, A]): A =
      ev.encode(value)
  }

}
