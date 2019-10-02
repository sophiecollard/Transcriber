package com.github.sophiecollard.hangeul4s.error

import cats.data.NonEmptyVector

sealed abstract class ParsingError(val message: String)

object ParsingError {

  final case class ParsingFailedWithValidationErrors(input: String, validationErrors: NonEmptyVector[ValidationError])
    extends ParsingError({
      val errorMessages = validationErrors.toVector.map(_.message).mkString("\n")
      s"Failed to parse '$input' with:\n$errorMessages"
    })

  final case class ParsingFailedWithDecodingErrors(input: String, decodingErrors: NonEmptyVector[DecodingError])
    extends ParsingError({
      val errorMessages = decodingErrors.toVector.map(_.message).mkString("\n")
      s"Failed to parse '$input' with:\n$errorMessages"
    })

  final case object Empty
    extends ParsingError("Failed to parse empty input")

  final case class RegexParsingFailure(override val message: String)
    extends ParsingError(message)

  final case class RegexParsingError(override val message: String)
    extends ParsingError(message)

}
