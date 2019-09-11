package com.github.sophiecollard.transliterator.model

sealed abstract class HangeulLetter(char: Char)

object HangeulLetter {

  sealed abstract class Consonant(char: Char) extends HangeulLetter(char)

  final case object ㄱ extends Consonant('ㄱ')
  final case object ㄲ extends Consonant('ㄲ')
  final case object ㄴ extends Consonant('ㄴ')
  final case object ㄷ extends Consonant('ㄷ')
  final case object ㄸ extends Consonant('ㄸ')
  final case object ㄹ extends Consonant('ㄹ')
  final case object ㅁ extends Consonant('ㅁ')
  final case object ㅂ extends Consonant('ㅂ')
  final case object ㅃ extends Consonant('ㅃ')
  final case object ㅅ extends Consonant('ㅅ')
  final case object ㅆ extends Consonant('ㅆ')
  final case object ㅇ extends Consonant('ㅇ')
  final case object ㅈ extends Consonant('ㅈ')
  final case object ㅉ extends Consonant('ㅉ')
  final case object ㅊ extends Consonant('ㅊ')
  final case object ㅋ extends Consonant('ㅋ')
  final case object ㅌ extends Consonant('ㅌ')
  final case object ㅍ extends Consonant('ㅍ')
  final case object ㅎ extends Consonant('ㅎ')

  sealed abstract class Vowel(char: Char) extends HangeulLetter(char)

  final case object ㅏ extends Vowel('ㅏ')
  final case object ㅐ extends Vowel('ㅐ')
  final case object ㅑ extends Vowel('ㅑ')
  final case object ㅒ extends Vowel('ㅒ')
  final case object ㅓ extends Vowel('ㅓ')
  final case object ㅔ extends Vowel('ㅔ')
  final case object ㅕ extends Vowel('ㅕ')
  final case object ㅖ extends Vowel('ㅖ')
  final case object ㅗ extends Vowel('ㅗ')
  final case object ㅘ extends Vowel('ㅘ')
  final case object ㅙ extends Vowel('ㅙ')
  final case object ㅚ extends Vowel('ㅚ')
  final case object ㅛ extends Vowel('ㅛ')
  final case object ㅜ extends Vowel('ㅜ')
  final case object ㅝ extends Vowel('ㅝ')
  final case object ㅞ extends Vowel('ㅞ')
  final case object ㅟ extends Vowel('ㅟ')
  final case object ㅠ extends Vowel('ㅠ')
  final case object ㅡ extends Vowel('ㅡ')
  final case object ㅢ extends Vowel('ㅢ')
  final case object ㅣ extends Vowel('ㅣ')

}
