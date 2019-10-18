package hangeul4s.model.hangeul

import hangeul4s.encoding.syntax._
import hangeul4s.error.DecodingFailure
import org.specs2.mutable.Specification

class HangeulJamoSpec extends Specification {

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
