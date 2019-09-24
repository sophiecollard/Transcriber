package com.github.sophiecollard.hangeul4s.model.hangeul

import com.github.sophiecollard.hangeul4s.encoding.instances.HangeulSyllabicBlockCodec
import com.github.sophiecollard.hangeul4s.error.{DecodingError, ParsingError}
import com.github.sophiecollard.hangeul4s.instances.vector._
import com.github.sophiecollard.hangeul4s.parsing.SequentialParser
import com.github.sophiecollard.hangeul4s.syntax.either.{EitherConstructors, EitherOps}
import com.github.sophiecollard.hangeul4s.syntax.traverse.TraverseOps
import com.github.sophiecollard.hangeul4s.util.types.{NonEmptyVector, ValidatedNev}

sealed trait HangeulTextElement

object HangeulTextElement {

  final case class Captured(syllabicBlocks: NonEmptyVector[HangeulSyllabicBlock]) extends HangeulTextElement

  object Captured {
    def fromSyllabicBlocks(b: HangeulSyllabicBlock, bs: HangeulSyllabicBlock*): Captured =
      Captured(NonEmptyVector(b, bs.toVector))

    val parser: SequentialParser[Captured] =
      SequentialParser.instance[Captured] { input =>
        input
          .map(HangeulSyllabicBlockCodec.decode(_).toValidatedNev)
          .toVector
          .traverse[ValidatedNev[DecodingError, ?], HangeulSyllabicBlock](identity)
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
    val parser: SequentialParser[NotCaptured] =
      SequentialParser.instance[NotCaptured] { input =>
        unvalidatedFrom(input).right[ParsingError, NotCaptured]
      }
  }

  val parser: SequentialParser[HangeulTextElement] =
    SequentialParser.instance[HangeulTextElement] { input =>
      Captured.parser.parse(input) orElse
        NotCaptured.parser.parse(input)
    }

}
