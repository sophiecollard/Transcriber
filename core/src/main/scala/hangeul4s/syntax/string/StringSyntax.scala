package hangeul4s.syntax.string

import scala.util.Try

trait StringSyntax {

  implicit class StringOps(string: String) {
    def safeCharAt(index: Int): Option[Char] =
      Try(string.charAt(index)).toOption
  }

}
