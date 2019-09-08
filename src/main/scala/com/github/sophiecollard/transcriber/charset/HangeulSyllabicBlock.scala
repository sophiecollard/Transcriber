package com.github.sophiecollard.transcriber.charset

sealed trait HangeulSyllabicBlock

object HangeulSyllabicBlock {

  final case class TwoLetter(
    consonant: HangeulLetter.Consonant,
    vowel: HangeulLetter.Vowel
  ) extends HangeulSyllabicBlock

  final case class ThreeLetter(
    initialConsonant: HangeulLetter.Consonant,
    vowel: HangeulLetter.Vowel,
    finalConsonant: HangeulLetter.Consonant
  ) extends HangeulSyllabicBlock

}
