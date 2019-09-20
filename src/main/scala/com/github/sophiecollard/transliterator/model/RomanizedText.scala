package com.github.sophiecollard.transliterator.model

import com.github.sophiecollard.transliterator.util.typeclasses.{Monoid, Parser}

final case class RomanizedText(words: Vector[RomanizedWord])

object RomanizedText {

  implicit val monoid: Monoid[RomanizedText] = new Monoid[RomanizedText] {
    override def empty: RomanizedText =
      RomanizedText(Vector.empty)

    override def combine(x: RomanizedText, y: RomanizedText): RomanizedText =
      RomanizedText(x.words ++ y.words)
  }

  implicit val parser: Parser[RomanizedText] =
    Parser.instance(_ => throw new RuntimeException("not implemented"))

}
