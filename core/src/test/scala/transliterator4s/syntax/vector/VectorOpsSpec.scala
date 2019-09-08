package transliterator4s.syntax.vector

import org.specs2.mutable.Specification

class VectorOpsSpec extends Specification {

  "RichVector#zipWithNeighbors" should {
    "return an empty vector when invoked on an empty vector" in {
      val input = Vector.empty[Int]

      input.zipWithNeighbors must beEqualTo(Vector.empty[Int])
    }

    "return each value in a vector along with its previous and next neighbours, if any" in {
      val input = Vector(1, 2, 3)

      val expectedOutput = Vector(
        (None, 1, Some(2)),
        (Some(1), 2, Some(3)),
        (Some(2), 3, None)
      )

      input.zipWithNeighbors must beEqualTo(expectedOutput)
    }
  }

}
