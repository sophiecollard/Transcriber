package com.github.sophiecollard.hangeul4s.model.hangeul

import cats.data.NonEmptyVector
import cats.instances.either._
import cats.instances.vector._
import cats.syntax.either._
import cats.syntax.traverse._
import cats.syntax.validated._
import com.github.sophiecollard.hangeul4s.encoding.instances.HangeulSyllabicBlockCodec
import com.github.sophiecollard.hangeul4s.error.ParsingError
import com.github.sophiecollard.hangeul4s.parsing._
import com.github.sophiecollard.hangeul4s.syntax.either._

import scala.util.matching.Regex
import scala.util.parsing.combinator._

sealed trait HangeulTextElement

object HangeulTextElement {

  final case class Captured(syllabicBlocks: NonEmptyVector[HangeulSyllabicBlock]) extends HangeulTextElement

  object Captured {
    def fromSyllabicBlocks(b: HangeulSyllabicBlock, bs: HangeulSyllabicBlock*): Captured =
      Captured(NonEmptyVector(b, bs.toVector))

    private [hangeul] val failFastParser: FailFastParser[Captured] =
      FailFastParser.instance[Captured] { input =>
        input
          .toVector
          .map(HangeulSyllabicBlockCodec.decode)
          .map(_.leftMap[ParsingError](e => ParsingError.ParsingFailedWithDecodingErrors(input, NonEmptyVector.one(e))))
          .sequence
          .flatMap(NonEmptyVector.fromVector(_).toRight(ParsingError.Empty))
          .map(Captured(_))
      }

    private [hangeul] val accumulativeParser: AccumulativeParser[Captured] =
      AccumulativeParser.instance[Captured] { input =>
        input
          .toVector
          .map(HangeulSyllabicBlockCodec.decode(_).toValidatedNev)
          .map(_.leftMap(e => NonEmptyVector.one[ParsingError](ParsingError.ParsingFailedWithDecodingErrors(input, e))))
          .sequence
          .andThen(NonEmptyVector.fromVector(_).toRight(ParsingError.Empty).toValidatedNev)
          .map(Captured(_))
      }
  }

  sealed abstract case class NotCaptured(contents: String) extends HangeulTextElement

  object NotCaptured {
    private [hangeul] def unvalidatedFrom(input: String): NotCaptured =
      new NotCaptured(input) {}

    // TODO validate input
    private [hangeul] val failFastParser: FailFastParser[NotCaptured] =
      FailFastParser.instance[NotCaptured] { input =>
        unvalidatedFrom(input).asRight[ParsingError]
      }

    // TODO validate input
    private [hangeul] val accumulativeParser: AccumulativeParser[NotCaptured] =
      AccumulativeParser.instance[NotCaptured] { input =>
        unvalidatedFrom(input).valid[NonEmptyVector[ParsingError]]
      }
  }

  private val failFastElementParser: FailFastParser[HangeulTextElement] =
    FailFastParser.instance { input =>
      Captured.failFastParser.parse(input) orElse
        NotCaptured.failFastParser.parse(input)
    }

  private val accumulativeElementParser: AccumulativeParser[HangeulTextElement] =
    AccumulativeParser.instance { input =>
      Captured.accumulativeParser.parse(input) orElse
        NotCaptured.accumulativeParser.parse(input)
    }

  private val splittingRegex: Regex = "([\uAC00-\uD7AF]+)|([^\\s\uAC00-\uD7AF]+)".r

  val failFastParser: FailFastParser[Vector[HangeulTextElement]] =
    FailFastParser.instance { input =>
      splittingRegex
        .findAllIn(input)
        .toVector
        .map(failFastElementParser.parse)
        .sequence
    }

  val accumulativeParser: AccumulativeParser[Vector[HangeulTextElement]] =
    AccumulativeParser.instance { input =>
      splittingRegex
        .findAllIn(input)
        .toVector
        .map(accumulativeElementParser.parse)
        .sequence
    }

  private object RegexParser extends RegexParsers {

    private def captured: Parser[Captured] =
      """[\uAC00-\uD7AF]+""".r ^^ { matched =>
        Captured(
          NonEmptyVector.fromVectorUnsafe( // not safe
            matched
              .toCharArray
              .map(HangeulSyllabicBlockCodec.decode(_).toOption.get) // not safe
              .toVector
          )
        )
      }

    private def notCaptured: Parser[NotCaptured] =
      """[^\\s\uAC00-\uD7AF]+""".r ^^ { new NotCaptured(_) {} }

    private def element: Parser[HangeulTextElement] =
      (captured | notCaptured) ^^ { identity }

    // TODO Can this be made tail-recursive?
    // TODO This should be able to parse an empty string
    def vector: Parser[Vector[HangeulTextElement]] = element ~ opt(vector) ^^ {
      case e ~ None =>
        Vector(e)
      case e ~ Some(v) =>
        e +: v
    }

  }

  val regexParser: FailFastParser[Vector[HangeulTextElement]] =
    FailFastParser.instance { input =>
      RegexParser.parse(RegexParser.vector, input) match {
        case RegexParser.Success(matched, _) =>
          FailFastParsingResult.success(matched)
        case RegexParser.Failure(message, _) =>
          FailFastParsingResult.failure(ParsingError.RegexParsingFailure(message))
        case RegexParser.Error(message, _) =>
          FailFastParsingResult.failure(ParsingError.RegexParsingError(message))
      }
    }

}
