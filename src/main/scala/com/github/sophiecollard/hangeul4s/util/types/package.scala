package com.github.sophiecollard.hangeul4s.util

import cats.data.{NonEmptyVector, Validated}

package object types {

  type ValidatedNev[E, A] = Validated[NonEmptyVector[E], A]

}
