package com.github.sophiecollard.transcriber.util

import org.specs2.mutable.Specification

class MonoidSpec extends Specification {

  "A monoid instance" should {

    "provide an empty value" in {
      Monoid.empty[String] must beEqualTo("")
    }

    "combine two values" in {
      Monoid.combine("A", "B") must beEqualTo("AB")
    }

    "combine an arbitrary number of values" in {
      Monoid.combineAll("A", "B", "C") must beEqualTo("ABC")
    }

  }

  implicit val stringMonoid: Monoid[String] = new Monoid[String] {
    override def empty: String = ""

    override def combine(x: String, y: String): String = x ++ y
  }

}
