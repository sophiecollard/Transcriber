package com.github.sophiecollard.transliterator.model.hangeul

import com.github.sophiecollard.transliterator.util.typeclasses.{Monoid, Parser}

final case class HangeulText(words: Vector[HangeulWord])

object HangeulText {

  implicit val monoid: Monoid[HangeulText] = new Monoid[HangeulText] {
    override def empty: HangeulText =
      HangeulText(Vector.empty)

    override def combine(x: HangeulText, y: HangeulText): HangeulText =
      HangeulText(x.words ++ y.words)
  }

  implicit val parser: Parser[HangeulText] =
    Parser.instance(_ => throw new RuntimeException("not implemented"))

}
