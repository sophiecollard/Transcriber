package hangeul4s.model.hangeul

import cats.Show
import hangeul4s.encoding.syntax._
import hangeul4s.error.DecodingFailure
import org.specs2.mutable.Specification

class HangeulJamoSpec extends Specification {

  "HangeulJamo#toString" should {

    "Return a String representation of a HangeulJamo.Initial instance" in {
      HangeulJamo.Initial.ㄲ.toString ==== "hangeul4s.model.hangeul.HangeulJamo.Initial(ᄁ)"
    }

    "Return a String representation of a HangeulJamo.Medial instance" in {
      HangeulJamo.Medial.ㅐ.toString === "hangeul4s.model.hangeul.HangeulJamo.Medial(ᅢ)"
    }

    "Return a String representation of a HangeulJamo.Final instance" in {
      HangeulJamo.Final.ㄲ.toString ==== "hangeul4s.model.hangeul.HangeulJamo.Final(ᆩ)"
    }

  }

  "HangeulJamo#show" should {

    "Return the character representing an initial consonant" in {
      Show[HangeulJamo.Initial].show(HangeulJamo.Initial.ㄲ) ==== "ᄁ"
      Show[HangeulJamo].show(HangeulJamo.Initial.ㄲ) ==== "ᄁ"
    }

    "Return the character representing a medial" in {
      Show[HangeulJamo.Medial].show(HangeulJamo.Medial.ㅐ) ==== "ᅢ"
      Show[HangeulJamo].show(HangeulJamo.Medial.ㅐ) ==== "ᅢ"
    }

    "Return the character representing a final consonant" in {
      Show[HangeulJamo.Final].show(HangeulJamo.Final.ㄲ) ==== "ᆩ"
      Show[HangeulJamo].show(HangeulJamo.Final.ㄲ) ==== "ᆩ"
    }

  }

  "HangeulJamo#charDecoder" should {

    "decode an initial consonant" in {
      'ᄀ'.decodeTo[HangeulJamo] should beRight[HangeulJamo](HangeulJamo.Initial.ㄱ)
    }

    "decode a medial" in {
      'ᅡ'.decodeTo[HangeulJamo] should beRight[HangeulJamo](HangeulJamo.Medial.ㅏ)
    }

    "decode a final consonant" in {
      'ᆨ'.decodeTo[HangeulJamo] should beRight[HangeulJamo](HangeulJamo.Final.ㄱ)
    }

    "fail to decode archaic jamo" in {
      'ᄓ'.decodeTo[HangeulJamo] should beLeft[DecodingFailure]
    }

  }

  "HangeulJamo#charEncoder" should {

    "encode an initial consonant" in {
      (HangeulJamo.Initial.ㅎ: HangeulJamo).encodeTo[Char] ==== 'ᄒ'
    }

    "encode a medial" in {
      (HangeulJamo.Medial.ㅣ: HangeulJamo).encodeTo[Char] ==== 'ᅵ'
    }

    "encode a final consonant" in {
      (HangeulJamo.Final.ㅎ: HangeulJamo).encodeTo[Char] ==== 'ᇂ'
    }

  }

}
