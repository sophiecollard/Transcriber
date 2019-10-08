package com.github.sophiecollard.hangeul4s.model.hangeul

import com.github.sophiecollard.hangeul4s.encoding.{Decoder, Encoder}
import com.github.sophiecollard.hangeul4s.error.DecodingError
import org.specs2.mutable.Specification

class HangeulSyllabicBlockSpec extends Specification {

  "HangeulSyllabicBlock#charCodec" should {

    val encodedTwoLetterBlock = '가'
    val encodedThreeLetterBlock = '힣'

    val decodedTwoLetterBlock =
      HangeulSyllabicBlock.twoLetter(HangeulJamo.Initial.ㄱ, HangeulJamo.Medial.ㅏ)
    val decodedThreeLetterBlock: HangeulSyllabicBlock =
      HangeulSyllabicBlock.threeLetter(HangeulJamo.Initial.ㅎ, HangeulJamo.Medial.ㅣ, HangeulJamo.Final.ㅎ)

    "decode a Char within the Unicode range [AC00–D7A3]" in {
      Decoder[Char, HangeulSyllabicBlock].decode(encodedTwoLetterBlock) must beRight(decodedTwoLetterBlock)
      Decoder[Char, HangeulSyllabicBlock].decode(encodedThreeLetterBlock) must beRight(decodedThreeLetterBlock)
    }

    "fail to decode a character outside the Unicode range [AC00–D7A3]" in {
      Decoder[Char, HangeulSyllabicBlock].decode('A') must beLeft[DecodingError]
    }

    "encode to a Char" in {
      Encoder[HangeulSyllabicBlock, Char].encode(decodedTwoLetterBlock) ==== encodedTwoLetterBlock
      Encoder[HangeulSyllabicBlock, Char].encode(decodedThreeLetterBlock) ==== encodedThreeLetterBlock
    }

  }

}
