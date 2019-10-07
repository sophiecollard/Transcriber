package com.github.sophiecollard.hangeul4s.error

sealed abstract class TransliterationFailure(val message: String) extends Hangeul4sError

object TransliterationFailure {

  final case object EmptyResult
    extends TransliterationFailure("Empty result")

}
