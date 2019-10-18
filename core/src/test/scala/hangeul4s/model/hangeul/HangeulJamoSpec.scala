package hangeul4s.model.hangeul

import hangeul4s.encoding.{Decoder, Encoder}
import hangeul4s.error.DecodingFailure
import org.specs2.mutable.Specification

class HangeulJamoSpec extends Specification {

  "HangeulJamo#charDecoder" should {

    "decode an initial consonant" in {
      Decoder[Char, HangeulJamo].decode('ᄀ') should beRight[HangeulJamo](HangeulJamo.Initial.ㄱ)
    }

    "decode a medial" in {
      Decoder[Char, HangeulJamo].decode('ᅡ') should beRight[HangeulJamo](HangeulJamo.Medial.ㅏ)
    }

    "decode a final consonant" in {
      Decoder[Char, HangeulJamo].decode('ᆨ') should beRight[HangeulJamo](HangeulJamo.Final.ㄱ)
    }

    "fail to decode archaic jamo" in {
      Decoder[Char, HangeulJamo].decode('ᄓ') should beLeft[DecodingFailure]
    }

  }

  "HangeulJamo#charEncoder" should {

    "encode an initial consonant" in {
      Encoder[HangeulJamo, Char].encode(HangeulJamo.Initial.ㅎ) ==== 'ᄒ'
    }

    "encode a medial" in {
      Encoder[HangeulJamo, Char].encode(HangeulJamo.Medial.ㅣ) ==== 'ᅵ'
    }

    "encode a final consonant" in {
      Encoder[HangeulJamo, Char].encode(HangeulJamo.Final.ㅎ) ==== 'ᇂ'
    }

  }

}
