package com.github.sophiecollard.hangeul4s.error

sealed abstract class TransliterationFailure(val message: String) extends Hangeul4sError
