package com.github.sophiecollard.transliterator.model

sealed trait HangeulSyllabicBlock {

  import HangeulSyllabicBlock._

  def getInitial: HangeulLetter.Consonant = this match {
    case IM(i, _)             => i
    case IMM(i, _, _)         => i
    case IMF(i, _, _)         => i
    case IMMF(i, _, _, _)     => i
    case IMFF(i, _, _, _)     => i
    case IMMFF(i, _, _, _, _) => i
  }

  def getFinal: Option[HangeulLetter.Consonant] = this match {
    case IM(_, _)               => None
    case IMM(_, _, _)           => None
    case IMF(_, _, f)           => Some(f)
    case IMMF(_, _, _, f)       => Some(f)
    case IMFF(_, _, f1, f2)     => Some(f2)
    case IMMFF(_, _, _, f1, f2) => Some(f2)
  }

}

object HangeulSyllabicBlock {

  final case class IM(
    initial: HangeulLetter.Consonant,
    medial: HangeulLetter.Vowel
  ) extends HangeulSyllabicBlock

  final case class IMM(
    initial: HangeulLetter.Consonant,
    medial: HangeulLetter.Vowel,
    secondMedial: HangeulLetter.Vowel
  ) extends HangeulSyllabicBlock

  final case class IMF(
    initial: HangeulLetter.Consonant,
    medial: HangeulLetter.Vowel,
    `final`: HangeulLetter.Consonant
  ) extends HangeulSyllabicBlock

  final case class IMMF(
    initial: HangeulLetter.Consonant,
    medial: HangeulLetter.Vowel,
    secondMedial: HangeulLetter.Vowel,
    `final`: HangeulLetter.Consonant
  ) extends HangeulSyllabicBlock

  final case class IMFF(
    initial: HangeulLetter.Consonant,
    medial: HangeulLetter.Vowel,
    `final`: HangeulLetter.Consonant,
    secondFinal: HangeulLetter.Consonant
  ) extends HangeulSyllabicBlock

  final case class IMMFF(
    initial: HangeulLetter.Consonant,
    medial: HangeulLetter.Vowel,
    secondMedial: HangeulLetter.Vowel,
    `final`: HangeulLetter.Consonant,
    secondFinal: HangeulLetter.Consonant
  ) extends HangeulSyllabicBlock

}
