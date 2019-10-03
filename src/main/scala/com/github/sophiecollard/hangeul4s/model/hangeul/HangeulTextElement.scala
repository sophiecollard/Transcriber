package com.github.sophiecollard.hangeul4s.model.hangeul

import cats.data.NonEmptyVector
import cats.instances.vector._
import cats.syntax.either._
import cats.syntax.traverse._
import com.github.sophiecollard.hangeul4s.encoding.instances.HangeulSyllabicBlockCodec
import com.github.sophiecollard.hangeul4s.error.ParsingError
import com.github.sophiecollard.hangeul4s.parsing.Parser
import com.github.sophiecollard.hangeul4s.syntax.either._

sealed trait HangeulTextElement

object HangeulTextElement {

  final case class Captured(syllabicBlocks: NonEmptyVector[HangeulSyllabicBlock]) extends HangeulTextElement

  object Captured {
    def fromSyllabicBlocks(b: HangeulSyllabicBlock, bs: HangeulSyllabicBlock*): Captured =
      Captured(NonEmptyVector(b, bs.toVector))

    val parser: Parser[Captured] =
      Parser.instance[Captured] { input =>
        input
          .map(HangeulSyllabicBlockCodec.decode(_).toValidatedNev)
          .toVector
          .sequence
          .toEither
          .leftMap[ParsingError](ParsingError.ParsingFailedWithDecodingErrors(input, _))
          .flatMap(NonEmptyVector.fromVector(_).toRight(ParsingError.Empty))
          .map(Captured(_))
      }
  }

  sealed abstract case class NotCaptured(contents: String) extends HangeulTextElement

  object NotCaptured {
    private [hangeul] def unvalidatedFrom(input: String): NotCaptured =
      new NotCaptured(input) {}

    // TODO validate input
    val parser: Parser[NotCaptured] =
      Parser.instance[NotCaptured] { input =>
        unvalidatedFrom(input).asRight[ParsingError]
      }
  }

  val parser: Parser[HangeulTextElement] =
    Parser.instance[HangeulTextElement] { input =>
      Captured.parser.parse(input) orElse
        NotCaptured.parser.parse(input)
    }

}
