package hangeul4s.parsing.syntax

import cats.instances.vector._
import hangeul4s.error.ParsingFailure
import hangeul4s.parsing.generic._
import hangeul4s.parsing._
import org.specs2.mutable.Specification

import scala.util.{Failure, Success, Try}

class SyntaxSpec extends Specification {

  private implicit val tokenizer: Tokenizer[Vector, Int] =
    Tokenizer.instance { string =>
      string
        .split("\\D+")
        .toVector
        .map(Token.apply[Int])
    }

  "Syntax#TokenizingOps" should {

    "provide a 'tokenizeTo' method on String instances" in {
      "14/07/1789".tokenizeTo[Vector, Int] ====
        Vector[Token[Int]](Token("14"), Token("07"), Token("1789"))
    }

  }

  private implicit val untokenizer: Untokenizer[Vector, Int] =
    Untokenizer.instance { tokens =>
      tokens.map(_.contents).mkString("/")
    }

  "Syntax#UntokenizingOps[F[_], A]" should {

    "provide an 'untokenize' method on F[Token[A]] instances" in {
      Vector[Token[Int]](Token("14"), Token("7"), Token("1789")).untokenize ====
        "14/7/1789"
    }

  }

  private implicit val parser: Parser[Token[Int], Int] =
    Parser.instance { token =>
      Try(token.contents.toInt) match {
        case Success(int) => Right(int)
        // not a sensible failure to return but this is irrelevant here
        case Failure(_)   => Left(ParsingFailure.EmptyInput)
      }
    }

  "Syntax#ParsingOps[A]" should {

    "provide a 'parseTo' method on A instances" in {
      Token[Int]("1789").parseTo[Int] should beRight(1789)
    }

    "provide a 'parseToF' method on A instances" in {
      "14/07/1789".parseToF[Vector, Int] should beRight(Vector(14, 7, 1789))
    }

  }

  private implicit val unparser: Unparser[Int, Token[Int]] =
    Unparser.instance { int =>
      Token[Int](int.toString)
    }

  private implicit val unparserF: Unparser[Int, Vector[Char]] =
    Unparser.instance { int =>
      int.toString.toCharArray.toVector
    }

  "Syntax#UnparsingOps[B]" should {

    "provide a 'unparseTo' method on B instances" in {
      1789.unparseTo[Token[Int]] ==== Token[Int]("1789")
    }

    "provide a 'unparseToF' method on B instances" in {
      1789.unparseToF[Vector, Char] ==== Vector('1', '7', '8', '9')
    }

  }

}
