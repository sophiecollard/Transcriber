package hangeul4s.parsing

import hangeul4s.error.ParsingFailure
import org.specs2.mutable.Specification

import scala.util.{Failure, Success, Try}

class ParserSpec extends Specification {

  private val parser: Parser[String, Int] =
    Parser.instance { string =>
      Try(string.toInt) match {
        case Success(int) => Right(int)
        // not a sensible failure to return but this is irrelevant here
        case Failure(_)   => Left(ParsingFailure.EmptyInput)
      }
    }

  "Parser#contramap" should {

    "return a new instance with a new input type from which the current input type can be derived" in {
      final case class MyStringWrapper(value: String)

      val contramappedParser = parser.contramap[MyStringWrapper](_.value)

      contramappedParser.parse(MyStringWrapper("1917")) should beRight(1917)
    }

  }

  "Parser#map" should {

    "return a new instance with a new output type which can be derived from the current output type" in {
      final case class MyIntWrapper(value: Int)

      val mappedParser = parser.map(MyIntWrapper)

      mappedParser.parse("1917") should beRight(MyIntWrapper(1917))
    }

  }

  "Parser#lift" should {

    "return a new instance with input and output types lifted into a type F[_]" in {
      import cats.instances.vector._

      val liftedParser = parser.lift[Vector, String, Int]

      liftedParser.parse(Vector("6", "12", "1917")) should beRight(Vector(6, 12, 1917))
    }

  }

}
