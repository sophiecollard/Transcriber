package hangeul4s.model.romanization

import cats.data.NonEmptyVector
import hangeul4s.model.hangeul.HangeulTextElement
import hangeul4s.parsing.{Token, Tokenizer, Unparser, Untokenizer}

import scala.util.matching.Regex

sealed trait RomanizedTextElement

object RomanizedTextElement {

  final case class Captured(letters: NonEmptyVector[RomanLetter]) extends RomanizedTextElement {

    override def toString: String = {
      val encodedLetters = letters.map(RomanLetter.charEncoder.encode).toVector.mkString
      s"hangeul4s.model.romanization.RomanizedTextElement.Captured($encodedLetters)"
    }

  }

  object Captured {
    def fromLetters(l: RomanLetter, ls: RomanLetter*): Captured =
      Captured(NonEmptyVector(l, ls.toVector))

    private [romanization] val regex: Regex = "[A-EG-PR-UWYa-eg-pr-uwy]+".r
  }

  sealed abstract case class NotCaptured(contents: String) extends RomanizedTextElement {

    override def toString: String =
      s"hangeul4s.model.romanization.RomanizedTextElement.NotCaptured($contents)"

  }

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

  implicit val iteratorTokenizer: Tokenizer[Iterator, RomanizedTextElement] =
    Tokenizer.instance { input =>
      s"(${Captured.regex})|(${NotCaptured.regex})".r
        .findAllIn(input)
        .map(Token.apply[RomanizedTextElement])
    }

  implicit val iteratorUntokenizer: Untokenizer[Iterator, RomanizedTextElement] =
    Untokenizer.instance { input =>
      input.map(_.contents).mkString
    }

}
