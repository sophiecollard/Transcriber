package com.github.sophiecollard.hangeul4s.encoding

trait Encoder[E, D] {

  def encode(decoded: D): E

}

object Encoder {

  def apply[E, D](implicit ev: Encoder[E, D]): Encoder[E, D] =
    ev

  def instance[E, D](f: D => E): Encoder[E, D] =
    new Encoder[E, D] {
      override def encode(decoded: D): E =
        f(decoded)
    }

}
