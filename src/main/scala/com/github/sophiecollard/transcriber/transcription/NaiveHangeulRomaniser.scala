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
      case HangeulChar.ㅏ => RomanizedText(List(A))
      case HangeulChar.ㅐ => RomanizedText(List(A, E))
      case HangeulChar.ㅑ => RomanizedText(List(Y, A))
      case HangeulChar.ㅒ => RomanizedText(List(Y, A, E))
      case HangeulChar.ㅓ => RomanizedText(List(E, O))
      case HangeulChar.ㅔ => RomanizedText(List(E))
      case HangeulChar.ㅕ => RomanizedText(List(Y, E, O))
      case HangeulChar.ㅖ => RomanizedText(List(Y, E))
      case HangeulChar.ㅗ => RomanizedText(List(O))
      case HangeulChar.ㅘ => RomanizedText(List(W, A))
      case HangeulChar.ㅙ => RomanizedText(List(W, A, E))
      case HangeulChar.ㅚ => RomanizedText(List(O, E))
      case HangeulChar.ㅛ => RomanizedText(List(Y, O))
      case HangeulChar.ㅜ => RomanizedText(List(U))
      case HangeulChar.ㅝ => RomanizedText(List(W, O))
      case HangeulChar.ㅞ => RomanizedText(List(W, E))
      case HangeulChar.ㅟ => RomanizedText(List(W, I))
      case HangeulChar.ㅠ => RomanizedText(List(Y, U))
      case HangeulChar.ㅡ => RomanizedText(List(E, U))
      case HangeulChar.ㅢ => RomanizedText(List(U, I))
      case HangeulChar.ㅣ => RomanizedText(List(I))
    }

  private def transcribeInitialConsonant(consonant: HangeulChar.Consonant): RomanizedText =
    consonant match {
      case HangeulChar.ㄱ => RomanizedText(List(G))
      case HangeulChar.ㄲ => RomanizedText(List(K, K))
      case HangeulChar.ㄴ => RomanizedText(List(N))
      case HangeulChar.ㄷ => RomanizedText(List(D))
      case HangeulChar.ㄸ => RomanizedText(List(T, T))
      case HangeulChar.ㄹ => RomanizedText(List(R))
      case HangeulChar.ㅁ => RomanizedText(List(M))
      case HangeulChar.ㅂ => RomanizedText(List(B))
      case HangeulChar.ㅃ => RomanizedText(List(P, P))
      case HangeulChar.ㅅ => RomanizedText(List(S))
      case HangeulChar.ㅆ => RomanizedText(List(S, S))
      case HangeulChar.ㅇ => RomanizedText(Nil) // Silent when in initial position
      case HangeulChar.ㅈ => RomanizedText(List(J))
      case HangeulChar.ㅉ => RomanizedText(List(J, J))
      case HangeulChar.ㅊ => RomanizedText(List(C, H))
      case HangeulChar.ㅋ => RomanizedText(List(K))
      case HangeulChar.ㅌ => RomanizedText(List(T))
      case HangeulChar.ㅍ => RomanizedText(List(P))
      case HangeulChar.ㅎ => RomanizedText(List(H))
    }

  private def transcribeFinalConsonant(consonant: HangeulChar.Consonant): RomanizedText =
    consonant match {
      case HangeulChar.ㄱ => RomanizedText(List(K))
      case HangeulChar.ㄲ => RomanizedText(List(K))
      case HangeulChar.ㄴ => RomanizedText(List(N))
      case HangeulChar.ㄷ => RomanizedText(List(T))
      case HangeulChar.ㄸ => RomanizedText(Nil)
      case HangeulChar.ㄹ => RomanizedText(List(L))
      case HangeulChar.ㅁ => RomanizedText(List(M))
      case HangeulChar.ㅂ => RomanizedText(List(P))
      case HangeulChar.ㅃ => RomanizedText(Nil)
      case HangeulChar.ㅅ => RomanizedText(List(T))
      case HangeulChar.ㅆ => RomanizedText(List(T))
      case HangeulChar.ㅇ => RomanizedText(List(N, G))
      case HangeulChar.ㅈ => RomanizedText(List(T))
      case HangeulChar.ㅉ => RomanizedText(Nil)
      case HangeulChar.ㅊ => RomanizedText(List(T))
      case HangeulChar.ㅋ => RomanizedText(List(K))
      case HangeulChar.ㅌ => RomanizedText(List(T))
      case HangeulChar.ㅍ => RomanizedText(List(P))
      case HangeulChar.ㅎ => RomanizedText(List(T))
    }

}
