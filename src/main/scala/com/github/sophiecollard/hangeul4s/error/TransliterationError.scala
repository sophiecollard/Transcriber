package com.github.sophiecollard.hangeul4s.error

sealed abstract class TransliterationError(val message: String) extends Error

object TransliterationError {

  final case class NotImplemented(feature: String)
    extends TransliterationError(s"The following feature has not been implemented: $feature")

}
