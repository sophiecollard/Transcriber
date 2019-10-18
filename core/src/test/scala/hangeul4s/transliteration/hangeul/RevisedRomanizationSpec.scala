package hangeul4s.transliteration.hangeul

import hangeul4s.model.hangeul.HangeulJamo._
import hangeul4s.model.hangeul.HangeulSyllable._
import hangeul4s.model.hangeul.HangeulTextElement
import hangeul4s.model.romanization.RomanLetter._
import hangeul4s.model.romanization.RomanizedTextElement
import org.specs2.mutable.Specification

class RevisedRomanizationSpec extends Specification {

  "RevisedRomanization#transliterator" should {

    "romanize words with ㄱ in final position" in {

      "국 => guk" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄱ, Medial.ㅜ, Final.ㄱ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(G, U, K)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "국이 => gugi (ㄱ+ㅇ => g)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄱ, Medial.ㅜ, Final.ㄱ),
            TwoJamo(Initial.ㅇ, Medial.ㅣ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(G, U, G, I)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄱ+ㄱ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅂ, Medial.ㅏ, Final.ㄱ),
            TwoJamo(Initial.ㄱ, Medial.ㅣ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(B, A, K, G, I)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "학년 => hangnyeon (ㄱ+ㄴ => ngn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅎ, Medial.ㅏ, Final.ㄱ),
            ThreeJamo(Initial.ㄴ, Medial.ㅕ, Final.ㄴ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(H, A, N, G, N, Y, E, O, N)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄱ+ㄷ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅂ, Medial.ㅏ, Final.ㄱ),
            TwoJamo(Initial.ㄷ, Medial.ㅣ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(B, A, K, D, I)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄱ+ㄹ => ngn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅂ, Medial.ㅏ, Final.ㄱ),
            TwoJamo(Initial.ㄹ, Medial.ㅣ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(B, A, N, G, N, I)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄱ+ㅁ => ngm)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅂ, Medial.ㅏ, Final.ㄱ),
            TwoJamo(Initial.ㅁ, Medial.ㅣ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(B, A, N, G, M, I)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "불국사 => bulguksa (ㄱ+ㅅ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅂ, Medial.ㅜ, Final.ㄹ),
            ThreeJamo(Initial.ㄱ, Medial.ㅜ, Final.ㄱ),
            TwoJamo(Initial.ㅅ, Medial.ㅏ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(B, U, L, G, U, K, S, A)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "묵호 => mukho (ㄱ+ㅎ => kh/k)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅁ, Medial.ㅜ, Final.ㄱ),
            TwoJamo(Initial.ㅎ, Medial.ㅗ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(M, U, K, H, O)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㄲ in final position" in {

      "밖 => bak" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅂ, Medial.ㅏ, Final.ㄲ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(B, A, K)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "밖에 => bakke (ㄲ+ㅇ => kk)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅂ, Medial.ㅏ, Final.ㄲ),
            TwoJamo(Initial.ㅇ, Medial.ㅔ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(B, A, K, K, E)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㄳ in final position" in {

      "몫 => mok" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅁ, Medial.ㅗ, Final.ㄳ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(M, O, K)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "몫은 => mokseun (ㄳ+ㅇ => ks)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅁ, Medial.ㅗ, Final.ㄳ),
            ThreeJamo(Initial.ㅇ, Medial.ㅡ, Final.ㄴ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(M, O, K, S, E, U, N)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㄴ in final position" in {

      "문 => mun" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅁ, Medial.ㅜ, Final.ㄴ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(M, U, N)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "문이 => muni (ㄴ+ㅇ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅁ, Medial.ㅜ, Final.ㄴ),
            TwoJamo(Initial.ㅇ, Medial.ㅣ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(M, U, N, I)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "성산읍 => seongsaneup (ㄴ+ㅇ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅅ, Medial.ㅓ, Final.ㅇ),
            ThreeJamo(Initial.ㅅ, Medial.ㅏ, Final.ㄴ),
            ThreeJamo(Initial.ㅇ, Medial.ㅡ, Final.ㅂ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(S, E, O, N, G, S, A, N, E, U, P)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄴ+ㄱ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅅ, Medial.ㅐ, Final.ㄴ),
            TwoJamo(Initial.ㄱ, Medial.ㅢ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(S, A, E, N, G, U, I)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "안녕하세요 => annyeonghaseyo (ㄴ+ㄴ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅇ, Medial.ㅏ, Final.ㄴ),
            ThreeJamo(Initial.ㄴ, Medial.ㅕ, Final.ㅇ),
            TwoJamo(Initial.ㅎ, Medial.ㅏ),
            TwoJamo(Initial.ㅅ, Medial.ㅔ),
            TwoJamo(Initial.ㅇ, Medial.ㅛ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(A, N, N, Y, E, O, N, G, H, A, S, E, Y, O)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄴ+ㄷ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅅ, Medial.ㅐ, Final.ㄴ),
            TwoJamo(Initial.ㄷ, Medial.ㅢ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(S, A, E, N, D, U, I)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "신라 => silla (ㄴ+ㄹ => ll/nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅅ, Medial.ㅣ, Final.ㄴ),
            TwoJamo(Initial.ㄹ, Medial.ㅏ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(S, I, L, L, A)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄴ+ㅁ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅅ, Medial.ㅐ, Final.ㄴ),
            TwoJamo(Initial.ㅁ, Medial.ㅢ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(S, A, E, N, M, U, I)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄴ+ㅎ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅅ, Medial.ㅐ, Final.ㄴ),
            TwoJamo(Initial.ㅎ, Medial.ㅢ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(S, A, E, N, H, U, I)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㄵ in final position" in {

      "앉아서 => anjaseo (ㄵ+ㅇ => nj)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅇ, Medial.ㅏ, Final.ㄵ),
            TwoJamo(Initial.ㅇ, Medial.ㅏ),
            TwoJamo(Initial.ㅅ, Medial.ㅓ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(A, N, J, A, S, E, O)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "앉다 => anda (ㄵ+ㄷ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅇ, Medial.ㅏ, Final.ㄵ),
            TwoJamo(Initial.ㄷ, Medial.ㅏ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(A, N, D, A)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㄶ in final position" in {

      "많은 => maneun (ㄶ+ㅇ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅁ, Medial.ㅏ, Final.ㄶ),
            ThreeJamo(Initial.ㅇ, Medial.ㅡ, Final.ㄴ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(M, A, N, E, U, N)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "많다 => manta (ㄶ+ㄷ => nt)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅁ, Medial.ㅏ, Final.ㄶ),
            TwoJamo(Initial.ㄷ, Medial.ㅏ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(M, A, N, T, A)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㄷ in final position" in {

      "닫으면 => dadeumyeon (ㄷ+ㅇ => d/j)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄷ, Medial.ㅏ, Final.ㄷ),
            TwoJamo(Initial.ㅇ, Medial.ㅡ),
            ThreeJamo(Initial.ㅁ, Medial.ㅕ, Final.ㄴ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(D, A, D, E, U, M, Y, E, O, N)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "닫다 => datda" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄷ, Medial.ㅏ, Final.ㄷ),
            TwoJamo(Initial.ㄷ, Medial.ㅏ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(D, A, T, D, A)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄷ+ㄱ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅈ, Medial.ㅑ, Final.ㄷ),
            TwoJamo(Initial.ㄱ, Medial.ㅡ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(J, Y, A, T, G, E, U)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄷ+ㄴ => nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅈ, Medial.ㅑ, Final.ㄷ),
            TwoJamo(Initial.ㄴ, Medial.ㅡ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(J, Y, A, N, N, E, U)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄷ+ㄷ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅈ, Medial.ㅑ, Final.ㄷ),
            TwoJamo(Initial.ㄷ, Medial.ㅡ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(J, Y, A, T, D, E, U)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄷ+ㄹ => nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅈ, Medial.ㅑ, Final.ㄷ),
            TwoJamo(Initial.ㄹ, Medial.ㅡ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(J, Y, A, N, N, E, U)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄷ+ㅁ => nm)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅈ, Medial.ㅑ, Final.ㄷ),
            TwoJamo(Initial.ㅁ, Medial.ㅡ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(J, Y, A, N, M, E, U)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄷ+ㅎ => th/t/ch)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅈ, Medial.ㅑ, Final.ㄷ),
            TwoJamo(Initial.ㅎ, Medial.ㅡ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(J, Y, A, T, H, E, U) // TODO jyateu

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㄹ in final position" in {

      "별 => byeol" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅂ, Medial.ㅕ, Final.ㄹ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(B, Y, E, O, L)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "설악 => seorak (ㄹ+ㅇ => r)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅅ, Medial.ㅓ, Final.ㄹ),
            ThreeJamo(Initial.ㅇ, Medial.ㅏ, Final.ㄱ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(S, E, O, R, A, K)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "딸기 => ttalgi (ㄹ+ㄱ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄸ, Medial.ㅏ, Final.ㄹ),
            TwoJamo(Initial.ㄱ, Medial.ㅣ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(T, T, A, L, G, I)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "칠곡 => chilgok (ㄹ+ㄱ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅊ, Medial.ㅣ, Final.ㄹ),
            ThreeJamo(Initial.ㄱ, Medial.ㅗ, Final.ㄱ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(C, H, I, L, G, O, K)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "일년 => illyeon (ㄹ+ㄴ => ll/nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅇ, Medial.ㅣ, Final.ㄹ),
            ThreeJamo(Initial.ㄴ, Medial.ㅕ, Final.ㄴ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(I, L, L, Y, E, O, N)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄹ+ㄷ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅊ, Medial.ㅒ, Final.ㄹ),
            TwoJamo(Initial.ㄷ, Medial.ㅠ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(C, H, Y, A, E, L, D, Y, U)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "울릉 => ulleung (ㄹ+ㄹ => ll)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅇ, Medial.ㅜ, Final.ㄹ),
            ThreeJamo(Initial.ㄹ, Medial.ㅡ, Final.ㅇ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(U, L, L, E, U, N, G)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄹ+ㅁ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅊ, Medial.ㅒ, Final.ㄹ),
            TwoJamo(Initial.ㅁ, Medial.ㅠ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(C, H, Y, A, E, L, M, Y, U)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "울산 => ulsan (ㄹ+ㅅ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅇ, Medial.ㅜ, Final.ㄹ),
            ThreeJamo(Initial.ㅅ, Medial.ㅏ, Final.ㄴ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(U, L, S, A, N)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄹ+ㅎ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅊ, Medial.ㅒ, Final.ㄹ),
            TwoJamo(Initial.ㅎ, Medial.ㅠ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(C, H, Y, A, E, L, H, Y, U)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㄺ in final position" in {

      "읽으면 => ilgeumyeon (ㄺ+ㅇ => lg)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅇ, Medial.ㅣ, Final.ㄺ),
            TwoJamo(Initial.ㅇ, Medial.ㅡ),
            ThreeJamo(Initial.ㅁ, Medial.ㅕ, Final.ㄴ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(I, L, G, E, U, M, Y, E, O, N)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "읽다 => ikda" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅇ, Medial.ㅣ, Final.ㄺ),
            TwoJamo(Initial.ㄷ, Medial.ㅏ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(I, K, D, A)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㄻ in final position" in {

      "삶 => sam" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅅ, Medial.ㅏ, Final.ㄻ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(S, A, M)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "삶은 => salmeun (ㄻ+ㅇ => lm)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅅ, Medial.ㅏ, Final.ㄻ),
            ThreeJamo(Initial.ㅇ, Medial.ㅡ, Final.ㄴ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(S, A, L, M, E, U, N)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㄼ in final position" in {

      "넓어서 => neolbeoseo (ㄼ+ㅇ => l)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄴ, Medial.ㅓ, Final.ㄼ),
            TwoJamo(Initial.ㅇ, Medial.ㅓ),
            TwoJamo(Initial.ㅅ, Medial.ㅓ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(N, E, O, L, B, E, O, S, E, O)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "넓다 => neolda (ㄼ+ㄷ => ld)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄴ, Medial.ㅓ, Final.ㄼ),
            TwoJamo(Initial.ㄷ, Medial.ㅏ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(N, E, O, L, D, A)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㄽ in final position" in todo

    "romanize words with ㄾ in final position" in {

      "핥은 => halteun (ㄾ+ㅇ => lt)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅎ, Medial.ㅏ, Final.ㄾ),
            ThreeJamo(Initial.ㅇ, Medial.ㅡ, Final.ㄴ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(H, A, L, T, E, U, N)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "핥다 => halda (ㄾ+ㄷ => ld)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅎ, Medial.ㅏ, Final.ㄾ),
            TwoJamo(Initial.ㄷ, Medial.ㅏ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(H, A, L, D, A)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㄿ in final position" in {

      "읊으면 => eulpeumyeon (ㄿ+ㅇ => lp)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅇ, Medial.ㅡ, Final.ㄿ),
            TwoJamo(Initial.ㅇ, Medial.ㅡ),
            ThreeJamo(Initial.ㅁ, Medial.ㅕ, Final.ㄴ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(E, U, L, P, E, U, M, Y, E, O, N)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "읊다 => eupda (ㄿ+ㄷ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅇ, Medial.ㅡ, Final.ㄿ),
            TwoJamo(Initial.ㄷ, Medial.ㅏ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(E, U, P, D, A)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㅀ in final position" in {

      "끓으면 => kkeureumyeon (ㅀ+ㅇ => r)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄲ, Medial.ㅡ, Final.ㅀ),
            TwoJamo(Initial.ㅇ, Medial.ㅡ),
            ThreeJamo(Initial.ㅁ, Medial.ㅕ, Final.ㄴ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, K, E, U, R, E, U, M, Y, E, O, N)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "끓다 => kkeulta (ㅀ+ㄷ => lt)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄲ, Medial.ㅡ, Final.ㅀ),
            TwoJamo(Initial.ㄷ, Medial.ㅏ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, K, E, U, L, T, A)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㅁ in final position" in {

      "몸 => mom" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅁ, Medial.ㅗ, Final.ㅁ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(M, O, M)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "몸이 => momi (ㅁ+ㅇ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅁ, Medial.ㅗ, Final.ㅁ),
            TwoJamo(Initial.ㅇ, Medial.ㅣ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(M, O, M, I)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "금곡 =>  geumgok (ㅁ+ㄱ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄱ, Medial.ㅡ, Final.ㅁ),
            ThreeJamo(Initial.ㄱ, Medial.ㅗ, Final.ㄱ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(G, E, U, M, G, O, K)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅁ+ㄴ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅋ, Medial.ㅓ, Final.ㅁ),
            TwoJamo(Initial.ㄴ, Medial.ㅟ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, E, O, M, N, W, I)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅁ+ㄷ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅋ, Medial.ㅓ, Final.ㅁ),
            TwoJamo(Initial.ㄷ, Medial.ㅟ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, E, O, M, D, W, I)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅁ+ㄹ => mn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅋ, Medial.ㅓ, Final.ㅁ),
            TwoJamo(Initial.ㄹ, Medial.ㅟ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, E, O, M, N, W, I)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅁ+ㅁ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅋ, Medial.ㅓ, Final.ㅁ),
            TwoJamo(Initial.ㅁ, Medial.ㅟ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, E, O, M, M, W, I)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅁ+ㅎ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅋ, Medial.ㅓ, Final.ㅁ),
            TwoJamo(Initial.ㅎ, Medial.ㅟ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, E, O, M, H, W, I)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㅂ in final position" in {

      "답 => dap" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄷ, Medial.ㅏ, Final.ㅂ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(D, A, P)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "답은 => dabeun (ㅂ+ㅇ => b)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄷ, Medial.ㅏ, Final.ㅂ),
            ThreeJamo(Initial.ㅇ, Medial.ㅡ, Final.ㄴ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(D, A, B, E, U, N)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅂ+ㄱ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅌ, Medial.ㅔ, Final.ㅂ),
            TwoJamo(Initial.ㄱ, Medial.ㅞ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(T, E, P, G, W, E)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "감사합니다 => gamsahamnida (ㅂ+ㄴ => mn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄱ, Medial.ㅏ, Final.ㅁ),
            TwoJamo(Initial.ㅅ, Medial.ㅏ),
            ThreeJamo(Initial.ㅎ, Medial.ㅏ, Final.ㅂ),
            TwoJamo(Initial.ㄴ, Medial.ㅣ),
            TwoJamo(Initial.ㄷ, Medial.ㅏ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(G, A, M, S, A, H, A, M, N, I, D, A)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅂ+ㄷ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅌ, Medial.ㅔ, Final.ㅂ),
            TwoJamo(Initial.ㄷ, Medial.ㅞ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(T, E, P, D, W, E)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅂ+ㄹ => mn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅌ, Medial.ㅔ, Final.ㅂ),
            TwoJamo(Initial.ㄹ, Medial.ㅞ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(T, E, M, N, W, E)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅂ+ㅁ => mm)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅌ, Medial.ㅔ, Final.ㅂ),
            TwoJamo(Initial.ㅁ, Medial.ㅞ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(T, E, M, M, W, E)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "잡혀 => japhyeo (ㅂ+ㅎ => ph/p)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅈ, Medial.ㅏ, Final.ㅂ),
            TwoJamo(Initial.ㅎ, Medial.ㅕ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(J, A, P, H, Y, E, O)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㅄ in final position" in {

      "없어서 => eopseoseo (ㅄ+ㅇ => ps)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅇ, Medial.ㅓ, Final.ㅄ),
            TwoJamo(Initial.ㅇ, Medial.ㅓ),
            TwoJamo(Initial.ㅅ, Medial.ㅓ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(E, O, P, S, E, O, S, E, O)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "없다 => eopda" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅇ, Medial.ㅓ, Final.ㅄ),
            TwoJamo(Initial.ㄷ, Medial.ㅏ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(E, O, P, D, A)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㅅ in final position" in {

      "옷 => ot" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅇ, Medial.ㅗ, Final.ㅅ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(O, T)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "옷이 => osi (ㅅ+ㅇ => s)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅇ, Medial.ㅗ, Final.ㅅ),
            TwoJamo(Initial.ㅇ, Medial.ㅣ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(O, S, I)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅅ+ㄱ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅍ, Medial.ㅕ, Final.ㅅ),
            TwoJamo(Initial.ㄱ, Medial.ㅝ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(P, Y, E, O, T, G, W, O)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅅ+ㄴ => nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅍ, Medial.ㅕ, Final.ㅅ),
            TwoJamo(Initial.ㄴ, Medial.ㅝ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(P, Y, E, O, N, N, W, O)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅅ+ㄷ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅍ, Medial.ㅕ, Final.ㅅ),
            TwoJamo(Initial.ㄷ, Medial.ㅝ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(P, Y, E, O, T, D, W, O)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅅ+ㄹ => nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅍ, Medial.ㅕ, Final.ㅅ),
            TwoJamo(Initial.ㄹ, Medial.ㅝ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(P, Y, E, O, N, N, W, O)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅅ+ㅁ => nm)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅍ, Medial.ㅕ, Final.ㅅ),
            TwoJamo(Initial.ㅁ, Medial.ㅝ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(P, Y, E, O, N, M, W, O)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅅ+ㅎ => th/t/ch)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅍ, Medial.ㅕ, Final.ㅅ),
            TwoJamo(Initial.ㅎ, Medial.ㅝ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(P, Y, E, O, T, H, W, O)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㅆ in final position" in {

      "있다 => itda" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅇ, Medial.ㅣ, Final.ㅆ),
            TwoJamo(Initial.ㄷ, Medial.ㅏ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(I, T, D, A)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "있어서 => isseoseo (ㅆ+ㅇ => ss)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅇ, Medial.ㅣ, Final.ㅆ),
            TwoJamo(Initial.ㅇ, Medial.ㅓ),
            TwoJamo(Initial.ㅅ, Medial.ㅓ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(I, S, S, E, O, S, E, O)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㅇ in final position" in {

      "강 => gang" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄱ, Medial.ㅏ, Final.ㅇ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(G, A, N, G)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "강이 => gangi (ㅇ+ㅇ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄱ, Medial.ㅏ, Final.ㅇ),
            TwoJamo(Initial.ㅇ, Medial.ㅣ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(G, A, N, G, I)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅇ+ㄱ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅎ, Medial.ㅖ, Final.ㅇ),
            TwoJamo(Initial.ㄱ, Medial.ㅜ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(H, Y, E, N, G, G, U)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅇ+ㄴ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅎ, Medial.ㅖ, Final.ㅇ),
            TwoJamo(Initial.ㄴ, Medial.ㅜ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(H, Y, E, N, G, N, U)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅇ+ㄷ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅎ, Medial.ㅖ, Final.ㅇ),
            TwoJamo(Initial.ㄷ, Medial.ㅜ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(H, Y, E, N, G, D, U)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "종로구 => jongnogu (ㅇ+ㄹ => ngn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅈ, Medial.ㅗ, Final.ㅇ),
            TwoJamo(Initial.ㄹ, Medial.ㅗ),
            TwoJamo(Initial.ㄱ, Medial.ㅜ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(J, O, N, G, N, O, G, U)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅇ+ㅁ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅎ, Medial.ㅖ, Final.ㅇ),
            TwoJamo(Initial.ㅁ, Medial.ㅜ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(H, Y, E, N, G, M, U)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅇ+ㅎ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅎ, Medial.ㅖ, Final.ㅇ),
            TwoJamo(Initial.ㅎ, Medial.ㅜ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(H, Y, E, N, G, H, U)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㅈ in final position" in {

      "낮 => nat" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄴ, Medial.ㅏ, Final.ㅈ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(N, A, T)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "낮에 => naje (ㅈ+ㅇ => j)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄴ, Medial.ㅏ, Final.ㅈ),
            TwoJamo(Initial.ㅇ, Medial.ㅔ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(N, A, J, E)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅈ+ㄱ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄲ, Medial.ㅗ, Final.ㅈ),
            TwoJamo(Initial.ㄱ, Medial.ㅛ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, K, O, T, G, Y, O)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅈ+ㄴ => nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄲ, Medial.ㅗ, Final.ㅈ),
            TwoJamo(Initial.ㄴ, Medial.ㅛ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, K, O, N, N, Y, O)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅈ+ㄷ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄲ, Medial.ㅗ, Final.ㅈ),
            TwoJamo(Initial.ㄷ, Medial.ㅛ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, K, O, T, D, Y, O)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅈ+ㄹ => nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄲ, Medial.ㅗ, Final.ㅈ),
            TwoJamo(Initial.ㄹ, Medial.ㅛ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, K, O, N, N, Y, O)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅈ+ㅁ => nm)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄲ, Medial.ㅗ, Final.ㅈ),
            TwoJamo(Initial.ㅁ, Medial.ㅛ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, K, O, N, M, Y, O)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅈ+ㅎ => th/t/ch)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄲ, Medial.ㅗ, Final.ㅈ),
            TwoJamo(Initial.ㅎ, Medial.ㅛ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, K, O, T, H, Y, O)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㅊ in final position" in {

      "꽃" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄲ, Medial.ㅗ, Final.ㅊ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, K, O, T)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "꽃이 => kkochi (ㅊ+ㅇ => ch)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄲ, Medial.ㅗ, Final.ㅊ),
            TwoJamo(Initial.ㅇ, Medial.ㅣ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, K, O, C, H, I)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅊ+ㄱ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄸ, Medial.ㅘ, Final.ㅊ),
            TwoJamo(Initial.ㄱ, Medial.ㅚ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(T, T, W, A, T, G, O, E)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅊ+ㄴ => nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄸ, Medial.ㅘ, Final.ㅊ),
            TwoJamo(Initial.ㄴ, Medial.ㅚ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(T, T, W, A, N, N, O, E)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅊ+ㄷ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄸ, Medial.ㅘ, Final.ㅊ),
            TwoJamo(Initial.ㄷ, Medial.ㅚ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(T, T, W, A, T, D, O, E)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅊ+ㄹ => nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄸ, Medial.ㅘ, Final.ㅊ),
            TwoJamo(Initial.ㄹ, Medial.ㅚ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(T, T, W, A, N, N, O, E)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅊ+ㅁ => nm)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄸ, Medial.ㅘ, Final.ㅊ),
            TwoJamo(Initial.ㅁ, Medial.ㅚ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(T, T, W, A, N, M, O, E)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅊ+ㅎ => th/t/ch)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄸ, Medial.ㅘ, Final.ㅊ),
            TwoJamo(Initial.ㅎ, Medial.ㅚ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(T, T, W, A, T, H, O, E)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㅋ in final position" in {

      "부엌 => bueok" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            TwoJamo(Initial.ㅂ, Medial.ㅜ),
            ThreeJamo(Initial.ㅇ, Medial.ㅓ, Final.ㅋ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(B, U, E, O, K)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "부엌에 => bueoke" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            TwoJamo(Initial.ㅂ, Medial.ㅜ),
            ThreeJamo(Initial.ㅇ, Medial.ㅓ, Final.ㅋ),
            TwoJamo(Initial.ㅇ, Medial.ㅔ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(B, U, E, O, K, E)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㅌ in final position" in {

      "끝 => kkeut" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄲ, Medial.ㅡ, Final.ㅌ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, K, E, U, T)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "끝에 => kkeute (ㅌ+ㅇ => t/ch)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄲ, Medial.ㅡ, Final.ㅌ),
            TwoJamo(Initial.ㅇ, Medial.ㅔ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, K, E, U, T, E)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅌ+ㄱ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅃ, Medial.ㅙ, Final.ㅌ),
            TwoJamo(Initial.ㄱ, Medial.ㅙ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(P, P, W, A, E, T, G, W, A, E)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅌ+ㄴ => nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅃ, Medial.ㅙ, Final.ㅌ),
            TwoJamo(Initial.ㄴ, Medial.ㅙ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(P, P, W, A, E, N, N, W, A, E)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅌ+ㄷ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅃ, Medial.ㅙ, Final.ㅌ),
            TwoJamo(Initial.ㄷ, Medial.ㅙ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(P, P, W, A, E, T, D, W, A, E)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅌ+ㄹ => nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅃ, Medial.ㅙ, Final.ㅌ),
            TwoJamo(Initial.ㄹ, Medial.ㅙ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(P, P, W, A, E, N, N, W, A, E)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅌ+ㅁ => nm)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅃ, Medial.ㅙ, Final.ㅌ),
            TwoJamo(Initial.ㅁ, Medial.ㅙ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(P, P, W, A, E, N, M, W, A, E)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅌ+ㅎ => th/t/ch)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅃ, Medial.ㅙ, Final.ㅌ),
            TwoJamo(Initial.ㅎ, Medial.ㅙ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(P, P, W, A, E, T, H, W, A, E)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㅍ in final position" in {

      "앞 => ap" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅇ, Medial.ㅏ, Final.ㅍ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(A, P)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "앞에 => ape" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅇ, Medial.ㅏ, Final.ㅍ),
            TwoJamo(Initial.ㅇ, Medial.ㅔ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(A, P, E)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㅎ in final position" in {

      "놓다 => nota" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄴ, Medial.ㅗ, Final.ㅎ),
            TwoJamo(Initial.ㄷ, Medial.ㅏ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(N, O, T, A)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "놓으면 => noeumyeon (ㅎ+ㅇ => silent)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄴ, Medial.ㅗ, Final.ㅎ),
            TwoJamo(Initial.ㅇ, Medial.ㅡ),
            ThreeJamo(Initial.ㅁ, Medial.ㅕ, Final.ㄴ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(N, O, E, U, M, Y, E, O, N)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "좋고 => joko (ㅎ+ㄱ => k)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅈ, Medial.ㅗ, Final.ㅎ),
            TwoJamo(Initial.ㄱ, Medial.ㅗ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(J, O, K, O)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅎ+ㄴ => nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅆ, Medial.ㅚ, Final.ㅎ),
            TwoJamo(Initial.ㄴ, Medial.ㅘ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(S, S, O, E, N, N, W, A)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "놓다 => nota (ㅎ+ㄷ => t)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄴ, Medial.ㅗ, Final.ㅎ),
            TwoJamo(Initial.ㄷ, Medial.ㅏ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(N, O, T, A)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅎ+ㄹ => nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅆ, Medial.ㅚ, Final.ㅎ),
            TwoJamo(Initial.ㄴ, Medial.ㅘ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(S, S, O, E, N, N, W, A)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅎ+ㅁ => nm)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅆ, Medial.ㅚ, Final.ㅎ),
            TwoJamo(Initial.ㅁ, Medial.ㅘ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(S, S, O, E, N, M, W, A)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      "낳지 => nachi (ㅎ+ㅈ => ch)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㄴ, Medial.ㅏ, Final.ㅎ),
            TwoJamo(Initial.ㅈ, Medial.ㅣ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(N, A, C, H, I)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅎ+ㅎ => t)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllables(
            ThreeJamo(Initial.ㅆ, Medial.ㅚ, Final.ㅎ),
            TwoJamo(Initial.ㅎ, Medial.ㅘ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(S, S, O, E, T, W, A)

        RevisedRomanization.transliterator.transliterate(input) must beRight(expectedOutput)
      }

    }

  }

}
