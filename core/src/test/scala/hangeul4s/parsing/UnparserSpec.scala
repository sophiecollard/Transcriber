package hangeul4s.parsing

import org.specs2.mutable.Specification

class UnparserSpec extends Specification {

  private val unparser: Unparser[Int, String] =
    Unparser.instance(_.toString)

  "Unparser#contramap" should {

    "return a new instance with a new input type from which the current input type can be derived" in {
      final case class MyIntWrapper(value: Int)

      val contramappedUnparser = unparser.contramap[MyIntWrapper](_.value)

      contramappedUnparser.unparse(MyIntWrapper(1810)) ==== "1810"
    }

  }

  "Unparser#map" should {

    "return a new instance with a new output type which can be derived from the current output type" in {
      final case class MyStringWrapper(value: String)

      val mappedUnparser = unparser.map(MyStringWrapper)

      mappedUnparser.unparse(1810) ==== MyStringWrapper("1810")
    }

  }

  "Unparser#lift" should {

    "return a new instance with input and output types lifted into a type F[_]" in {
      import cats.instances.vector._

      val liftedUnparser = unparser.lift[Vector]

      liftedUnparser.unparse(Vector(20, 7, 1810)) ==== Vector("20", "7", "1810")
    }

  }

}
