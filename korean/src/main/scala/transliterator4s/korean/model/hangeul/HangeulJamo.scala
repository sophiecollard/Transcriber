package transliterator4s.korean.model.hangeul

import transliterator4s.encoding.Decoder
import transliterator4s.error.DecodingFailure

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

    implicit val charDecoder: Decoder[Char, Initial] =
      Decoder.instance[Char, Initial] {
        case 'ᄀ' => Right(ㄱ)
        case 'ᄁ' => Right(ㄲ)
        case 'ᄂ' => Right(ㄴ)
        case 'ᄃ' => Right(ㄷ)
        case 'ᄄ' => Right(ㄸ)
        case 'ᄅ' => Right(ㄹ)
        case 'ᄆ' => Right(ㅁ)
        case 'ᄇ' => Right(ㅂ)
        case 'ᄈ' => Right(ㅃ)
        case 'ᄉ' => Right(ㅅ)
        case 'ᄊ' => Right(ㅆ)
        case 'ᄋ' => Right(ㅇ)
        case 'ᄌ' => Right(ㅈ)
        case 'ᄍ' => Right(ㅉ)
        case 'ᄎ' => Right(ㅊ)
        case 'ᄏ' => Right(ㅋ)
        case 'ᄐ' => Right(ㅌ)
        case 'ᄑ' => Right(ㅍ)
        case 'ᄒ' => Right(ㅎ)
        case c   => Left(DecodingFailure.FailedToDecodeHangeulJamoInitial(c))
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

    implicit val charDecoder: Decoder[Char, Medial] =
      Decoder.instance[Char, Medial] {
        case 'ᅡ' => Right(ㅏ)
        case 'ᅢ' => Right(ㅐ)
        case 'ᅣ' => Right(ㅑ)
        case 'ᅤ' => Right(ㅒ)
        case 'ᅥ' => Right(ㅓ)
        case 'ᅦ' => Right(ㅔ)
        case 'ᅧ' => Right(ㅕ)
        case 'ᅨ' => Right(ㅖ)
        case 'ᅩ' => Right(ㅗ)
        case 'ᅪ' => Right(ㅘ)
        case 'ᅫ' => Right(ㅙ)
        case 'ᅬ' => Right(ㅚ)
        case 'ᅭ' => Right(ㅛ)
        case 'ᅮ' => Right(ㅜ)
        case 'ᅯ' => Right(ㅝ)
        case 'ᅰ' => Right(ㅞ)
        case 'ᅱ' => Right(ㅟ)
        case 'ᅲ' => Right(ㅠ)
        case 'ᅳ' => Right(ㅡ)
        case 'ᅴ' => Right(ㅢ)
        case 'ᅵ' => Right(ㅣ)
        case c   => Left(DecodingFailure.FailedToDecodeHangeulJamoMedial(c))
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

    implicit val charDecoder: Decoder[Char, Final] =
      Decoder.instance[Char, Final] {
        case 'ᆨ' => Right(ㄱ)
        case 'ᆩ' => Right(ㄲ)
        case 'ᆪ' => Right(ㄳ)
        case 'ᆫ' => Right(ㄴ)
        case 'ᆬ' => Right(ㄵ)
        case 'ᆭ' => Right(ㄶ)
        case 'ᆮ' => Right(ㄷ)
        case 'ᆯ' => Right(ㄹ)
        case 'ᆰ' => Right(ㄺ)
        case 'ᆱ' => Right(ㄻ)
        case 'ᆲ' => Right(ㄼ)
        case 'ᆳ' => Right(ㄽ)
        case 'ᆴ' => Right(ㄾ)
        case 'ᆵ' => Right(ㄿ)
        case 'ᆶ' => Right(ㅀ)
        case 'ᆷ' => Right(ㅁ)
        case 'ᆸ' => Right(ㅂ)
        case 'ᆹ' => Right(ㅄ)
        case 'ᆺ' => Right(ㅅ)
        case 'ᆻ' => Right(ㅆ)
        case 'ᆼ' => Right(ㅇ)
        case 'ᆽ' => Right(ㅈ)
        case 'ᆾ' => Right(ㅊ)
        case 'ᆿ' => Right(ㅋ)
        case 'ᇀ' => Right(ㅌ)
        case 'ᇁ' => Right(ㅍ)
        case 'ᇂ' => Right(ㅎ)
        case c   => Left(DecodingFailure.FailedToDecodeHangeulJamoFinal(c))
      }

  }

}
