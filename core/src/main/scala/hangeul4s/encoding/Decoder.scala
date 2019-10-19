package hangeul4s.encoding

import hangeul4s.error.DecodingFailure

trait Decoder[-A, +B] {

  def decode(encoded: A): Either[DecodingFailure, B]

}

object Decoder {

  def apply[A, B](implicit decoder: Decoder[A, B]): Decoder[A, B] =
    decoder

  def instance[A, B](f: A => Either[DecodingFailure, B]): Decoder[A, B] =
    new Decoder[A, B] {
      override def decode(encoded: A): Either[DecodingFailure, B] =
        f(encoded)
    }

}
