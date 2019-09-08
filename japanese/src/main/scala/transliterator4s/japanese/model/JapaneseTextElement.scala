package transliterator4s.japanese.model

import cats.data.NonEmptyVector

sealed trait JapaneseTextElement

object JapaneseTextElement {

  final case class CapturedKanji(contents: NonEmptyVector[Kanji]) extends JapaneseTextElement

  final case class CapturedHiragana(contents: NonEmptyVector[Hiragana]) extends JapaneseTextElement

  final case class CapturedKatakana(contents: NonEmptyVector[Katakana]) extends JapaneseTextElement

  final case class NotCaptured(contents: String) extends JapaneseTextElement

}
