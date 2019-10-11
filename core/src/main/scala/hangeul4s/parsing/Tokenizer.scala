package hangeul4s.parsing

trait Tokenizer[F[_], A] {

  def tokenize(input: String): F[Token[A]]

}

object Tokenizer {

  def apply[F[_], A](implicit ev: Tokenizer[F, A]): Tokenizer[F, A] =
    ev

  def instance[F[_], A](f: String => F[Token[A]]): Tokenizer[F, A] =
    new Tokenizer[F, A] {
      override def tokenize(input: String): F[Token[A]] =
        f(input)
    }

}
