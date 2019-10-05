package com.github.sophiecollard.hangeul4s.error

import cats.data.NonEmptyVector

sealed abstract class ParsingError(val message: String) extends Error

object ParsingError {

  final case class ParsingFailedWithDecodingErrors(input: String, decodingErrors: NonEmptyVector[DecodingError])
    extends ParsingError({
      val errorMessages = decodingErrors.toVector.map(_.message).mkString("\n")
      s"Failed to parse '$input' with:\n$errorMessages"
    })

  final case object Empty
    extends ParsingError("Failed to parse empty input")

}
