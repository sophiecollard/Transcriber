package hangeul4s.model.hangeul

import hangeul4s.encoding.{Decoder, Encoder}
import hangeul4s.error.DecodingFailure
import org.specs2.mutable.Specification

class HangeulSyllableSpec extends Specification {

  "HangeulSyllable#charCodec" should {

    val encodedTwoJamoSyllable = '가'
    val encodedThreeJamoSyllable = '힣'

    val decodedTwoJamoSyllable =
      HangeulSyllable.twoJamo(HangeulJamo.Initial.ㄱ, HangeulJamo.Medial.ㅏ)
    val decodedThreeJamoSyllable: HangeulSyllable =
      HangeulSyllable.threeJamo(HangeulJamo.Initial.ㅎ, HangeulJamo.Medial.ㅣ, HangeulJamo.Final.ㅎ)

    "decode a Char within the Unicode range [AC00–D7A3]" in {
      Decoder[Char, HangeulSyllable].decode(encodedTwoJamoSyllable) must beRight(decodedTwoJamoSyllable)
      Decoder[Char, HangeulSyllable].decode(encodedThreeJamoSyllable) must beRight(decodedThreeJamoSyllable)
    }

    "fail to decode a character outside the Unicode range [AC00–D7A3]" in {
      Decoder[Char, HangeulSyllable].decode('A') must beLeft[DecodingFailure]
    }

    "encode to a Char" in {
      Encoder[HangeulSyllable, Char].encode(decodedTwoJamoSyllable) ==== encodedTwoJamoSyllable
      Encoder[HangeulSyllable, Char].encode(decodedThreeJamoSyllable) ==== encodedThreeJamoSyllable
    }

  }

}
