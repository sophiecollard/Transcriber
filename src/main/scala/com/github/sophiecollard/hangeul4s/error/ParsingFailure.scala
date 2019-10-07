package com.github.sophiecollard.hangeul4s.error

import cats.data.NonEmptyVector

import scala.util.matching.Regex

sealed abstract class ParsingFailure(val message: String) extends Hangeul4sError

object ParsingFailure {

  final case class FailedWithDecodingErrors(input: String, decodingErrors: NonEmptyVector[DecodingError])
    extends ParsingFailure({
      val errorMessages = decodingErrors.toVector.map(_.message).mkString("\n")
      s"Failed to parse '$input' due to decoding errors:\n$errorMessages"
    })

  final case class FailedToMatchRegex(input: String, regex: Regex)
    extends ParsingFailure(s"Input '$input' does not match regex pattern '${regex.toString}'")

  final case object Empty
    extends ParsingFailure("Empty input")

}
