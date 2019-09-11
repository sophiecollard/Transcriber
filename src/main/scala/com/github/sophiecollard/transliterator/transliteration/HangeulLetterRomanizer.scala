package com.github.sophiecollard.transliterator.transliteration

import com.github.sophiecollard.transliterator.model.{HangeulLetter, RomanLetter}
import com.github.sophiecollard.transliterator.model.RomanLetter._

private [transliteration] object HangeulLetterRomanizer {

  def transliterateVowel(vowel: HangeulLetter.Vowel): Vector[RomanLetter] =
    vowel match {
      case HangeulLetter.ㅏ => Vector(A)
      case HangeulLetter.ㅐ => Vector(A, E)
      case HangeulLetter.ㅑ => Vector(Y, A)
      case HangeulLetter.ㅒ => Vector(Y, A, E)
      case HangeulLetter.ㅓ => Vector(E, O)
      case HangeulLetter.ㅔ => Vector(E)
      case HangeulLetter.ㅕ => Vector(Y, E, O)
      case HangeulLetter.ㅖ => Vector(Y, E)
      case HangeulLetter.ㅗ => Vector(O)
      case HangeulLetter.ㅘ => Vector(W, A)
      case HangeulLetter.ㅙ => Vector(W, A, E)
      case HangeulLetter.ㅚ => Vector(O, E)
      case HangeulLetter.ㅛ => Vector(Y, O)
      case HangeulLetter.ㅜ => Vector(U)
      case HangeulLetter.ㅝ => Vector(W, O)
      case HangeulLetter.ㅞ => Vector(W, E)
      case HangeulLetter.ㅟ => Vector(W, I)
      case HangeulLetter.ㅠ => Vector(Y, U)
      case HangeulLetter.ㅡ => Vector(E, U)
      case HangeulLetter.ㅢ => Vector(U, I)
      case HangeulLetter.ㅣ => Vector(I)
    }

  def transliterateInitialConsonant(consonant: HangeulLetter.Consonant): Vector[RomanLetter] =
    consonant match {
      case HangeulLetter.ㄱ => Vector(G)
      case HangeulLetter.ㄲ => Vector(K, K)
      case HangeulLetter.ㄴ => Vector(N)
      case HangeulLetter.ㄷ => Vector(D)
      case HangeulLetter.ㄸ => Vector(T, T)
      case HangeulLetter.ㄹ => Vector(R)
      case HangeulLetter.ㅁ => Vector(M)
      case HangeulLetter.ㅂ => Vector(B)
      case HangeulLetter.ㅃ => Vector(P, P)
      case HangeulLetter.ㅅ => Vector(S)
      case HangeulLetter.ㅆ => Vector(S, S)
      case HangeulLetter.ㅇ => Vector.empty // Silent when in initial position
      case HangeulLetter.ㅈ => Vector(J)
      case HangeulLetter.ㅉ => Vector(J, J)
      case HangeulLetter.ㅊ => Vector(C, H)
      case HangeulLetter.ㅋ => Vector(K)
      case HangeulLetter.ㅌ => Vector(T)
      case HangeulLetter.ㅍ => Vector(P)
      case HangeulLetter.ㅎ => Vector(H)
    }

  def transliterateFinalConsonant(consonant: HangeulLetter.Consonant): Vector[RomanLetter] =
    consonant match {
      case HangeulLetter.ㄱ => Vector(K)
      case HangeulLetter.ㄲ => Vector(K)
      case HangeulLetter.ㄴ => Vector(N)
      case HangeulLetter.ㄷ => Vector(T)
      case HangeulLetter.ㄸ => Vector.empty
      case HangeulLetter.ㄹ => Vector(L)
      case HangeulLetter.ㅁ => Vector(M)
      case HangeulLetter.ㅂ => Vector(P)
      case HangeulLetter.ㅃ => Vector.empty
      case HangeulLetter.ㅅ => Vector(T)
      case HangeulLetter.ㅆ => Vector(T)
      case HangeulLetter.ㅇ => Vector(N, G)
      case HangeulLetter.ㅈ => Vector(T)
      case HangeulLetter.ㅉ => Vector.empty
      case HangeulLetter.ㅊ => Vector(T)
      case HangeulLetter.ㅋ => Vector(K)
      case HangeulLetter.ㅌ => Vector(T)
      case HangeulLetter.ㅍ => Vector(P)
      case HangeulLetter.ㅎ => Vector(T)
    }

}
