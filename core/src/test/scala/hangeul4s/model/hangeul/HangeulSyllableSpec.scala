package hangeul4s.model.hangeul

import hangeul4s.encoding.syntax._
import hangeul4s.error.DecodingFailure
import org.specs2.mutable.Specification

class HangeulSyllableSpec extends Specification {

  private val encodedTwoJamoSyllable = '가'
  private val encodedThreeJamoSyllable = '힣'

  private val decodedTwoJamoSyllable =
    HangeulSyllable.twoJamo(HangeulJamo.Initial.ㄱ, HangeulJamo.Medial.ㅏ)
  private val decodedThreeJamoSyllable =
    HangeulSyllable.threeJamo(HangeulJamo.Initial.ㅎ, HangeulJamo.Medial.ㅣ, HangeulJamo.Final.ㅎ)

  "HangeulSyllable#toString" should {

    "return a String representation of a HangeulSyllable.TwoJamo instance" in {
      val syllable = HangeulSyllable.twoJamo(HangeulJamo.Initial.ㄱ, HangeulJamo.Medial.ㅏ)
      syllable.toString ==== "hangeul4s.model.hangeul.HangeulSyllable(가)"
    }

    "return a String representation of a HangeulSyllable.ThreeJamo instance" in {
      val syllable = HangeulSyllable.threeJamo(HangeulJamo.Initial.ㅎ, HangeulJamo.Medial.ㅣ, HangeulJamo.Final.ㅎ)
      syllable.toString ==== "hangeul4s.model.hangeul.HangeulSyllable(힣)"
    }

  }

  "HangeulSyllable#charDecoder" should {

    "decode a Char within the Unicode range [AC00–D7A3]" in {
      encodedTwoJamoSyllable.decodeTo[HangeulSyllable] should beRight(decodedTwoJamoSyllable)
      encodedThreeJamoSyllable.decodeTo[HangeulSyllable] must beRight(decodedThreeJamoSyllable)
    }

    "fail to decode a character outside the Unicode range [AC00–D7A3]" in {
      'A'.decodeTo[HangeulSyllable] must beLeft[DecodingFailure]
    }

  }

  "HangeulSyllable#charEncoder" should {

    "encode to a Char" in {
      decodedTwoJamoSyllable.encodeTo[Char] ==== encodedTwoJamoSyllable
      decodedThreeJamoSyllable.encodeTo[Char] ==== encodedThreeJamoSyllable
    }

  }

}
