package transliterator4s.korean.model.romanization

import cats.data.NonEmptyVector
import transliterator4s.korean.model.hangeul.HangeulTextElement
import transliterator4s.parsing.{Token, Tokenizer, Unparser, Untokenizer}

import scala.util.matching.Regex

sealed trait RomanizedTextElement

object RomanizedTextElement {

  final case class Captured(letters: NonEmptyVector[RomanLetter]) extends RomanizedTextElement

  object Captured {
    def fromLetters(l: RomanLetter, ls: RomanLetter*): Captured =
      Captured(NonEmptyVector(l, ls.toVector))

    private [romanization] val regex: Regex = "[A-Za-z]+".r
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

    private [romanization] val regex: Regex = "[^A-Za-z]+".r
  }

  implicit val unparser: Unparser[RomanizedTextElement, String] =
    Unparser.instance {
      case Captured(letters)     => letters.toVector.map(_.char).mkString
      case NotCaptured(contents) => contents
    }

  implicit val vectorTokenizer: Tokenizer[Vector, RomanizedTextElement] =
    Tokenizer.instance { input =>
      s"(${Captured.regex})|(${NotCaptured.regex})".r
        .findAllIn(input)
        .map(Token.apply[RomanizedTextElement])
        .toVector
    }

  implicit val vectorUntokenizer: Untokenizer[Vector, RomanizedTextElement] =
    Untokenizer.instance { input =>
      input.map(_.contents).mkString
    }

}
