package hangeul4s.model.romanization

import cats.arrow.FunctionK
import cats.data.NonEmptyVector
import hangeul4s.model.hangeul.HangeulTextElement
import hangeul4s.parsing.{Token, Tokenizer, Unparser, Untokenizer}

import scala.util.matching.Regex

sealed trait RomanizedTextElement

object RomanizedTextElement {

  final case class Captured(letters: NonEmptyVector[RomanLetter]) extends RomanizedTextElement

  object Captured {
    def fromLetters(l: RomanLetter, ls: RomanLetter*): Captured =
      Captured(NonEmptyVector(l, ls.toVector))

    private [romanization] val regex: Regex = "[A-EG-PR-UWYa-eg-pr-uwy]+".r
  }

  sealed abstract case class NotCaptured(contents: String) extends RomanizedTextElement

  object NotCaptured {
    def fromHangeul(hangeul: HangeulTextElement.NotCaptured): NotCaptured =
      new NotCaptured(hangeul.contents) {}

    def fromString(input: String): Option[NotCaptured] =
      s"^($regex)$$".r
        .findFirstIn(input)
        .map(new NotCaptured(_) {})

    private [romanization] def unvalidatedFromString(input: String): NotCaptured =
      new NotCaptured(input) {}

    private [romanization] val regex: Regex = "[^A-EG-PR-UWYa-eg-pr-uwy]+".r
  }

  implicit val unparser: Unparser[RomanizedTextElement, String] =
    Unparser.instance {
      case Captured(letters)     => letters.toVector.map(_.char).mkString
      case NotCaptured(contents) => contents
    }

  private val iteratorTokenizer: Tokenizer[Iterator, RomanizedTextElement] =
    Tokenizer.instance { input =>
      s"(${Captured.regex})|(${NotCaptured.regex})".r
        .findAllIn(input)
        .map(Token.apply[RomanizedTextElement])
    }

  implicit val listTokenizer: Tokenizer[List, RomanizedTextElement] =
    iteratorTokenizer.mapK(λ[FunctionK[Iterator, List]](_.toList))

  implicit val vectorTokenizer: Tokenizer[Vector, RomanizedTextElement] =
    iteratorTokenizer.mapK(λ[FunctionK[Iterator, Vector]](_.toVector))

  private val iteratorUntokenizer: Untokenizer[Iterator, RomanizedTextElement] =
    Untokenizer.instance { input =>
      input.map(_.contents).mkString
    }

  implicit val listUntokenizer: Untokenizer[List, RomanizedTextElement] =
    iteratorUntokenizer.contramapK(λ[FunctionK[List, Iterator]](_.iterator))

  implicit val vectorUntokenizer: Untokenizer[Vector, RomanizedTextElement] =
    iteratorUntokenizer.contramapK(λ[FunctionK[Vector, Iterator]](_.iterator))

}
