package hangeul4s.parsing

import cats.arrow.FunctionK

trait Untokenizer[F[_], A] {

  def untokenize(input: F[Token[A]]): String

  final def contramapK[G[_]](f: FunctionK[G, F]): Untokenizer[G, A] =
    Untokenizer.instance { input =>
      untokenize(f(input))
    }

}

object Untokenizer {

  def apply[F[_], A](implicit ev: Untokenizer[F, A]): Untokenizer[F, A] =
    ev

  def instance[F[_], A](f: F[Token[A]] => String): Untokenizer[F, A] =
    new Untokenizer[F, A] {
      override def untokenize(input: F[Token[A]]): String =
        f(input)
    }

}
