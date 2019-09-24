package com.github.sophiecollard.hangeul4s.util.typeclasses

import com.github.sophiecollard.hangeul4s.instances.vector.vectorTraverse
import org.specs2.mutable.Specification

import scala.util.Try

class TraverseSpec extends Specification {

  "A Traverse instance" should {

    "implement the 'traverse' method" in {
      val f: String => Option[Int] = n => Try(n.toInt).toOption
      Traverse.traverse(Vector("1", "2", "3"))(f) must beSome(Vector(1, 2, 3))
      Traverse.traverse(Vector("1", "B", "3"))(f) must beNone
    }

    "implement the 'sequence' method" in {
      Traverse.sequence(Vector(Option(1), Option(2), Option(3))) must beSome(Vector(1, 2, 3))
      Traverse.sequence(Vector(Option(1), None, Option(3))) must beNone
    }

  }

  implicit val optionApplicative: Applicative[Option] =
    new Applicative[Option] {
      def pure[A](a: A): Option[A] =
        Some(a)

      def ap[A, B](ff: Option[A => B])(fa: Option[A]): Option[B] =
        ff.flatMap(f => fa.map(a => f(a)))
    }

}
