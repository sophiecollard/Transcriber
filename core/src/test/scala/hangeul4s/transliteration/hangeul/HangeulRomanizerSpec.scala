package hangeul4s.transliteration.hangeul

import hangeul4s.model.hangeul.HangeulJamo._
import hangeul4s.model.hangeul.HangeulSyllabicBlock._
import hangeul4s.model.hangeul.HangeulTextElement
import hangeul4s.model.romanization.RomanLetter._
import hangeul4s.model.romanization.RomanizedTextElement
import org.specs2.mutable.Specification

class HangeulRomanizerSpec extends Specification {

  "HangeulRomanizer" should {

    "romanize words with ㄱ in final position" in {

      // TODO find a better example
      "(ㄱ+ㅇ => g)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅂ, Medial.ㅏ, Final.ㄱ),
            TwoLetter(Initial.ㅇ, Medial.ㅣ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(B, A, G, I)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄱ+ㄱ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅂ, Medial.ㅏ, Final.ㄱ),
            TwoLetter(Initial.ㄱ, Medial.ㅣ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(B, A, K, G, I)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      "학년 => hangnyeon (ㄱ+ㄴ => ngn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅎ, Medial.ㅏ, Final.ㄱ),
            ThreeLetter(Initial.ㄴ, Medial.ㅕ, Final.ㄴ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(
            H, A, N, G, N, Y, E, O, N
          )

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄱ+ㄷ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅂ, Medial.ㅏ, Final.ㄱ),
            TwoLetter(Initial.ㄷ, Medial.ㅣ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(B, A, K, D, I)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄱ+ㄹ => ngn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅂ, Medial.ㅏ, Final.ㄱ),
            TwoLetter(Initial.ㄹ, Medial.ㅣ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(B, A, N, G, N, I)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄱ+ㅁ => ngm)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅂ, Medial.ㅏ, Final.ㄱ),
            TwoLetter(Initial.ㅁ, Medial.ㅣ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(B, A, N, G, M, I)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      "불국사 => bulguksa (ㄱ+ㅅ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅂ, Medial.ㅜ, Final.ㄹ),
            ThreeLetter(Initial.ㄱ, Medial.ㅜ, Final.ㄱ),
            TwoLetter(Initial.ㅅ, Medial.ㅏ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(
            B, U, L, G, U, K, S, A
          )

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      "묵호 => mukho (ㄱ+ㅎ => kh/k)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅁ, Medial.ㅜ, Final.ㄱ),
            TwoLetter(Initial.ㅎ, Medial.ㅗ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(
            M, U, K, H, O
          )

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㄴ in final position" in {

      "성산읍 => seongsaneup (ㄴ+ㅇ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅅ, Medial.ㅓ, Final.ㅇ),
            ThreeLetter(Initial.ㅅ, Medial.ㅏ, Final.ㄴ),
            ThreeLetter(Initial.ㅇ, Medial.ㅡ, Final.ㅂ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(
            S, E, O, N, G, S, A, N, E, U, P
          )

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄴ+ㄱ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅅ, Medial.ㅐ, Final.ㄴ),
            TwoLetter(Initial.ㄱ, Medial.ㅢ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(S, A, E, N, G, U, I)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      "안녕하세요 => annyeonghaseyo (ㄴ+ㄴ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅇ, Medial.ㅏ, Final.ㄴ),
            ThreeLetter(Initial.ㄴ, Medial.ㅕ, Final.ㅇ),
            TwoLetter(Initial.ㅎ, Medial.ㅏ),
            TwoLetter(Initial.ㅅ, Medial.ㅔ),
            TwoLetter(Initial.ㅇ, Medial.ㅛ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(
            A, N, N, Y, E, O, N, G, H, A, S, E, Y, O
          )

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄴ+ㄷ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅅ, Medial.ㅐ, Final.ㄴ),
            TwoLetter(Initial.ㄷ, Medial.ㅢ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(S, A, E, N, D, U, I)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄴ+ㄹ => ll/nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅅ, Medial.ㅐ, Final.ㄴ),
            TwoLetter(Initial.ㄹ, Medial.ㅢ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(S, A, E, L, L, U, I)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄴ+ㅁ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅅ, Medial.ㅐ, Final.ㄴ),
            TwoLetter(Initial.ㅁ, Medial.ㅢ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(S, A, E, N, M, U, I)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄴ+ㅎ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅅ, Medial.ㅐ, Final.ㄴ),
            TwoLetter(Initial.ㅎ, Medial.ㅢ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(S, A, E, N, H, U, I)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㄷ in final position" in {

      // TODO find a better example
      "(ㄷ+ㅇ => d/j)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅈ, Medial.ㅑ, Final.ㄷ),
            TwoLetter(Initial.ㅇ, Medial.ㅡ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(J, Y, A, D, E, U)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄷ+ㄱ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅈ, Medial.ㅑ, Final.ㄷ),
            TwoLetter(Initial.ㄱ, Medial.ㅡ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(J, Y, A, T, G, E, U)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄷ+ㄴ => nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅈ, Medial.ㅑ, Final.ㄷ),
            TwoLetter(Initial.ㄴ, Medial.ㅡ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(J, Y, A, N, N, E, U)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄷ+ㄷ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅈ, Medial.ㅑ, Final.ㄷ),
            TwoLetter(Initial.ㄷ, Medial.ㅡ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(J, Y, A, T, D, E, U)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄷ+ㄹ => nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅈ, Medial.ㅑ, Final.ㄷ),
            TwoLetter(Initial.ㄹ, Medial.ㅡ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(J, Y, A, N, N, E, U)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄷ+ㅁ => nm)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅈ, Medial.ㅑ, Final.ㄷ),
            TwoLetter(Initial.ㅁ, Medial.ㅡ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(J, Y, A, N, M, E, U)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄷ+ㅎ => th/t/ch)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅈ, Medial.ㅑ, Final.ㄷ),
            TwoLetter(Initial.ㅎ, Medial.ㅡ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(J, Y, A, T, H, E, U)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㄹ in final position" in {

      "설악 => seorak (ㄹ+ㅇ => r)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅅ, Medial.ㅓ, Final.ㄹ),
            ThreeLetter(Initial.ㅇ, Medial.ㅏ, Final.ㄱ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(
            S, E, O, R, A, K
          )

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      "딸기 => ttalgi (ㄹ+ㄱ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㄸ, Medial.ㅏ, Final.ㄹ),
            TwoLetter(Initial.ㄱ, Medial.ㅣ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(T, T, A, L, G, I)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      "칠곡 => chilgok (ㄹ+ㄱ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅊ, Medial.ㅣ, Final.ㄹ),
            ThreeLetter(Initial.ㄱ, Medial.ㅗ, Final.ㄱ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(
            C, H, I, L, G, O, K
          )

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      "일년 => illyeon (ㄹ+ㄴ => ll/nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅇ, Medial.ㅣ, Final.ㄹ),
            ThreeLetter(Initial.ㄴ, Medial.ㅕ, Final.ㄴ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(
            I, L, L, Y, E, O, N
          )

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄹ+ㄷ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅊ, Medial.ㅒ, Final.ㄹ),
            TwoLetter(Initial.ㄷ, Medial.ㅠ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(C, H, Y, A, E, L, D, Y, U)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      "울릉 => ulleung (ㄹ+ㄹ => ll)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅇ, Medial.ㅜ, Final.ㄹ),
            ThreeLetter(Initial.ㄹ, Medial.ㅡ, Final.ㅇ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(
            U, L, L, E, U, N, G
          )

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄹ+ㅁ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅊ, Medial.ㅒ, Final.ㄹ),
            TwoLetter(Initial.ㅁ, Medial.ㅠ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(C, H, Y, A, E, L, M, Y, U)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      "울산 => ulsan (ㄹ+ㅅ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅇ, Medial.ㅜ, Final.ㄹ),
            ThreeLetter(Initial.ㅅ, Medial.ㅏ, Final.ㄴ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(
            U, L, S, A, N
          )

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㄹ+ㅎ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅊ, Medial.ㅒ, Final.ㄹ),
            TwoLetter(Initial.ㅎ, Medial.ㅠ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(C, H, Y, A, E, L, H, Y, U)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㅁ in final position" in {

      // TODO find a better example
      "(ㅁ+ㅇ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅋ, Medial.ㅓ, Final.ㅁ),
            TwoLetter(Initial.ㅇ, Medial.ㅟ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, E, O, M, W, I)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅁ+ㄱ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅋ, Medial.ㅓ, Final.ㅁ),
            TwoLetter(Initial.ㄱ, Medial.ㅟ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, E, O, M, G, W, I)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅁ+ㄴ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅋ, Medial.ㅓ, Final.ㅁ),
            TwoLetter(Initial.ㄴ, Medial.ㅟ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, E, O, M, N, W, I)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅁ+ㄷ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅋ, Medial.ㅓ, Final.ㅁ),
            TwoLetter(Initial.ㄷ, Medial.ㅟ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, E, O, M, D, W, I)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅁ+ㄹ => mn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅋ, Medial.ㅓ, Final.ㅁ),
            TwoLetter(Initial.ㄹ, Medial.ㅟ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, E, O, M, N, W, I)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅁ+ㅁ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅋ, Medial.ㅓ, Final.ㅁ),
            TwoLetter(Initial.ㅁ, Medial.ㅟ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, E, O, M, M, W, I)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅁ+ㅎ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅋ, Medial.ㅓ, Final.ㅁ),
            TwoLetter(Initial.ㅎ, Medial.ㅟ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, E, O, M, H, W, I)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㅂ in final position" in {

      // TODO find a better example
      "(ㅂ+ㅇ => b)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅌ, Medial.ㅔ, Final.ㅂ),
            TwoLetter(Initial.ㅇ, Medial.ㅞ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(T, E, B, W, E)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅂ+ㄱ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅌ, Medial.ㅔ, Final.ㅂ),
            TwoLetter(Initial.ㄱ, Medial.ㅞ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(T, E, P, G, W, E)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      "감사합니다 => gamsahamnida (ㅂ+ㄴ => mn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㄱ, Medial.ㅏ, Final.ㅁ),
            TwoLetter(Initial.ㅅ, Medial.ㅏ),
            ThreeLetter(Initial.ㅎ, Medial.ㅏ, Final.ㅂ),
            TwoLetter(Initial.ㄴ, Medial.ㅣ),
            TwoLetter(Initial.ㄷ, Medial.ㅏ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(
            G, A, M, S, A, H, A, M, N, I, D, A
          )

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅂ+ㄷ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅌ, Medial.ㅔ, Final.ㅂ),
            TwoLetter(Initial.ㄷ, Medial.ㅞ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(T, E, P, D, W, E)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅂ+ㄹ => mn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅌ, Medial.ㅔ, Final.ㅂ),
            TwoLetter(Initial.ㄹ, Medial.ㅞ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(T, E, M, N, W, E)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅂ+ㅁ => mm)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅌ, Medial.ㅔ, Final.ㅂ),
            TwoLetter(Initial.ㅁ, Medial.ㅞ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(T, E, M, M, W, E)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      "잡혀 => japyeo (ㅂ+ㅎ => ph/p)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅈ, Medial.ㅏ, Final.ㅂ),
            TwoLetter(Initial.ㅎ, Medial.ㅕ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(J, A, P, Y, E, O)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }.pendingUntilFixed

    }

    "romanize words with ㅅ in final position" in {

      // TODO find a better example
      "(ㅅ+ㅇ => s)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅍ, Medial.ㅕ, Final.ㅅ),
            TwoLetter(Initial.ㅇ, Medial.ㅝ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(P, Y, E, O, S, W, O)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅅ+ㄱ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅍ, Medial.ㅕ, Final.ㅅ),
            TwoLetter(Initial.ㄱ, Medial.ㅝ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(P, Y, E, O, T, G, W, O)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅅ+ㄴ => nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅍ, Medial.ㅕ, Final.ㅅ),
            TwoLetter(Initial.ㄴ, Medial.ㅝ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(P, Y, E, O, N, N, W, O)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅅ+ㄷ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅍ, Medial.ㅕ, Final.ㅅ),
            TwoLetter(Initial.ㄷ, Medial.ㅝ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(P, Y, E, O, T, D, W, O)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅅ+ㄹ => nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅍ, Medial.ㅕ, Final.ㅅ),
            TwoLetter(Initial.ㄹ, Medial.ㅝ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(P, Y, E, O, N, N, W, O)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅅ+ㅁ => nm)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅍ, Medial.ㅕ, Final.ㅅ),
            TwoLetter(Initial.ㅁ, Medial.ㅝ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(P, Y, E, O, N, M, W, O)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅅ+ㅎ => th/t/ch)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅍ, Medial.ㅕ, Final.ㅅ),
            TwoLetter(Initial.ㅎ, Medial.ㅝ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(P, Y, E, O, T, H, W, O)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㅇ in final position" in {

      // TODO find a better example
      "(ㅇ+ㅇ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅎ, Medial.ㅖ, Final.ㅇ),
            TwoLetter(Initial.ㅇ, Medial.ㅜ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(H, Y, E, N, G, U)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅇ+ㄱ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅎ, Medial.ㅖ, Final.ㅇ),
            TwoLetter(Initial.ㄱ, Medial.ㅜ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(H, Y, E, N, G, G, U)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅇ+ㄴ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅎ, Medial.ㅖ, Final.ㅇ),
            TwoLetter(Initial.ㄴ, Medial.ㅜ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(H, Y, E, N, G, N, U)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅇ+ㄷ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅎ, Medial.ㅖ, Final.ㅇ),
            TwoLetter(Initial.ㄷ, Medial.ㅜ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(H, Y, E, N, G, D, U)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      "종로구 => jongnogu (ㅇ+ㄹ => ngn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅈ, Medial.ㅗ, Final.ㅇ),
            TwoLetter(Initial.ㄹ, Medial.ㅗ),
            TwoLetter(Initial.ㄱ, Medial.ㅜ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(
            J, O, N, G, N, O, G, U
          )

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅇ+ㅁ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅎ, Medial.ㅖ, Final.ㅇ),
            TwoLetter(Initial.ㅁ, Medial.ㅜ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(H, Y, E, N, G, M, U)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅇ+ㅎ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅎ, Medial.ㅖ, Final.ㅇ),
            TwoLetter(Initial.ㅎ, Medial.ㅜ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(H, Y, E, N, G, H, U)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㅈ in final position" in {

      // TODO find a better example
      "(ㅈ+ㅇ => j)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㄲ, Medial.ㅗ, Final.ㅈ),
            TwoLetter(Initial.ㅇ, Medial.ㅛ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, K, O, J, Y, O)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅈ+ㄱ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㄲ, Medial.ㅗ, Final.ㅈ),
            TwoLetter(Initial.ㄱ, Medial.ㅛ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, K, O, T, G, Y, O)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅈ+ㄴ => nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㄲ, Medial.ㅗ, Final.ㅈ),
            TwoLetter(Initial.ㄴ, Medial.ㅛ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, K, O, N, N, Y, O)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅈ+ㄷ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㄲ, Medial.ㅗ, Final.ㅈ),
            TwoLetter(Initial.ㄷ, Medial.ㅛ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, K, O, T, D, Y, O)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅈ+ㄹ => nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㄲ, Medial.ㅗ, Final.ㅈ),
            TwoLetter(Initial.ㄹ, Medial.ㅛ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, K, O, N, N, Y, O)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅈ+ㅁ => nm)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㄲ, Medial.ㅗ, Final.ㅈ),
            TwoLetter(Initial.ㅁ, Medial.ㅛ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, K, O, N, M, Y, O)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅈ+ㅎ => th/t/ch)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㄲ, Medial.ㅗ, Final.ㅈ),
            TwoLetter(Initial.ㅎ, Medial.ㅛ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(K, K, O, T, H, Y, O)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㅊ in final position" in {

      // TODO find a better example
      "(ㅊ+ㅇ => ch)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㄸ, Medial.ㅘ, Final.ㅊ),
            TwoLetter(Initial.ㅇ, Medial.ㅚ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(T, T, W, A, C, H, O, E)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅊ+ㄱ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㄸ, Medial.ㅘ, Final.ㅊ),
            TwoLetter(Initial.ㄱ, Medial.ㅚ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(T, T, W, A, T, G, O, E)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅊ+ㄴ => nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㄸ, Medial.ㅘ, Final.ㅊ),
            TwoLetter(Initial.ㄴ, Medial.ㅚ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(T, T, W, A, N, N, O, E)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅊ+ㄷ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㄸ, Medial.ㅘ, Final.ㅊ),
            TwoLetter(Initial.ㄷ, Medial.ㅚ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(T, T, W, A, T, D, O, E)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅊ+ㄹ => nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㄸ, Medial.ㅘ, Final.ㅊ),
            TwoLetter(Initial.ㄹ, Medial.ㅚ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(T, T, W, A, N, N, O, E)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅊ+ㅁ => nm)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㄸ, Medial.ㅘ, Final.ㅊ),
            TwoLetter(Initial.ㅁ, Medial.ㅚ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(T, T, W, A, N, M, O, E)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅊ+ㅎ => th/t/ch)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㄸ, Medial.ㅘ, Final.ㅊ),
            TwoLetter(Initial.ㅎ, Medial.ㅚ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(T, T, W, A, T, H, O, E)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㅌ in final position" in {

      // TODO find a better example
      "(ㅌ+ㅇ => t/ch)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅃ, Medial.ㅙ, Final.ㅌ),
            TwoLetter(Initial.ㅇ, Medial.ㅙ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(P, P, W, A, E, T, W, A, E)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅌ+ㄱ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅃ, Medial.ㅙ, Final.ㅌ),
            TwoLetter(Initial.ㄱ, Medial.ㅙ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(P, P, W, A, E, T, G, W, A, E)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅌ+ㄴ => nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅃ, Medial.ㅙ, Final.ㅌ),
            TwoLetter(Initial.ㄴ, Medial.ㅙ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(P, P, W, A, E, N, N, W, A, E)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅌ+ㄷ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅃ, Medial.ㅙ, Final.ㅌ),
            TwoLetter(Initial.ㄷ, Medial.ㅙ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(P, P, W, A, E, T, D, W, A, E)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅌ+ㄹ => nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅃ, Medial.ㅙ, Final.ㅌ),
            TwoLetter(Initial.ㄹ, Medial.ㅙ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(P, P, W, A, E, N, N, W, A, E)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅌ+ㅁ => nm)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅃ, Medial.ㅙ, Final.ㅌ),
            TwoLetter(Initial.ㅁ, Medial.ㅙ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(P, P, W, A, E, N, M, W, A, E)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅌ+ㅎ => th/t/ch)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅃ, Medial.ㅙ, Final.ㅌ),
            TwoLetter(Initial.ㅎ, Medial.ㅙ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(P, P, W, A, E, T, H, W, A, E)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with ㅎ in final position" in {

      // TODO find better example
      "(ㅎ+ㅇ)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅆ, Medial.ㅚ, Final.ㅎ),
            TwoLetter(Initial.ㅇ, Medial.ㅘ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(S, S, O, E, H, W, A)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      "좋고 => joko (ㅎ+ㄱ => k)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅈ, Medial.ㅗ, Final.ㅎ),
            TwoLetter(Initial.ㄱ, Medial.ㅗ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(J, O, K, O)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅎ+ㄴ => nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅆ, Medial.ㅚ, Final.ㅎ),
            TwoLetter(Initial.ㄴ, Medial.ㅘ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(S, S, O, E, N, N, W, A)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      "놓다 => nota (ㅎ+ㄷ => t)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㄴ, Medial.ㅗ, Final.ㅎ),
            TwoLetter(Initial.ㄷ, Medial.ㅏ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(N, O, T, A)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅎ+ㄹ => nn)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅆ, Medial.ㅚ, Final.ㅎ),
            TwoLetter(Initial.ㄴ, Medial.ㅘ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(S, S, O, E, N, N, W, A)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      // TODO find a better example
      "(ㅎ+ㅁ => nm)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅆ, Medial.ㅚ, Final.ㅎ),
            TwoLetter(Initial.ㅁ, Medial.ㅘ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(S, S, O, E, N, M, W, A)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      "낳지 => nachi (ㅎ+ㅈ => ch)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㄴ, Medial.ㅏ, Final.ㅎ),
            TwoLetter(Initial.ㅈ, Medial.ㅣ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(N, A, C, H, I)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }.pendingUntilFixed

      // TODO find a better example
      "(ㅎ+ㅎ => t)" in {
        val input: HangeulTextElement =
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅆ, Medial.ㅚ, Final.ㅎ),
            TwoLetter(Initial.ㅎ, Medial.ㅘ)
          )

        val expectedOutput: RomanizedTextElement =
          RomanizedTextElement.Captured.fromLetters(S, S, O, E, T, W, A)

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

    }

  }

}
