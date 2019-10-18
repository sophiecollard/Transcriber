package hangeul4s.model.romanization

import hangeul4s.encoding.syntax._
import hangeul4s.error.DecodingFailure
import org.specs2.mutable.Specification

class RomanLetterSpec extends Specification {

  "RomanLetter#toString" should {

    "return a String representation of a RomanLetter instance" in {
      RomanLetter.A.toString ==== "hangeul4s.model.romanization.RomanLetter(a)"
    }

  }

  "RomanLetter#charDecoder" should {

    "decode an lower-case roman letter" in {
      'a'.decodeTo[RomanLetter] should beRight[RomanLetter](RomanLetter.A)
    }

    "decode an upper-case roman letter" in {
      'A'.decodeTo[RomanLetter] should beRight[RomanLetter](RomanLetter.A)
    }

    "fail to decode a character outside the [A-EG-PR-UWYa-eg-pr-uwy] range" in {
      'z'.decodeTo[RomanLetter] should beLeft[DecodingFailure]
      'ᄓ'.decodeTo[RomanLetter] should beLeft[DecodingFailure]
    }

  }

  "RomanLetter#charEncoder" should {

    "encode a roman letter" in {
      (RomanLetter.A: RomanLetter).encodeTo[Char] ==== 'a'
    }

  }

}
