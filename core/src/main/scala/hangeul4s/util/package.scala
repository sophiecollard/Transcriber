package hangeul4s

import cats.data.{NonEmptyVector, Validated}

package object util {

  type ValidatedNev[+E, +A] = Validated[NonEmptyVector[E], A]

}
