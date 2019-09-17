package com.github.sophiecollard.transliterator.model

sealed trait HangeulSyllabicBlock {

  def initial: HangeulJamo.Initial

  def medial: HangeulJamo.Medial

  def maybeFinal: Option[HangeulJamo.Final] = this match {
    case HangeulSyllabicBlock.TwoLetter(_, _)      => None
    case HangeulSyllabicBlock.ThreeLetter(_, _, f) => Some(f)
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
