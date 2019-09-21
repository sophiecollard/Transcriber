package com.github.sophiecollard.transliterator.model.hangeul

import com.github.sophiecollard.transliterator.encoding.instances.HangeulSyllabicBlockCodec
import com.github.sophiecollard.transliterator.error.{DecodingError, ParsingError}
import com.github.sophiecollard.transliterator.instances.vector._
import com.github.sophiecollard.transliterator.model.UnicodeBlock
import com.github.sophiecollard.transliterator.parsing.Parser
import com.github.sophiecollard.transliterator.syntax.either.EitherOps
import com.github.sophiecollard.transliterator.syntax.traverse.TraverseOps
import com.github.sophiecollard.transliterator.util.types.{NonEmptyVector, ValidatedNev}

sealed trait HangeulTextElement

object HangeulTextElement {

  final case class Word(syllabicBlocks: NonEmptyVector[HangeulSyllabicBlock]) extends HangeulTextElement

  object Word {
    implicit val parser: Parser[Word] =
      Parser.instance[Word] { input =>
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
    implicit val parser: Parser[Punctuation] =
      Parser.instance[Punctuation] { input =>
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
    implicit val parser: Parser[Digits] =
      Parser.instance[Digits] { input =>
        UnicodeBlock
          .validateString(input, UnicodeBlock.ASCIIDigits)
          .toEither
          .bimap(
            ParsingError.ParsingFailedWithValidationErrors(input, _),
            new Digits(_) {}
          )
      }
  }

  implicit val parser: Parser[HangeulTextElement] =
    Parser.instance[HangeulTextElement] { input =>
      Digits.parser.parse(input) orElse
        Punctuation.parser.parse(input) orElse
        Word.parser.parse(input)
    }

}
