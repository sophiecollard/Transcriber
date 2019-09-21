package com.github.sophiecollard.transliterator.error

sealed abstract class TransliterationError(val message: String)

object TransliterationError {

  final case class NotImplemented(feature: String)
    extends TransliterationError(s"The following feature has not been implemented: $feature")

}
