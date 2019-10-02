package com.github.sophiecollard.hangeul4s.model.hangeul

import com.github.sophiecollard.hangeul4s.error.ParsingError
import com.github.sophiecollard.hangeul4s.instances.vector._
import com.github.sophiecollard.hangeul4s.parsing.AccumulativeParser
import com.github.sophiecollard.hangeul4s.syntax.either.EitherOps
import com.github.sophiecollard.hangeul4s.syntax.option.OptionOps
import com.github.sophiecollard.hangeul4s.syntax.traverse.SequenceOps
import com.github.sophiecollard.hangeul4s.util.types.NonEmptyVector

import scala.util.matching.Regex

final case class HangeulText(elements: NonEmptyVector[HangeulTextElement])

object HangeulText {

  def fromElements(e: HangeulTextElement, es: HangeulTextElement*): HangeulText =
    HangeulText(NonEmptyVector(e, es.toVector))

  private val splittingRegex: Regex = "([\uAC00-\uD7AF]+)|([^\uAC00-\uD7AF]+)".r

  val parser: AccumulativeParser[HangeulText] =
    AccumulativeParser.instance[HangeulText] { input =>
      splittingRegex
        .findAllIn(input).toVector
        .map(HangeulTextElement.parser.parse(_).toValidatedNev)
        .sequence
        .flatMap(NonEmptyVector.fromVector(_).toValid[ParsingError](ParsingError.Empty))
        .map(HangeulText.apply)
    }

}
