package com.github.sophiecollard.transliterator.model.hangeul

sealed trait HangeulSyllabicBlock {

  def initial: HangeulJamo.Initial

  def medial: HangeulJamo.Medial

  def maybeFinal: Option[HangeulJamo.Final] = this match {
    case HangeulSyllabicBlock.TwoLetter(_, _)      => None
    case HangeulSyllabicBlock.ThreeLetter(_, _, f) => Some(f)
  }

  def charSequence: CharSequence = this match {
    case HangeulSyllabicBlock.TwoLetter(i, m)      => s"${i.char}${m.char}"
    case HangeulSyllabicBlock.ThreeLetter(i, m, f) => s"${i.char}${m.char}${f.char}"
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
