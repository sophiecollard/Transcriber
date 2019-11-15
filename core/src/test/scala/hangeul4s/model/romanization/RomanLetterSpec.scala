package hangeul4s.model.romanization

import cats.instances.either._
import cats.instances.vector._
import cats.syntax.traverse._
import hangeul4s.encoding.syntax._
import hangeul4s.error.DecodingFailure
import hangeul4s.model.romanization.RomanLetter._
import org.specs2.mutable.Specification

class RomanLetterSpec extends Specification {

  "RomanLetter#toString" should {

    "return a String representation of a RomanLetter instance" in {
      RomanLetter.A.toString ==== "hangeul4s.model.romanization.RomanLetter(a)"
    }

  }

  "RomanLetter#charDecoder" should {

    "decode lower-case roman letters" in {
      val encoded = Vector('a', 'b', 'c', 'd', 'e', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'r', 's', 't', 'u', 'w', 'y')

      val decoded = Vector[RomanLetter](A, B, C, D, E, G, H, I, J, K, L, M, N, O, P, R, S, T, U, W, Y)

      encoded.traverse(_.decodeTo[RomanLetter]) should beRight(decoded)
    }

    "decode upper-case roman letters" in {
      val encoded = Vector('A', 'B', 'C', 'D', 'E', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'R', 'S', 'T', 'U', 'W', 'Y')

      val decoded = Vector[RomanLetter](A, B, C, D, E, G, H, I, J, K, L, M, N, O, P, R, S, T, U, W, Y)

      encoded.traverse(_.decodeTo[RomanLetter]) should beRight(decoded)
    }

    "fail to decode a character outside the [A-EG-PR-UWYa-eg-pr-uwy] range" in {
      'z'.decodeTo[RomanLetter] should beLeft[DecodingFailure]
      'ᄓ'.decodeTo[RomanLetter] should beLeft[DecodingFailure]
    }

  }

  "RomanLetter#charEncoder" should {

    "encode a roman letter" in {
      RomanLetter.A.encodeTo[Char] ==== 'a'
    }

  }

}
