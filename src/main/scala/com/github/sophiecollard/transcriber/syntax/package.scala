package com.github.sophiecollard.transcriber

import com.github.sophiecollard.transcriber.text.Text

package object syntax {

  implicit class TextList[+A](value: List[Text[A]]) {
    def flattenChars: List[A] = {
      def go(remainder: List[Text[A]], acc: Vector[A]): List[A] = {
        remainder match {
          case Nil    => acc.toList
          case h :: t => go(t, acc ++ h.chars.toVector)
        }
      }

      go(value, Vector.empty[A])
    }
  }

}
