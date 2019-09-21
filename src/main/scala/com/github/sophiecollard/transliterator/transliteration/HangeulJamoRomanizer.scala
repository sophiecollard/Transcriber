package com.github.sophiecollard.transliterator.transliteration

import com.github.sophiecollard.transliterator.model.hangeul.HangeulJamo
import com.github.sophiecollard.transliterator.model.romanization.RomanLetter
import com.github.sophiecollard.transliterator.model.romanization.RomanLetter._

private [transliteration] object HangeulJamoRomanizer {

  def transliterateInitial(jamo: HangeulJamo.Initial): Vector[RomanLetter] =
    jamo match {
      case HangeulJamo.Initial.ㄱ => Vector(G)
      case HangeulJamo.Initial.ㄲ => Vector(K, K)
      case HangeulJamo.Initial.ㄴ => Vector(N)
      case HangeulJamo.Initial.ㄷ => Vector(D)
      case HangeulJamo.Initial.ㄸ => Vector(T, T)
      case HangeulJamo.Initial.ㄹ => Vector(R)
      case HangeulJamo.Initial.ㅁ => Vector(M)
      case HangeulJamo.Initial.ㅂ => Vector(B)
      case HangeulJamo.Initial.ㅃ => Vector(P, P)
      case HangeulJamo.Initial.ㅅ => Vector(S)
      case HangeulJamo.Initial.ㅆ => Vector(S, S)
      case HangeulJamo.Initial.ㅇ => Vector.empty // Silent in initial position
      case HangeulJamo.Initial.ㅈ => Vector(J)
      case HangeulJamo.Initial.ㅉ => Vector(J, J)
      case HangeulJamo.Initial.ㅊ => Vector(C, H)
      case HangeulJamo.Initial.ㅋ => Vector(K)
      case HangeulJamo.Initial.ㅌ => Vector(T)
      case HangeulJamo.Initial.ㅍ => Vector(P)
      case HangeulJamo.Initial.ㅎ => Vector(H)
    }

  def transliterateMedial(jamo: HangeulJamo.Medial): Vector[RomanLetter] =
    jamo match {
      case HangeulJamo.Medial.ㅏ => Vector(A)
      case HangeulJamo.Medial.ㅐ => Vector(A, E)
      case HangeulJamo.Medial.ㅑ => Vector(Y, A)
      case HangeulJamo.Medial.ㅒ => Vector(Y, A, E)
      case HangeulJamo.Medial.ㅓ => Vector(E, O)
      case HangeulJamo.Medial.ㅔ => Vector(E)
      case HangeulJamo.Medial.ㅕ => Vector(Y, E, O)
      case HangeulJamo.Medial.ㅖ => Vector(Y, E)
      case HangeulJamo.Medial.ㅗ => Vector(O)
      case HangeulJamo.Medial.ㅘ => Vector(W, A)
      case HangeulJamo.Medial.ㅙ => Vector(W, A, E)
      case HangeulJamo.Medial.ㅚ => Vector(O, E)
      case HangeulJamo.Medial.ㅛ => Vector(Y, O)
      case HangeulJamo.Medial.ㅜ => Vector(U)
      case HangeulJamo.Medial.ㅝ => Vector(W, O)
      case HangeulJamo.Medial.ㅞ => Vector(W, E)
      case HangeulJamo.Medial.ㅟ => Vector(W, I)
      case HangeulJamo.Medial.ㅠ => Vector(Y, U)
      case HangeulJamo.Medial.ㅡ => Vector(E, U)
      case HangeulJamo.Medial.ㅢ => Vector(U, I)
      case HangeulJamo.Medial.ㅣ => Vector(I)
    }

  def transliterateFinal(jamo: HangeulJamo.Final): Vector[RomanLetter] =
    jamo match {
      case HangeulJamo.Final.ㄱ => Vector(K)
      case HangeulJamo.Final.ㄲ => Vector(K)
      case HangeulJamo.Final.ㄳ => Vector(K) // Treated as ㄱ
      case HangeulJamo.Final.ㄴ => Vector(N)
      case HangeulJamo.Final.ㄵ => Vector(N) // Treated as ㄴ
      case HangeulJamo.Final.ㄶ => Vector(N) // Treated as ㄴ
      case HangeulJamo.Final.ㄷ => Vector(T)
      case HangeulJamo.Final.ㄹ => Vector(L)
      case HangeulJamo.Final.ㄺ => Vector(K) // Treated as ㄱ
      case HangeulJamo.Final.ㄻ => Vector(M) // Treated as ㅁ
      case HangeulJamo.Final.ㄼ => Vector(P) // Treated as ㅂ
      case HangeulJamo.Final.ㄽ => Vector(L) // Treated as ㄹ
      case HangeulJamo.Final.ㄾ => Vector(L) // Treated as ㄹ
      case HangeulJamo.Final.ㄿ => Vector(P) // Treated as ㅍ or ㅂ
      case HangeulJamo.Final.ㅀ => Vector(L) // Treated as ㄹ
      case HangeulJamo.Final.ㅁ => Vector(M)
      case HangeulJamo.Final.ㅂ => Vector(P)
      case HangeulJamo.Final.ㅄ => Vector(P) // Treated as ㅂ
      case HangeulJamo.Final.ㅅ => Vector(T)
      case HangeulJamo.Final.ㅆ => Vector(T)
      case HangeulJamo.Final.ㅇ => Vector(N, G)
      case HangeulJamo.Final.ㅈ => Vector(T)
      case HangeulJamo.Final.ㅊ => Vector(T)
      case HangeulJamo.Final.ㅋ => Vector(K)
      case HangeulJamo.Final.ㅌ => Vector(T)
      case HangeulJamo.Final.ㅍ => Vector(P)
      case HangeulJamo.Final.ㅎ => Vector(T)
    }

}
