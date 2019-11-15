package hangeul4s.model.hangeul

import cats.instances.either._
import cats.instances.vector._
import cats.syntax.traverse._
import hangeul4s.encoding.syntax._
import hangeul4s.error.DecodingFailure
import hangeul4s.model.hangeul.HangeulJamo._
import org.specs2.mutable.Specification

class HangeulJamoSpec extends Specification {

  "HangeulJamo#toString" should {

    "return a String representation of a HangeulJamo.Initial instance" in {
      Initial.ㄲ.toString ==== "hangeul4s.model.hangeul.HangeulJamo.Initial(ᄁ)"
    }

    "return a String representation of a Medial instance" in {
      Medial.ㅐ.toString === "hangeul4s.model.hangeul.HangeulJamo.Medial(ᅢ)"
    }

    "return a String representation of a Final instance" in {
      Final.ㄲ.toString ==== "hangeul4s.model.hangeul.HangeulJamo.Final(ᆩ)"
    }

  }

  "HangeulJamo#charDecoder" should {

    "decode initial consonants" in {
      val encoded = Vector(
        'ᄀ', 'ᄁ', 'ᄂ', 'ᄃ', 'ᄄ', 'ᄅ', 'ᄆ', 'ᄇ', 'ᄈ', 'ᄉ', 'ᄊ', 'ᄋ', 'ᄌ', 'ᄍ', 'ᄎ', 'ᄏ', 'ᄐ', 'ᄑ', 'ᄒ'
      )

      val decoded = Vector[HangeulJamo](
        Initial.ㄱ, Initial.ㄲ, Initial.ㄴ, Initial.ㄷ, Initial.ㄸ, Initial.ㄹ, Initial.ㅁ, Initial.ㅂ, Initial.ㅃ,
        Initial.ㅅ, Initial.ㅆ, Initial.ㅇ, Initial.ㅈ, Initial.ㅉ, Initial.ㅊ, Initial.ㅋ, Initial.ㅌ, Initial.ㅍ,
        Initial.ㅎ
      )

      encoded.traverse(_.decodeTo[HangeulJamo]) should beRight(decoded)
    }

    "decode medials" in {
      val encoded = Vector(
        'ᅡ', 'ᅢ', 'ᅣ', 'ᅤ', 'ᅥ', 'ᅦ', 'ᅧ', 'ᅨ', 'ᅩ', 'ᅪ', 'ᅫ', 'ᅬ', 'ᅭ', 'ᅮ', 'ᅯ', 'ᅰ', 'ᅱ', 'ᅲ', 'ᅳ', 'ᅴ',
        'ᅵ'
      )

      val decoded = Vector[HangeulJamo](
        Medial.ㅏ, Medial.ㅐ, Medial.ㅑ, Medial.ㅒ, Medial.ㅓ, Medial.ㅔ, Medial.ㅕ, Medial.ㅖ, Medial.ㅗ, Medial.ㅘ,
        Medial.ㅙ, Medial.ㅚ, Medial.ㅛ, Medial.ㅜ, Medial.ㅝ, Medial.ㅞ, Medial.ㅟ, Medial.ㅠ, Medial.ㅡ, Medial.ㅢ,
        Medial.ㅣ
      )

      encoded.traverse(_.decodeTo[HangeulJamo]) should beRight(decoded)
    }

    "decode final consonants" in {
      val encoded = Vector(
        'ᆨ', 'ᆩ', 'ᆪ', 'ᆫ', 'ᆬ', 'ᆭ', 'ᆮ', 'ᆯ', 'ᆰ', 'ᆱ', 'ᆲ', 'ᆳ', 'ᆴ', 'ᆵ', 'ᆶ', 'ᆷ', 'ᆸ', 'ᆹ', 'ᆺ', 'ᆻ',
        'ᆼ', 'ᆽ', 'ᆾ', 'ᆿ', 'ᇀ', 'ᇁ', 'ᇂ'
      )

      val decoded = Vector[HangeulJamo](
        Final.ㄱ, Final.ㄲ, Final.ㄳ, Final.ㄴ, Final.ㄵ, Final.ㄶ, Final.ㄷ, Final.ㄹ, Final.ㄺ, Final.ㄻ, Final.ㄼ,
        Final.ㄽ, Final.ㄾ, Final.ㄿ, Final.ㅀ, Final.ㅁ, Final.ㅂ, Final.ㅄ, Final.ㅅ, Final.ㅆ, Final.ㅇ, Final.ㅈ,
        Final.ㅊ, Final.ㅋ, Final.ㅌ, Final.ㅍ, Final.ㅎ
      )

      encoded.traverse(_.decodeTo[HangeulJamo]) should beRight(decoded)
    }

    "fail to decode archaic jamo" in {
      'ᄓ'.decodeTo[HangeulJamo] should beLeft[DecodingFailure]
    }

  }

  "HangeulJamo#charEncoder" should {

    "encode an initial consonant" in {
      Initial.ㅎ.encodeTo[Char] ==== 'ᄒ'
    }

    "encode a medial" in {
      Medial.ㅣ.encodeTo[Char] ==== 'ᅵ'
    }

    "encode a final consonant" in {
      Final.ㅎ.encodeTo[Char] ==== 'ᇂ'
    }

  }

}
