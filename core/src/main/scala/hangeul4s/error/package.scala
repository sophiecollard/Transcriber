package hangeul4s

import scala.util.matching.Regex

package object error {

  sealed abstract class Hangeul4sError(val message: String)

  sealed abstract class ParsingFailure(override val message: String) extends Hangeul4sError(message)

  object ParsingFailure {

    final case object EmptyInput
      extends ParsingFailure("Empty input")

    final case class FailedToMatchRegex(input: String, regex: Regex)
      extends ParsingFailure(s"Input [$input] does not match regex pattern [${regex.toString}]")

  }

  sealed abstract class DecodingFailure(override val message: String) extends ParsingFailure(message)

  object DecodingFailure {

    final case class FailedToDecodeHangeulJamoInitial(char: Char)
      extends DecodingFailure(s"Failed to decode HangeulJamo.Initial from Char [$char] with decimal code point [${char.toInt}]")

    final case class FailedToDecodeHangeulJamoMedial(char: Char)
      extends DecodingFailure(s"Failed to decode HangeulJamo.Medial from Char [$char] with decimal code point [${char.toInt}]")

    final case class FailedToDecodeHangeulJamoFinal(char: Char)
      extends DecodingFailure(s"Failed to decode HangeulJamo.Final from Char [$char] with decimal code point [${char.toInt}]")

    final case class FailedToDecodeHangeulSyllable(char: Char)
      extends DecodingFailure(s"Failed to decode HangeulSyllable from Char [$char] with decimal code point [${char.toInt}]")

    final case class FailedToDecodeRomanLetter(char: Char)
      extends DecodingFailure(s"Failed to decode RomanLetter from Char [$char] with decimal code point [${char.toInt}]")

  }

  sealed abstract class TransliterationFailure(override val message: String) extends Hangeul4sError(message)

  object TransliterationFailure {

    final case object EmptyResult
      extends TransliterationFailure("Empty result")

  }

}
