package hangeul4s.encoding.syntax

import hangeul4s.encoding.{Decoder, Encoder}
import hangeul4s.error.DecodingFailure

trait Syntax {

  implicit class DecoderOps[A](value: A) {
    def decodeAs[B](implicit ev: Decoder[A, B]): Either[DecodingFailure, B] =
      ev.decode(value)
  }

  implicit class EncoderOps[B](value: B) {
    def encodeAs[A](implicit ev: Encoder[B, A]): A =
      ev.encode(value)
  }

}
