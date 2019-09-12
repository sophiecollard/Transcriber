package com.github.sophiecollard.transliterator.model

sealed trait HangeulSyllabicBlock

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
