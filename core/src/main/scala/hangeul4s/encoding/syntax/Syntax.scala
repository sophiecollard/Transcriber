package hangeul4s.encoding.syntax

import hangeul4s.encoding.{Decoder, Encoder}
import hangeul4s.error.DecodingFailure

trait Syntax {

  implicit class DecoderOps[A](value: A) {
    def decodeTo[B](implicit decoder: Decoder[A, B]): Either[DecodingFailure, B] =
      decoder.decode(value)
  }

  implicit class EncoderOps[B](value: B) {
    def encodeTo[A](implicit encoder: Encoder[B, A]): A =
      encoder.encode(value)
  }

}
