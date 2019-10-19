package hangeul4s.encoding.syntax

import hangeul4s.encoding.{Decoder, Encoder}
import hangeul4s.error.DecodingFailure
import hangeul4s.model.romanization.RomanLetter
import org.specs2.mutable.Specification

class SyntaxSpec extends Specification {

  private implicit val decoder: Decoder[Char, RomanLetter] =
    Decoder.instance {
      case 'A' | 'a' => Right(RomanLetter.A)
      case 'B' | 'c' => Right(RomanLetter.B)
      case c         => Left(DecodingFailure.FailedToDecodeRomanLetter(c))
    }

  "Syntax#DecodingOps[A]" should {

    "provide a 'decodeTo' method on A instances" in {
      'A'.decodeTo[RomanLetter] must beRight[RomanLetter](RomanLetter.A)
    }

  }

  private implicit val encoder: Encoder[RomanLetter, Char] =
    Encoder.instance { letter =>
      letter.char
    }

  "Syntax#EncodingOps[B]" should {

    "provide an 'encodeTo' method on B instances" in {
      (RomanLetter.A: RomanLetter).encodeTo[Char] ==== 'a'
    }

  }

}
