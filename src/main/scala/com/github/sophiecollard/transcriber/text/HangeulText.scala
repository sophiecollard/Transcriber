package com.github.sophiecollard.transcriber.text

import com.github.sophiecollard.transcriber.charset.HangeulSyllabicBlock
import com.github.sophiecollard.transcriber.util.Monoid

final case class HangeulText(contents: Vector[HangeulSyllabicBlock])

object HangeulText {

  implicit val monoid: Monoid[HangeulText] = new Monoid[HangeulText] {
    override def empty: HangeulText =
      HangeulText(Vector.empty)

    override def combine(x: HangeulText, y: HangeulText): HangeulText =
      HangeulText(x.contents ++ y.contents)
  }

  implicit val parser: Parser[HangeulText] =
    Parser.instance(_ => throw new RuntimeException("not implemented"))

}
