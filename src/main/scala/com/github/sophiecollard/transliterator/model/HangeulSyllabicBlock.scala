package com.github.sophiecollard.transliterator.model

sealed trait HangeulSyllabicBlock {

  import HangeulSyllabicBlock._

  def getInitial: HangeulJamo.Initial = this match {
    case TwoLetter(i, _)      => i
    case ThreeLetter(i, _, _) => i
  }

  def getFinal: Option[HangeulJamo.Final] = this match {
    case TwoLetter(_, _)      => None
    case ThreeLetter(_, _, f) => Some(f)
  }

}

object HangeulSyllabicBlock {

  final case class TwoLetter(
    initial: HangeulJamo.Initial,
    medial: HangeulJamo.Medial
  ) extends HangeulSyllabicBlock

  final case class ThreeLetter(
    initial: HangeulJamo.Initial,
    medial: HangeulJamo.Medial,
    `final`: HangeulJamo.Final
  ) extends HangeulSyllabicBlock

}
