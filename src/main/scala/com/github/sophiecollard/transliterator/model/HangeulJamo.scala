package com.github.sophiecollard.transliterator.model

sealed abstract class HangeulJamo(char: Char, val index: Int)

object HangeulJamo {

  sealed abstract class Initial(char: Char, index: Int) extends HangeulJamo(char, index)

  object Initial {

    final case object ㄱ extends Initial('ᄀ', 0)
    final case object ㄲ extends Initial('ᄁ', 1)
    final case object ㄴ extends Initial('ᄂ', 2)
    final case object ㄷ extends Initial('ᄃ', 3)
    final case object ㄸ extends Initial('ᄄ', 4)
    final case object ㄹ extends Initial('ᄅ', 5)
    final case object ㅁ extends Initial('ᄆ', 6)
    final case object ㅂ extends Initial('ᄇ', 7)
    final case object ㅃ extends Initial('ᄈ', 8)
    final case object ㅅ extends Initial('ᄉ', 9)
    final case object ㅆ extends Initial('ᄊ', 10)
    final case object ㅇ extends Initial('ᄋ', 11)
    final case object ㅈ extends Initial('ᄌ', 12)
    final case object ㅉ extends Initial('ᄍ', 13)
    final case object ㅊ extends Initial('ᄎ', 14)
    final case object ㅋ extends Initial('ᄏ', 15)
    final case object ㅌ extends Initial('ᄐ', 16)
    final case object ㅍ extends Initial('ᄑ', 17)
    final case object ㅎ extends Initial('ᄒ', 18)

    def fromChar(char: Char): Option[Initial] =
      char match {
        case 'ᄀ' => Some(ㄱ)
        case 'ᄁ' => Some(ㄲ)
        case 'ᄂ' => Some(ㄴ)
        case 'ᄃ' => Some(ㄷ)
        case 'ᄄ' => Some(ㄸ)
        case 'ᄅ' => Some(ㄹ)
        case 'ᄆ' => Some(ㅁ)
        case 'ᄇ' => Some(ㅂ)
        case 'ᄈ' => Some(ㅃ)
        case 'ᄉ' => Some(ㅅ)
        case 'ᄊ' => Some(ㅆ)
        case 'ᄋ' => Some(ㅇ)
        case 'ᄌ' => Some(ㅈ)
        case 'ᄍ' => Some(ㅉ)
        case 'ᄎ' => Some(ㅊ)
        case 'ᄏ' => Some(ㅋ)
        case 'ᄐ' => Some(ㅌ)
        case 'ᄑ' => Some(ㅍ)
        case 'ᄒ' => Some(ㅎ)
        case _   => None
      }

  }

  sealed abstract class Medial(char: Char, index: Int) extends HangeulJamo(char, index)

  object Medial {

    final case object ㅏ extends Medial('ᅡ', 0)
    final case object ㅐ extends Medial('ᅢ', 1)
    final case object ㅑ extends Medial('ᅣ', 2)
    final case object ㅒ extends Medial('ᅤ', 3)
    final case object ㅓ extends Medial('ᅥ', 4)
    final case object ㅔ extends Medial('ᅦ', 5)
    final case object ㅕ extends Medial('ᅧ', 6)
    final case object ㅖ extends Medial('ᅨ', 7)
    final case object ㅗ extends Medial('ᅩ', 8)
    final case object ㅘ extends Medial('ᅪ', 9)
    final case object ㅙ extends Medial('ᅫ', 10)
    final case object ㅚ extends Medial('ᅬ', 11)
    final case object ㅛ extends Medial('ᅭ', 12)
    final case object ㅜ extends Medial('ᅮ', 13)
    final case object ㅝ extends Medial('ᅯ', 14)
    final case object ㅞ extends Medial('ᅰ', 15)
    final case object ㅟ extends Medial('ᅱ', 16)
    final case object ㅠ extends Medial('ᅲ', 17)
    final case object ㅡ extends Medial('ᅳ', 18)
    final case object ㅢ extends Medial('ᅴ', 19)
    final case object ㅣ extends Medial('ᅵ', 20)

    def fromChar(char: Char): Option[Medial] =
      char match {
        case 'ᅡ' => Some(ㅏ)
        case 'ᅢ' => Some(ㅐ)
        case 'ᅣ' => Some(ㅑ)
        case 'ᅤ' => Some(ㅒ)
        case 'ᅥ' => Some(ㅓ)
        case 'ᅦ' => Some(ㅔ)
        case 'ᅧ' => Some(ㅕ)
        case 'ᅨ' => Some(ㅖ)
        case 'ᅩ' => Some(ㅗ)
        case 'ᅪ' => Some(ㅘ)
        case 'ᅫ' => Some(ㅙ)
        case 'ᅬ' => Some(ㅚ)
        case 'ᅭ' => Some(ㅛ)
        case 'ᅮ' => Some(ㅜ)
        case 'ᅯ' => Some(ㅝ)
        case 'ᅰ' => Some(ㅞ)
        case 'ᅱ' => Some(ㅟ)
        case 'ᅲ' => Some(ㅠ)
        case 'ᅳ' => Some(ㅡ)
        case 'ᅴ' => Some(ㅢ)
        case 'ᅵ' => Some(ㅣ)
        case _   => None
      }

  }

  sealed abstract class Final(char: Char, index: Int) extends HangeulJamo(char, index)

  object Final {

    final case object ㄱ extends Final('ᆨ', 1)
    final case object ㄲ extends Final('ᆩ', 2)
    final case object ㄳ extends Final('ᆪ', 3)
    final case object ㄴ extends Final('ᆫ', 4)
    final case object ㄵ extends Final('ᆬ', 5)
    final case object ㄶ extends Final('ᆭ', 6)
    final case object ㄷ extends Final('ᆮ', 7)
    final case object ㄹ extends Final('ᆯ', 8)
    final case object ㄺ extends Final('ᆰ', 9)
    final case object ㄻ extends Final('ᆱ', 10)
    final case object ㄼ extends Final('ᆲ', 11)
    final case object ㄽ extends Final('ᆳ', 12)
    final case object ㄾ extends Final('ᆴ', 13)
    final case object ㄿ extends Final('ᆵ', 14)
    final case object ㅀ extends Final('ᆶ', 15)
    final case object ㅁ extends Final('ᆷ', 16)
    final case object ㅂ extends Final('ᆸ', 17)
    final case object ㅄ extends Final('ᆹ', 18)
    final case object ㅅ extends Final('ᆺ', 19)
    final case object ㅆ extends Final('ᆻ', 20)
    final case object ㅇ extends Final('ᆼ', 21)
    final case object ㅈ extends Final('ᆽ', 22)
    final case object ㅊ extends Final('ᆾ', 23)
    final case object ㅋ extends Final('ᆿ', 24)
    final case object ㅌ extends Final('ᇀ', 25)
    final case object ㅍ extends Final('ᇁ', 26)
    final case object ㅎ extends Final('ᇂ', 27)

    def fromChar(char: Char): Option[Final] =
      char match {
        case 'ᆨ' => Some(ㄱ)
        case 'ᆩ' => Some(ㄲ)
        case 'ᆪ' => Some(ㄳ)
        case 'ᆫ' => Some(ㄴ)
        case 'ᆬ' => Some(ㄵ)
        case 'ᆭ' => Some(ㄶ)
        case 'ᆮ' => Some(ㄷ)
        case 'ᆯ' => Some(ㄹ)
        case 'ᆰ' => Some(ㄺ)
        case 'ᆱ' => Some(ㄻ)
        case 'ᆲ' => Some(ㄼ)
        case 'ᆳ' => Some(ㄽ)
        case 'ᆴ' => Some(ㄾ)
        case 'ᆵ' => Some(ㄿ)
        case 'ᆶ' => Some(ㅀ)
        case 'ᆷ' => Some(ㅁ)
        case 'ᆸ' => Some(ㅂ)
        case 'ᆹ' => Some(ㅄ)
        case 'ᆺ' => Some(ㅅ)
        case 'ᆻ' => Some(ㅆ)
        case 'ᆼ' => Some(ㅇ)
        case 'ᆽ' => Some(ㅈ)
        case 'ᆾ' => Some(ㅊ)
        case 'ᆿ' => Some(ㅋ)
        case 'ᇀ' => Some(ㅌ)
        case 'ᇁ' => Some(ㅍ)
        case 'ᇂ' => Some(ㅎ)
        case _   => None
      }

  }

}
