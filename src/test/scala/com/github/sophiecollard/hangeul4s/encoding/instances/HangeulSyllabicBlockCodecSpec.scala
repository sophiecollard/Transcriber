package com.github.sophiecollard.hangeul4s.encoding.instances

import com.github.sophiecollard.hangeul4s.encoding.syntax._
import com.github.sophiecollard.hangeul4s.error.DecodingError
import com.github.sophiecollard.hangeul4s.model.hangeul.{HangeulJamo, HangeulSyllabicBlock}
import org.specs2.mutable.Specification

class HangeulSyllabicBlockCodecSpec extends Specification {

  "A Codec[Char, HangeulSyllabicBlock] instance" should {

    val encodedTwoLetterBlock: Char = '가'
    val encodedThreeLetterBlock: Char = '힣'

    val decodedTwoLetterBlock: HangeulSyllabicBlock =
      HangeulSyllabicBlock.TwoLetter(HangeulJamo.Initial.ㄱ, HangeulJamo.Medial.ㅏ)

    val decodedThreeLetterBlock: HangeulSyllabicBlock =
      HangeulSyllabicBlock.ThreeLetter(HangeulJamo.Initial.ㅎ, HangeulJamo.Medial.ㅣ, HangeulJamo.Final.ㅎ)

    "encode a syllabic block to a Char" in {
      decodedTwoLetterBlock.encode[Char] must beRight(encodedTwoLetterBlock)
      decodedThreeLetterBlock.encode[Char] must beRight(encodedThreeLetterBlock)
    }

    "decode a Char within the Unicode range [AC00–D7A3] to a syllabic block" in {
      encodedTwoLetterBlock.decode[HangeulSyllabicBlock] must beRight(decodedTwoLetterBlock)
      encodedThreeLetterBlock.decode[HangeulSyllabicBlock] must beRight(decodedThreeLetterBlock)
    }

    "fail to decode a character outside the Unicode range [AC00–D7A3]" in {
      'A'.decode[HangeulSyllabicBlock] must beLeft[DecodingError]
    }

  }

}
