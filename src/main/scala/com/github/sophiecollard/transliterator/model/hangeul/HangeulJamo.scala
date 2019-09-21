package com.github.sophiecollard.transliterator.model.hangeul

sealed abstract class HangeulJamo(val char: Char)

object HangeulJamo {

  sealed abstract class Initial(char: Char) extends HangeulJamo(char)

  object Initial {

    final case object ㄱ extends Initial('ᄀ')
    final case object ㄲ extends Initial('ᄁ')
    final case object ㄴ extends Initial('ᄂ')
    final case object ㄷ extends Initial('ᄃ')
    final case object ㄸ extends Initial('ᄄ')
    final case object ㄹ extends Initial('ᄅ')
    final case object ㅁ extends Initial('ᄆ')
    final case object ㅂ extends Initial('ᄇ')
    final case object ㅃ extends Initial('ᄈ')
    final case object ㅅ extends Initial('ᄉ')
    final case object ㅆ extends Initial('ᄊ')
    final case object ㅇ extends Initial('ᄋ')
    final case object ㅈ extends Initial('ᄌ')
    final case object ㅉ extends Initial('ᄍ')
    final case object ㅊ extends Initial('ᄎ')
    final case object ㅋ extends Initial('ᄏ')
    final case object ㅌ extends Initial('ᄐ')
    final case object ㅍ extends Initial('ᄑ')
    final case object ㅎ extends Initial('ᄒ')

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

  sealed abstract class Medial(char: Char) extends HangeulJamo(char)

  object Medial {

    final case object ㅏ extends Medial('ᅡ')
    final case object ㅐ extends Medial('ᅢ')
    final case object ㅑ extends Medial('ᅣ')
    final case object ㅒ extends Medial('ᅤ')
    final case object ㅓ extends Medial('ᅥ')
    final case object ㅔ extends Medial('ᅦ')
    final case object ㅕ extends Medial('ᅧ')
    final case object ㅖ extends Medial('ᅨ')
    final case object ㅗ extends Medial('ᅩ')
    final case object ㅘ extends Medial('ᅪ')
    final case object ㅙ extends Medial('ᅫ')
    final case object ㅚ extends Medial('ᅬ')
    final case object ㅛ extends Medial('ᅭ')
    final case object ㅜ extends Medial('ᅮ')
    final case object ㅝ extends Medial('ᅯ')
    final case object ㅞ extends Medial('ᅰ')
    final case object ㅟ extends Medial('ᅱ')
    final case object ㅠ extends Medial('ᅲ')
    final case object ㅡ extends Medial('ᅳ')
    final case object ㅢ extends Medial('ᅴ')
    final case object ㅣ extends Medial('ᅵ')

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

  sealed abstract class Final(char: Char) extends HangeulJamo(char)

  object Final {

    final case object ㄱ extends Final('ᆨ')
    final case object ㄲ extends Final('ᆩ')
    final case object ㄳ extends Final('ᆪ')
    final case object ㄴ extends Final('ᆫ')
    final case object ㄵ extends Final('ᆬ')
    final case object ㄶ extends Final('ᆭ')
    final case object ㄷ extends Final('ᆮ')
    final case object ㄹ extends Final('ᆯ')
    final case object ㄺ extends Final('ᆰ')
    final case object ㄻ extends Final('ᆱ')
    final case object ㄼ extends Final('ᆲ')
    final case object ㄽ extends Final('ᆳ')
    final case object ㄾ extends Final('ᆴ')
    final case object ㄿ extends Final('ᆵ')
    final case object ㅀ extends Final('ᆶ')
    final case object ㅁ extends Final('ᆷ')
    final case object ㅂ extends Final('ᆸ')
    final case object ㅄ extends Final('ᆹ')
    final case object ㅅ extends Final('ᆺ')
    final case object ㅆ extends Final('ᆻ')
    final case object ㅇ extends Final('ᆼ')
    final case object ㅈ extends Final('ᆽ')
    final case object ㅊ extends Final('ᆾ')
    final case object ㅋ extends Final('ᆿ')
    final case object ㅌ extends Final('ᇀ')
    final case object ㅍ extends Final('ᇁ')
    final case object ㅎ extends Final('ᇂ')

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
