package com.github.sophiecollard.hangeul4s.transliteration

import com.github.sophiecollard.hangeul4s.model.hangeul.{HangeulText, HangeulTextElement}
import com.github.sophiecollard.hangeul4s.model.hangeul.HangeulJamo._
import com.github.sophiecollard.hangeul4s.model.hangeul.HangeulSyllabicBlock._
import com.github.sophiecollard.hangeul4s.model.romanization.{RomanizedText, RomanizedTextElement}
import com.github.sophiecollard.hangeul4s.model.romanization.RomanLetter._
import org.specs2.mutable.Specification

class HangeulRomanizerSpec extends Specification {

  "HangeulRomanizer" should {

    "romanize words made of regular consonant clusters only, such as" in {

      "'안녕하세요' to 'annyeonghaseyo'" in {
        val input = HangeulText.fromElements(
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅇ, Medial.ㅏ, Final.ㄴ),
            ThreeLetter(Initial.ㄴ, Medial.ㅕ, Final.ㅇ),
            TwoLetter(Initial.ㅎ, Medial.ㅏ),
            TwoLetter(Initial.ㅅ, Medial.ㅔ),
            TwoLetter(Initial.ㅇ, Medial.ㅛ)
          )
        )

        val expectedOutput = RomanizedText.fromElements(
          RomanizedTextElement.Captured.fromLetters(
            A, N, N, Y, E, O, N, G, H, A, S, E, Y, O
          )
        )

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      "'불국사' to 'bulguksa'" in {
        val input = HangeulText.fromElements(
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅂ, Medial.ㅜ, Final.ㄹ),
            ThreeLetter(Initial.ㄱ, Medial.ㅜ, Final.ㄱ),
            TwoLetter(Initial.ㅅ, Medial.ㅏ)
          )
        )

        val expectedOutput = RomanizedText.fromElements(
          RomanizedTextElement.Captured.fromLetters(
            B, U, L, G, U, K, S, A
          )
        )

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      "'묵호' to 'mukho'" in {
        val input = HangeulText.fromElements(
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅁ, Medial.ㅜ, Final.ㄱ),
            TwoLetter(Initial.ㅎ, Medial.ㅗ)
          )
        )

        val expectedOutput = RomanizedText.fromElements(
          RomanizedTextElement.Captured.fromLetters(
            M, U, K, H, O
          )
        )

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      "'울산' to 'ulsan'" in {
        val input = HangeulText.fromElements(
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅇ, Medial.ㅜ, Final.ㄹ),
            ThreeLetter(Initial.ㅅ, Medial.ㅏ, Final.ㄴ)
          )
        )

        val expectedOutput = RomanizedText.fromElements(
          RomanizedTextElement.Captured.fromLetters(
            U, L, S, A, N
          )
        )

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with irregular consonant clusters, such as" in {

      "'성산읍' to 'seongsaneup'" in {
        val input = HangeulText.fromElements(
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅅ, Medial.ㅓ, Final.ㅇ),
            ThreeLetter(Initial.ㅅ, Medial.ㅏ, Final.ㄴ),
            ThreeLetter(Initial.ㅇ, Medial.ㅡ, Final.ㅂ)
          )
        )

        val expectedOutput = RomanizedText.fromElements(
          RomanizedTextElement.Captured.fromLetters(
            S, E, O, N, G, S, A, N, E, U, P
          )
        )

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      "'설악' to 'seorak'" in {
        val input = HangeulText.fromElements(
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅅ, Medial.ㅓ, Final.ㄹ),
            ThreeLetter(Initial.ㅇ, Medial.ㅏ, Final.ㄱ)
          )
        )

        val expectedOutput = RomanizedText.fromElements(
          RomanizedTextElement.Captured.fromLetters(
              S, E, O, R, A, K
          )
        )

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      "'감사합니다' to 'gamsahamnida'" in {
        val input = HangeulText.fromElements(
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㄱ, Medial.ㅏ, Final.ㅁ),
            TwoLetter(Initial.ㅅ, Medial.ㅏ),
            ThreeLetter(Initial.ㅎ, Medial.ㅏ, Final.ㅂ),
            TwoLetter(Initial.ㄴ, Medial.ㅣ),
            TwoLetter(Initial.ㄷ, Medial.ㅏ)
          )
        )

        val expectedOutput = RomanizedText.fromElements(
          RomanizedTextElement.Captured.fromLetters(
            G, A, M, S, A, H, A, M, N, I, D, A
          )
        )

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      "'칠곡' to 'chilgok'" in {
        val input = HangeulText.fromElements(
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅊ, Medial.ㅣ, Final.ㄹ),
            ThreeLetter(Initial.ㄱ, Medial.ㅗ, Final.ㄱ)
          )
        )

        val expectedOutput = RomanizedText.fromElements(
          RomanizedTextElement.Captured.fromLetters(
            C, H, I, L, G, O, K
          )
        )

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      "'울릉' to 'ulleung'" in {
        val input = HangeulText.fromElements(
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅇ, Medial.ㅜ, Final.ㄹ),
            ThreeLetter(Initial.ㄹ, Medial.ㅡ, Final.ㅇ)
          )
        )

        val expectedOutput = RomanizedText.fromElements(
          RomanizedTextElement.Captured.fromLetters(
            U, L, L, E, U, N, G
          )
        )

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      "'종로구' to 'jongnogu'" in {
        val input = HangeulText.fromElements(
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅈ, Medial.ㅗ, Final.ㅇ),
            TwoLetter(Initial.ㄹ, Medial.ㅗ),
            TwoLetter(Initial.ㄱ, Medial.ㅜ)
          )
        )

        val expectedOutput = RomanizedText.fromElements(
          RomanizedTextElement.Captured.fromLetters(
            J, O, N, G, N, O, G, U
          )
        )

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

    }

  }

}
