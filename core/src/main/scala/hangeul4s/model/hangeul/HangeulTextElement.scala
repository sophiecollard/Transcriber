package hangeul4s.model.hangeul

import cats.data.NonEmptyVector
import cats.instances.either._
import cats.instances.vector._
import cats.syntax.either._ // required for orElse method in Scala 2.11 and 2.12
import cats.syntax.traverse._
import hangeul4s.encoding.Decoder
import hangeul4s.error.ParsingFailure
import hangeul4s.parsing._
import hangeul4s.syntax.either._
import hangeul4s.syntax.option._

import scala.util.matching.Regex

sealed trait HangeulTextElement

object HangeulTextElement {

  final case class Captured(syllabicBlocks: NonEmptyVector[HangeulSyllabicBlock]) extends HangeulTextElement

  object Captured {
    def fromSyllabicBlocks(b: HangeulSyllabicBlock, bs: HangeulSyllabicBlock*): Captured =
      Captured(NonEmptyVector(b, bs.toVector))

    private [hangeul] val failFastParser: Parser[String, Captured] =
      Parser.instance[String, Captured] { input =>
        input
          .toVector
          .map(Decoder[Char, HangeulSyllabicBlock].decode)
          .sequence
          .flatMap(NonEmptyVector.fromVector(_).toRight(ParsingFailure.EmptyInput))
          .map(Captured(_))
      }

    private [hangeul] val accumulativeParser: AccumulativeParser[String, Captured] =
      AccumulativeParser.instance[String, Captured] { input =>
        input
          .toVector
          .map(Decoder[Char, HangeulSyllabicBlock].decode(_).toValidatedNev)
          .sequence
          .andThen(NonEmptyVector.fromVector(_).toRight(ParsingFailure.EmptyInput).toValidatedNev)
          .map(Captured(_))
      }

    private [hangeul] val regex: Regex = "[\uAC00-\uD7AF]+".r
  }

  sealed abstract case class NotCaptured(contents: String) extends HangeulTextElement

  object NotCaptured {
    def fromString(input: String): Option[NotCaptured] =
      s"^($regex)$$".r
        .findFirstIn(input)
        .map(new NotCaptured(_) {})

    private [hangeul] def unvalidatedFromString(input: String): NotCaptured =
      new NotCaptured(input) {}

    private [hangeul] val failFastParser: Parser[String, NotCaptured] =
      Parser.instance[String, NotCaptured] { input =>
        fromString(input).toRight[ParsingFailure](ParsingFailure.FailedToMatchRegex(input, regex))
      }

    private [hangeul] val accumulativeParser: AccumulativeParser[String, NotCaptured] =
      AccumulativeParser.instance[String, NotCaptured] { input =>
        fromString(input).toValid[ParsingFailure](ParsingFailure.FailedToMatchRegex(input, regex))
      }

    private [hangeul] val regex: Regex = "[^\uAC00-\uD7AF]+".r
  }

  implicit val failFastParser: Parser[String, HangeulTextElement] =
    Parser.instance { input =>
      Captured.failFastParser.parse(input) orElse
        NotCaptured.failFastParser.parse(input)
    }

  implicit val accumulativeParser: AccumulativeParser[String, HangeulTextElement] =
    AccumulativeParser.instance { input =>
      Captured.accumulativeParser.parse(input) orElse
        NotCaptured.accumulativeParser.parse(input)
    }

  implicit val unparser: Unparser[HangeulTextElement, String] =
    Unparser.instance {
      case Captured(syllabicBlocks) => syllabicBlocks.toVector.map(_.toString).mkString
      case NotCaptured(contents)    => contents
    }

  implicit val vectorTokenizer: Tokenizer[Vector, HangeulTextElement] =
    Tokenizer.instance { input =>
      s"(${Captured.regex})|(${NotCaptured.regex})".r
        .findAllIn(input)
        .map(Token.apply[HangeulTextElement])
        .toVector
    }

  implicit val vectorUntokenizer: Untokenizer[Vector, HangeulTextElement] =
    Untokenizer.instance { input =>
      input.map(_.contents).mkString
    }

}
