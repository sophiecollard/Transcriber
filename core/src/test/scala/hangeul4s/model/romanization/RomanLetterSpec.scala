package hangeul4s.model.romanization

import org.specs2.mutable.Specification

class RomanLetterSpec extends Specification {

  "RomanLetter#toString" should {

    "return a String representation of a RomanLetter instance" in {
      RomanLetter.A.toString ==== "hangeul4s.model.romanization.RomanLetter(a)"
    }

  }

}
