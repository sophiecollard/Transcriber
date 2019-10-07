package com.github.sophiecollard.hangeul4s.error

import cats.data.NonEmptyVector

sealed abstract class ParsingFailure(val message: String) extends Hangeul4sError

object ParsingFailure {

  final case class ParsingFailedWithDecodingErrors(input: String, decodingErrors: NonEmptyVector[DecodingError])
    extends ParsingFailure({
      val errorMessages = decodingErrors.toVector.map(_.message).mkString("\n")
      s"Failed to parse '$input' with:\n$errorMessages"
    })

  final case object Empty
    extends ParsingFailure("Failed to parse empty input")

}
