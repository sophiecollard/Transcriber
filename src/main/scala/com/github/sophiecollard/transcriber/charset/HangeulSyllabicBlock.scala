package com.github.sophiecollard.transcriber.charset

sealed trait HangeulSyllabicBlock

object HangeulSyllabicBlock {

  final case class TwoLetter(
    consonant: HangeulChar.Consonant,
    vowel: HangeulChar.Vowel
  ) extends HangeulSyllabicBlock

  final case class ThreeLetter(
    initialConsonant: HangeulChar.Consonant,
    vowel: HangeulChar.Vowel,
    finalConsonant: HangeulChar.Consonant
  ) extends HangeulSyllabicBlock

}
