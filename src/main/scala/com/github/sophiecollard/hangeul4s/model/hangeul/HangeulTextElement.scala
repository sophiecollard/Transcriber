package com.github.sophiecollard.hangeul4s.model.hangeul

import cats.data.NonEmptyVector
import cats.instances.either._
import cats.instances.vector._
import cats.syntax.either._
import cats.syntax.traverse._
import cats.syntax.validated._
import com.github.sophiecollard.hangeul4s.encoding.Decoder
import com.github.sophiecollard.hangeul4s.error.ParsingError
import com.github.sophiecollard.hangeul4s.parsing.{AccumulativeParser, Parser}
import com.github.sophiecollard.hangeul4s.syntax.either._

import scala.util.matching.Regex

sealed trait HangeulTextElement

object HangeulTextElement {

  final case class Captured(syllabicBlocks: NonEmptyVector[HangeulSyllabicBlock]) extends HangeulTextElement

  object Captured {
    def fromSyllabicBlocks(b: HangeulSyllabicBlock, bs: HangeulSyllabicBlock*): Captured =
      Captured(NonEmptyVector(b, bs.toVector))

    private [hangeul] val failFastParser: Parser[Captured] =
      Parser.instance[Captured] { input =>
        input
          .toVector
          .map(Decoder[Char, HangeulSyllabicBlock].decode)
          .map(_.leftMap[ParsingError](e => ParsingError.ParsingFailedWithDecodingErrors(input, NonEmptyVector.one(e))))
          .sequence
          .flatMap(NonEmptyVector.fromVector(_).toRight(ParsingError.Empty))
          .map(Captured(_))
      }

    private [hangeul] val accumulativeParser: AccumulativeParser[Captured] =
      AccumulativeParser.instance[Captured] { input =>
        input
          .toVector
          .map(Decoder[Char, HangeulSyllabicBlock].decode(_).toValidatedNev)
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
    private [hangeul] val failFastParser: Parser[NotCaptured] =
      Parser.instance[NotCaptured] { input =>
        unvalidatedFrom(input).asRight[ParsingError]
      }

    // TODO validate input
    private [hangeul] val accumulativeParser: AccumulativeParser[NotCaptured] =
      AccumulativeParser.instance[NotCaptured] { input =>
        unvalidatedFrom(input).valid[NonEmptyVector[ParsingError]]
      }
  }

  private val failFastElementParser: Parser[HangeulTextElement] =
    Parser.instance { input =>
      Captured.failFastParser.parse(input) orElse
        NotCaptured.failFastParser.parse(input)
    }

  private val accumulativeElementParser: AccumulativeParser[HangeulTextElement] =
    AccumulativeParser.instance { input =>
      Captured.accumulativeParser.parse(input) orElse
        NotCaptured.accumulativeParser.parse(input)
    }

  private val splittingRegex: Regex = "([\uAC00-\uD7AF]+)|([^\\s\uAC00-\uD7AF]+)".r

  implicit val failFastParser: Parser[Vector[HangeulTextElement]] =
    Parser.instance { input =>
      splittingRegex
        .findAllIn(input)
        .toVector
        .map(failFastElementParser.parse)
        .sequence
    }

  implicit val accumulativeParser: AccumulativeParser[Vector[HangeulTextElement]] =
    AccumulativeParser.instance { input =>
      splittingRegex
        .findAllIn(input)
        .toVector.map(accumulativeElementParser.parse)
        .sequence
    }

}
