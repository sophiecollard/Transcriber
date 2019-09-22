package com.github.sophiecollard.hangeul4s.util.typeclasses

trait Monoid[A] {

  def empty: A

  def combine(x: A, y: A): A

}

object Monoid {

  def empty[A](implicit ev: Monoid[A]): A =
    ev.empty

  def combine[A](x: A, y: A)(implicit ev: Monoid[A]): A =
    ev.combine(x, y)

  def combineAll[A](as: A*)(implicit ev: Monoid[A]): A =
    as.foldRight(ev.empty)(ev.combine)

}
