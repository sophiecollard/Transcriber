package com.github.sophiecollard.hangeul4s.model.hangeul

import cats.data.NonEmptyVector
import cats.instances.vector._
import cats.syntax.traverse._
import com.github.sophiecollard.hangeul4s.error.ParsingError
import com.github.sophiecollard.hangeul4s.parsing.AccumulativeParser
import com.github.sophiecollard.hangeul4s.syntax.either.EitherOps
import com.github.sophiecollard.hangeul4s.syntax.option.OptionOps

import scala.util.matching.Regex

final case class HangeulText(elements: NonEmptyVector[HangeulTextElement])

object HangeulText {

  def fromElements(e: HangeulTextElement, es: HangeulTextElement*): HangeulText =
    HangeulText(NonEmptyVector(e, es.toVector))

  private val splittingRegex: Regex = "([\uAC00-\uD7AF]+)|([^\\s\uAC00-\uD7AF]+)".r

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
