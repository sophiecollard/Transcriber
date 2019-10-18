package hangeul4s.parsing

import cats.data.NonEmptyVector
import cats.data.Validated.{Invalid, Valid}
import hangeul4s.error.ParsingFailure
import org.specs2.mutable.Specification

import scala.util.{Failure, Success, Try}

class AccumulativeParserSpec extends Specification {

  private val parser: AccumulativeParser[String, Int] =
    AccumulativeParser.instance { string =>
      Try(string.toInt) match {
        case Success(int) => Valid(int)
        // not a sensible failure to return but this is irrelevant here
        case Failure(_)   => Invalid(NonEmptyVector.one(ParsingFailure.EmptyInput))
      }
    }

  "AccumulativeParser#contramap" should {

    "return a new instance with a new input type from which the current input type can be derived" in {
      final case class MyStringWrapper(value: String)

      val contramappedParser = parser.contramap[MyStringWrapper](_.value)

      contramappedParser.parse(MyStringWrapper("1917")).toEither should beRight(1917)
    }

  }

  "AccumulativeParser#map" should {

    "return a new instance with a new output type which can be derived from the current output type" in {
      final case class MyIntWrapper(value: Int)

      val mappedParser = parser.map(MyIntWrapper)

      mappedParser.parse("1917").toEither should beRight(MyIntWrapper(1917))
    }

  }

  "AccumulativeParser#lift" should {

    "return a new instance with input and output types lifted into a type F[_]" in {
      import cats.instances.vector._

      val liftedParser = parser.lift[Vector]

      liftedParser.parse(Vector("6", "12", "1917")).toEither should beRight(Vector(6, 12, 1917))
    }

  }

}
