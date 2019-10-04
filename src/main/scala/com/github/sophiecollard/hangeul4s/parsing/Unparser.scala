package com.github.sophiecollard.hangeul4s.parsing

trait Unparser[A] {

  def unparse(input: A): String

}

object Unparser {

  def apply[A](implicit ev: Unparser[A]): Unparser[A] =
    ev

  def instance[A](f: A => String): Unparser[A] =
    new Unparser[A] {
      override def unparse(input: A): String =
        f(input)
    }

}
