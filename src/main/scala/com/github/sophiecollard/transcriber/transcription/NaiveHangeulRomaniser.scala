package com.github.sophiecollard.transcriber.transcription

import com.github.sophiecollard.transcriber.charset.{HangeulLetter, HangeulSyllabicBlock}
import com.github.sophiecollard.transcriber.charset.HangeulSyllabicBlock._
import com.github.sophiecollard.transcriber.charset.RomanLetter._
import com.github.sophiecollard.transcriber.error.TranscriptionError
import com.github.sophiecollard.transcriber.text.{HangeulText, RomanizedText}

object NaiveHangeulRomaniser extends Transcriber[HangeulText, RomanizedText] {

  override def transcribe(text: HangeulText): Either[TranscriptionError, RomanizedText] =
    Right(
      // TODO refactor
      RomanizedText(
        text.chars.map(transcribeBlock).flatMap(_.chars)
      )
    )

  private def transcribeBlock(block: HangeulSyllabicBlock): RomanizedText =
    block match {
      case block: TwoLetter   => transcribeTwoLetterBlock(block)
      case block: ThreeLetter => transcribeThreeLetterBlock(block)
    }

  private def transcribeTwoLetterBlock(block: TwoLetter): RomanizedText =
    // TODO refactor
    RomanizedText(
      transcribeInitialConsonant(block.consonant).chars ++
        transcribeVowel(block.vowel).chars
    )

  private def transcribeThreeLetterBlock(block: ThreeLetter): RomanizedText =
    // TODO refactor
    RomanizedText(
      transcribeInitialConsonant(block.initialConsonant).chars ++
        transcribeVowel(block.vowel).chars ++
        transcribeFinalConsonant(block.finalConsonant).chars
    )

  private def transcribeVowel(vowel: HangeulLetter.Vowel): RomanizedText =
    vowel match {
      case HangeulLetter.ㅏ => RomanizedText(Vector(A))
      case HangeulLetter.ㅐ => RomanizedText(Vector(A, E))
      case HangeulLetter.ㅑ => RomanizedText(Vector(Y, A))
      case HangeulLetter.ㅒ => RomanizedText(Vector(Y, A, E))
      case HangeulLetter.ㅓ => RomanizedText(Vector(E, O))
      case HangeulLetter.ㅔ => RomanizedText(Vector(E))
      case HangeulLetter.ㅕ => RomanizedText(Vector(Y, E, O))
      case HangeulLetter.ㅖ => RomanizedText(Vector(Y, E))
      case HangeulLetter.ㅗ => RomanizedText(Vector(O))
      case HangeulLetter.ㅘ => RomanizedText(Vector(W, A))
      case HangeulLetter.ㅙ => RomanizedText(Vector(W, A, E))
      case HangeulLetter.ㅚ => RomanizedText(Vector(O, E))
      case HangeulLetter.ㅛ => RomanizedText(Vector(Y, O))
      case HangeulLetter.ㅜ => RomanizedText(Vector(U))
      case HangeulLetter.ㅝ => RomanizedText(Vector(W, O))
      case HangeulLetter.ㅞ => RomanizedText(Vector(W, E))
      case HangeulLetter.ㅟ => RomanizedText(Vector(W, I))
      case HangeulLetter.ㅠ => RomanizedText(Vector(Y, U))
      case HangeulLetter.ㅡ => RomanizedText(Vector(E, U))
      case HangeulLetter.ㅢ => RomanizedText(Vector(U, I))
      case HangeulLetter.ㅣ => RomanizedText(Vector(I))
    }

  private def transcribeInitialConsonant(consonant: HangeulLetter.Consonant): RomanizedText =
    consonant match {
      case HangeulLetter.ㄱ => RomanizedText(Vector(G))
      case HangeulLetter.ㄲ => RomanizedText(Vector(K, K))
      case HangeulLetter.ㄴ => RomanizedText(Vector(N))
      case HangeulLetter.ㄷ => RomanizedText(Vector(D))
      case HangeulLetter.ㄸ => RomanizedText(Vector(T, T))
      case HangeulLetter.ㄹ => RomanizedText(Vector(R))
      case HangeulLetter.ㅁ => RomanizedText(Vector(M))
      case HangeulLetter.ㅂ => RomanizedText(Vector(B))
      case HangeulLetter.ㅃ => RomanizedText(Vector(P, P))
      case HangeulLetter.ㅅ => RomanizedText(Vector(S))
      case HangeulLetter.ㅆ => RomanizedText(Vector(S, S))
      case HangeulLetter.ㅇ => RomanizedText(Vector.empty) // Silent when in initial position
      case HangeulLetter.ㅈ => RomanizedText(Vector(J))
      case HangeulLetter.ㅉ => RomanizedText(Vector(J, J))
      case HangeulLetter.ㅊ => RomanizedText(Vector(C, H))
      case HangeulLetter.ㅋ => RomanizedText(Vector(K))
      case HangeulLetter.ㅌ => RomanizedText(Vector(T))
      case HangeulLetter.ㅍ => RomanizedText(Vector(P))
      case HangeulLetter.ㅎ => RomanizedText(Vector(H))
    }

  private def transcribeFinalConsonant(consonant: HangeulLetter.Consonant): RomanizedText =
    consonant match {
      case HangeulLetter.ㄱ => RomanizedText(Vector(K))
      case HangeulLetter.ㄲ => RomanizedText(Vector(K))
      case HangeulLetter.ㄴ => RomanizedText(Vector(N))
      case HangeulLetter.ㄷ => RomanizedText(Vector(T))
      case HangeulLetter.ㄸ => RomanizedText(Vector.empty)
      case HangeulLetter.ㄹ => RomanizedText(Vector(L))
      case HangeulLetter.ㅁ => RomanizedText(Vector(M))
      case HangeulLetter.ㅂ => RomanizedText(Vector(P))
      case HangeulLetter.ㅃ => RomanizedText(Vector.empty)
      case HangeulLetter.ㅅ => RomanizedText(Vector(T))
      case HangeulLetter.ㅆ => RomanizedText(Vector(T))
      case HangeulLetter.ㅇ => RomanizedText(Vector(N, G))
      case HangeulLetter.ㅈ => RomanizedText(Vector(T))
      case HangeulLetter.ㅉ => RomanizedText(Vector.empty)
      case HangeulLetter.ㅊ => RomanizedText(Vector(T))
      case HangeulLetter.ㅋ => RomanizedText(Vector(K))
      case HangeulLetter.ㅌ => RomanizedText(Vector(T))
      case HangeulLetter.ㅍ => RomanizedText(Vector(P))
      case HangeulLetter.ㅎ => RomanizedText(Vector(T))
    }

}
