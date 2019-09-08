package transliterator4s.encoding

trait Encoder[B, A] {

  def encode(decoded: B): A

}

object Encoder {

  def apply[B, A](implicit ev: Encoder[B, A]): Encoder[B, A] =
    ev

  def instance[B, A](f: B => A): Encoder[B, A] =
    new Encoder[B, A] {
      override def encode(decoded: B): A =
        f(decoded)
    }

}
