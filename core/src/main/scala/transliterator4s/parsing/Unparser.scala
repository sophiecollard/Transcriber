package transliterator4s.parsing

import cats.Functor
import cats.syntax.functor._

trait Unparser[B, A] {

  def unparse(input: B): A

  final def contramap[C](f: C => B): Unparser[C, A] =
    Unparser.instance { input =>
      unparse(f(input))
    }

  final def map[C](f: A => C): Unparser[B, C] =
    Unparser.instance { input =>
      f(unparse(input))
    }

  final def lift[F[_]: Functor]: Unparser[F[B], F[A]] =
    Unparser.instance { input =>
      input.map(unparse)
    }

}

object Unparser {

  def apply[B, A](implicit ev: Unparser[B, A]): Unparser[B, A] =
    ev

  def instance[B, A](f: B => A): Unparser[B, A] =
    new Unparser[B, A] {
      override def unparse(input: B): A =
        f(input)
    }

}
