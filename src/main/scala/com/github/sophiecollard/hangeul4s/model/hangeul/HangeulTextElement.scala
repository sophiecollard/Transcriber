package com.github.sophiecollard.hangeul4s.model.hangeul

import com.github.sophiecollard.hangeul4s.encoding.instances.HangeulSyllabicBlockCodec
import com.github.sophiecollard.hangeul4s.error.{DecodingError, ParsingError}
import com.github.sophiecollard.hangeul4s.instances.vector._
import com.github.sophiecollard.hangeul4s.model.UnicodeBlock
import com.github.sophiecollard.hangeul4s.parsing.SequentialParser
import com.github.sophiecollard.hangeul4s.syntax.either.EitherOps
import com.github.sophiecollard.hangeul4s.syntax.traverse.TraverseOps
import com.github.sophiecollard.hangeul4s.util.types.{NonEmptyVector, ValidatedNev}

sealed trait HangeulTextElement

object HangeulTextElement {

  final case class Word(syllabicBlocks: NonEmptyVector[HangeulSyllabicBlock]) extends HangeulTextElement

  object Word {
    val parser: SequentialParser[Word] =
      SequentialParser.instance[Word] { input =>
        input
          .map(HangeulSyllabicBlockCodec.decode(_).toValidatedNev)
          .toVector
          .traverse[ValidatedNev[DecodingError, ?], HangeulSyllabicBlock](identity)
          .toEither
          .leftMap[ParsingError](ParsingError.ParsingFailedWithDecodingErrors(input, _))
          .flatMap(NonEmptyVector.fromVector(_).toRight(ParsingError.Empty))
          .map(Word(_))
      }
  }

  sealed abstract case class Punctuation(contents: String) extends HangeulTextElement

  object Punctuation {
    private [hangeul] def unvalidatedFrom(input: String): Punctuation =
      new Punctuation(input) {}

    val parser: SequentialParser[Punctuation] =
      SequentialParser.instance[Punctuation] { input =>
        UnicodeBlock
          .validateString(input, UnicodeBlock.ASCIIPunctuation)
          .toEither
          .bimap(
            ParsingError.ParsingFailedWithValidationErrors(input, _),
            new Punctuation(_) {}
          )
      }
  }

  sealed abstract case class Digits(contents: String) extends HangeulTextElement

  object Digits {
    private [hangeul] def unvalidatedFrom(input: String): Digits =
      new Digits(input) {}

    val parser: SequentialParser[Digits] =
      SequentialParser.instance[Digits] { input =>
        UnicodeBlock
          .validateString(input, UnicodeBlock.ASCIIDigits)
          .toEither
          .bimap(
            ParsingError.ParsingFailedWithValidationErrors(input, _),
            new Digits(_) {}
          )
      }
  }

  val parser: SequentialParser[HangeulTextElement] =
    SequentialParser.instance[HangeulTextElement] { input =>
      Digits.parser.parse(input) orElse
        Punctuation.parser.parse(input) orElse
        Word.parser.parse(input)
    }

}
