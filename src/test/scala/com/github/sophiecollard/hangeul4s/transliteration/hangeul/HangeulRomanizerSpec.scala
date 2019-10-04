package com.github.sophiecollard.hangeul4s.transliteration.hangeul

import cats.instances.either._
import cats.instances.vector._
import cats.syntax.traverse._
import com.github.sophiecollard.hangeul4s.model.hangeul.HangeulJamo._
import com.github.sophiecollard.hangeul4s.model.hangeul.HangeulSyllabicBlock._
import com.github.sophiecollard.hangeul4s.model.hangeul.HangeulTextElement
import com.github.sophiecollard.hangeul4s.model.romanization.RomanLetter._
import com.github.sophiecollard.hangeul4s.model.romanization.RomanizedTextElement
import org.specs2.mutable.Specification

class HangeulRomanizerSpec extends Specification {

  "HangeulRomanizer" should {

    "romanize words made of regular consonant clusters only, such as" in {

      "'안녕하세요' to 'annyeonghaseyo'" in {
        val input = Vector[HangeulTextElement](
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅇ, Medial.ㅏ, Final.ㄴ),
            ThreeLetter(Initial.ㄴ, Medial.ㅕ, Final.ㅇ),
            TwoLetter(Initial.ㅎ, Medial.ㅏ),
            TwoLetter(Initial.ㅅ, Medial.ㅔ),
            TwoLetter(Initial.ㅇ, Medial.ㅛ)
          )
        )

        val expectedOutput = Vector[RomanizedTextElement](
          RomanizedTextElement.Captured.fromLetters(
            A, N, N, Y, E, O, N, G, H, A, S, E, Y, O
          )
        )

        input.map(HangeulRomanizer.transliterate).sequence must beRight(expectedOutput)
      }

      "'불국사' to 'bulguksa'" in {
        val input = Vector[HangeulTextElement](
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅂ, Medial.ㅜ, Final.ㄹ),
            ThreeLetter(Initial.ㄱ, Medial.ㅜ, Final.ㄱ),
            TwoLetter(Initial.ㅅ, Medial.ㅏ)
          )
        )

        val expectedOutput = Vector[RomanizedTextElement](
          RomanizedTextElement.Captured.fromLetters(
            B, U, L, G, U, K, S, A
          )
        )

        input.map(HangeulRomanizer.transliterate).sequence must beRight(expectedOutput)
      }

      "'묵호' to 'mukho'" in {
        val input = Vector[HangeulTextElement](
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅁ, Medial.ㅜ, Final.ㄱ),
            TwoLetter(Initial.ㅎ, Medial.ㅗ)
          )
        )

        val expectedOutput = Vector[RomanizedTextElement](
          RomanizedTextElement.Captured.fromLetters(
            M, U, K, H, O
          )
        )

        input.map(HangeulRomanizer.transliterate).sequence must beRight(expectedOutput)
      }

      "'울산' to 'ulsan'" in {
        val input = Vector[HangeulTextElement](
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅇ, Medial.ㅜ, Final.ㄹ),
            ThreeLetter(Initial.ㅅ, Medial.ㅏ, Final.ㄴ)
          )
        )

        val expectedOutput = Vector[RomanizedTextElement](
          RomanizedTextElement.Captured.fromLetters(
            U, L, S, A, N
          )
        )

        input.map(HangeulRomanizer.transliterate).sequence must beRight(expectedOutput)
      }

    }

    "romanize words with irregular consonant clusters, such as" in {

      "'성산읍' to 'seongsaneup'" in {
        val input = Vector[HangeulTextElement](
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅅ, Medial.ㅓ, Final.ㅇ),
            ThreeLetter(Initial.ㅅ, Medial.ㅏ, Final.ㄴ),
            ThreeLetter(Initial.ㅇ, Medial.ㅡ, Final.ㅂ)
          )
        )

        val expectedOutput = Vector[RomanizedTextElement](
          RomanizedTextElement.Captured.fromLetters(
            S, E, O, N, G, S, A, N, E, U, P
          )
        )

        input.map(HangeulRomanizer.transliterate).sequence must beRight(expectedOutput)
      }

      "'설악' to 'seorak'" in {
        val input = Vector[HangeulTextElement](
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅅ, Medial.ㅓ, Final.ㄹ),
            ThreeLetter(Initial.ㅇ, Medial.ㅏ, Final.ㄱ)
          )
        )

        val expectedOutput = Vector[RomanizedTextElement](
          RomanizedTextElement.Captured.fromLetters(
              S, E, O, R, A, K
          )
        )

        input.map(HangeulRomanizer.transliterate).sequence must beRight(expectedOutput)
      }

      "'감사합니다' to 'gamsahamnida'" in {
        val input = Vector[HangeulTextElement](
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㄱ, Medial.ㅏ, Final.ㅁ),
            TwoLetter(Initial.ㅅ, Medial.ㅏ),
            ThreeLetter(Initial.ㅎ, Medial.ㅏ, Final.ㅂ),
            TwoLetter(Initial.ㄴ, Medial.ㅣ),
            TwoLetter(Initial.ㄷ, Medial.ㅏ)
          )
        )

        val expectedOutput = Vector[RomanizedTextElement](
          RomanizedTextElement.Captured.fromLetters(
            G, A, M, S, A, H, A, M, N, I, D, A
          )
        )

        input.map(HangeulRomanizer.transliterate).sequence must beRight(expectedOutput)
      }

      "'칠곡' to 'chilgok'" in {
        val input = Vector[HangeulTextElement](
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅊ, Medial.ㅣ, Final.ㄹ),
            ThreeLetter(Initial.ㄱ, Medial.ㅗ, Final.ㄱ)
          )
        )

        val expectedOutput = Vector[RomanizedTextElement](
          RomanizedTextElement.Captured.fromLetters(
            C, H, I, L, G, O, K
          )
        )

        input.map(HangeulRomanizer.transliterate).sequence must beRight(expectedOutput)
      }

      "'울릉' to 'ulleung'" in {
        val input = Vector[HangeulTextElement](
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅇ, Medial.ㅜ, Final.ㄹ),
            ThreeLetter(Initial.ㄹ, Medial.ㅡ, Final.ㅇ)
          )
        )

        val expectedOutput = Vector[RomanizedTextElement](
          RomanizedTextElement.Captured.fromLetters(
            U, L, L, E, U, N, G
          )
        )

        input.map(HangeulRomanizer.transliterate).sequence must beRight(expectedOutput)
      }

      "'종로구' to 'jongnogu'" in {
        val input = Vector[HangeulTextElement](
          HangeulTextElement.Captured.fromSyllabicBlocks(
            ThreeLetter(Initial.ㅈ, Medial.ㅗ, Final.ㅇ),
            TwoLetter(Initial.ㄹ, Medial.ㅗ),
            TwoLetter(Initial.ㄱ, Medial.ㅜ)
          )
        )

        val expectedOutput = Vector[RomanizedTextElement](
          RomanizedTextElement.Captured.fromLetters(
            J, O, N, G, N, O, G, U
          )
        )

        input.map(HangeulRomanizer.transliterate).sequence must beRight(expectedOutput)
      }

    }

  }

}
