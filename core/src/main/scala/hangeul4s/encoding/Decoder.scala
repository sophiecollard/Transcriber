package hangeul4s.encoding

import hangeul4s.error.DecodingFailure

trait Decoder[A, B] {

  def decode(encoded: A): Either[DecodingFailure, B]

}

object Decoder {

  def apply[A, B](implicit ev: Decoder[A, B]): Decoder[A, B] =
    ev

  def instance[A, B](g: A => Either[DecodingFailure, B]): Decoder[A, B] =
    new Decoder[A, B] {
      override def decode(encoded: A): Either[DecodingFailure, B] =
        g(encoded)
    }

}
