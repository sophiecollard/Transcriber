package com.github.sophiecollard.transcriber.transcription

import com.github.sophiecollard.transcriber.charset.{HangeulChar, HangeulSyllabicBlock}
import com.github.sophiecollard.transcriber.charset.HangeulSyllabicBlock._
import com.github.sophiecollard.transcriber.charset.RomanChar._
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

  private def transcribeVowel(vowel: HangeulChar.Vowel): RomanizedText =
    vowel match {
      case HangeulChar.ㅏ => RomanizedText(Vector(A))
      case HangeulChar.ㅐ => RomanizedText(Vector(A, E))
      case HangeulChar.ㅑ => RomanizedText(Vector(Y, A))
      case HangeulChar.ㅒ => RomanizedText(Vector(Y, A, E))
      case HangeulChar.ㅓ => RomanizedText(Vector(E, O))
      case HangeulChar.ㅔ => RomanizedText(Vector(E))
      case HangeulChar.ㅕ => RomanizedText(Vector(Y, E, O))
      case HangeulChar.ㅖ => RomanizedText(Vector(Y, E))
      case HangeulChar.ㅗ => RomanizedText(Vector(O))
      case HangeulChar.ㅘ => RomanizedText(Vector(W, A))
      case HangeulChar.ㅙ => RomanizedText(Vector(W, A, E))
      case HangeulChar.ㅚ => RomanizedText(Vector(O, E))
      case HangeulChar.ㅛ => RomanizedText(Vector(Y, O))
      case HangeulChar.ㅜ => RomanizedText(Vector(U))
      case HangeulChar.ㅝ => RomanizedText(Vector(W, O))
      case HangeulChar.ㅞ => RomanizedText(Vector(W, E))
      case HangeulChar.ㅟ => RomanizedText(Vector(W, I))
      case HangeulChar.ㅠ => RomanizedText(Vector(Y, U))
      case HangeulChar.ㅡ => RomanizedText(Vector(E, U))
      case HangeulChar.ㅢ => RomanizedText(Vector(U, I))
      case HangeulChar.ㅣ => RomanizedText(Vector(I))
    }

  private def transcribeInitialConsonant(consonant: HangeulChar.Consonant): RomanizedText =
    consonant match {
      case HangeulChar.ㄱ => RomanizedText(Vector(G))
      case HangeulChar.ㄲ => RomanizedText(Vector(K, K))
      case HangeulChar.ㄴ => RomanizedText(Vector(N))
      case HangeulChar.ㄷ => RomanizedText(Vector(D))
      case HangeulChar.ㄸ => RomanizedText(Vector(T, T))
      case HangeulChar.ㄹ => RomanizedText(Vector(R))
      case HangeulChar.ㅁ => RomanizedText(Vector(M))
      case HangeulChar.ㅂ => RomanizedText(Vector(B))
      case HangeulChar.ㅃ => RomanizedText(Vector(P, P))
      case HangeulChar.ㅅ => RomanizedText(Vector(S))
      case HangeulChar.ㅆ => RomanizedText(Vector(S, S))
      case HangeulChar.ㅇ => RomanizedText(Vector.empty) // Silent when in initial position
      case HangeulChar.ㅈ => RomanizedText(Vector(J))
      case HangeulChar.ㅉ => RomanizedText(Vector(J, J))
      case HangeulChar.ㅊ => RomanizedText(Vector(C, H))
      case HangeulChar.ㅋ => RomanizedText(Vector(K))
      case HangeulChar.ㅌ => RomanizedText(Vector(T))
      case HangeulChar.ㅍ => RomanizedText(Vector(P))
      case HangeulChar.ㅎ => RomanizedText(Vector(H))
    }

  private def transcribeFinalConsonant(consonant: HangeulChar.Consonant): RomanizedText =
    consonant match {
      case HangeulChar.ㄱ => RomanizedText(Vector(K))
      case HangeulChar.ㄲ => RomanizedText(Vector(K))
      case HangeulChar.ㄴ => RomanizedText(Vector(N))
      case HangeulChar.ㄷ => RomanizedText(Vector(T))
      case HangeulChar.ㄸ => RomanizedText(Vector.empty)
      case HangeulChar.ㄹ => RomanizedText(Vector(L))
      case HangeulChar.ㅁ => RomanizedText(Vector(M))
      case HangeulChar.ㅂ => RomanizedText(Vector(P))
      case HangeulChar.ㅃ => RomanizedText(Vector.empty)
      case HangeulChar.ㅅ => RomanizedText(Vector(T))
      case HangeulChar.ㅆ => RomanizedText(Vector(T))
      case HangeulChar.ㅇ => RomanizedText(Vector(N, G))
      case HangeulChar.ㅈ => RomanizedText(Vector(T))
      case HangeulChar.ㅉ => RomanizedText(Vector.empty)
      case HangeulChar.ㅊ => RomanizedText(Vector(T))
      case HangeulChar.ㅋ => RomanizedText(Vector(K))
      case HangeulChar.ㅌ => RomanizedText(Vector(T))
      case HangeulChar.ㅍ => RomanizedText(Vector(P))
      case HangeulChar.ㅎ => RomanizedText(Vector(T))
    }

}
