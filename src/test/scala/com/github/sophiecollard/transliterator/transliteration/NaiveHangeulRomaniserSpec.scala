package com.github.sophiecollard.transliterator.transliteration

import com.github.sophiecollard.transliterator.charset.{HangeulLetter, HangeulSyllabicBlock, RomanLetter}
import com.github.sophiecollard.transliterator.text.{HangeulText, HangeulWord, RomanizedText, RomanizedWord}
import org.specs2.mutable.Specification

class NaiveHangeulRomaniserSpec extends Specification {

  "NaiveHangeulRomaniser" should {

    "romanise words made of two-letter blocks, such as" in {

      "'제주도' to 'jejudo'" in {
        val input = HangeulText(
          Vector(
            HangeulWord(
              Vector(
                HangeulSyllabicBlock.TwoLetter(
                  HangeulLetter.ㅈ,
                  HangeulLetter.ㅔ
                ),
                HangeulSyllabicBlock.TwoLetter(
                  HangeulLetter.ㅈ,
                  HangeulLetter.ㅜ
                ),
                HangeulSyllabicBlock.TwoLetter(
                  HangeulLetter.ㄷ,
                  HangeulLetter.ㅗ
                )
              )
            )
          )
        )

        val expectedOutput = RomanizedText(
          Vector(
            RomanizedWord(
              Vector(
                RomanLetter.J,
                RomanLetter.E,
                RomanLetter.J,
                RomanLetter.U,
                RomanLetter.D,
                RomanLetter.O
              )
            )
          )
        )

        NaiveHangeulRomaniser.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanise words in which consonant position within a single block is significant, such as" in {

      "'불국사' to 'bulguksa'" in {
        val input = HangeulText(
          Vector(
            HangeulWord(
              Vector(
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulLetter.ㅂ,
                  HangeulLetter.ㅜ,
                  HangeulLetter.ㄹ
                ),
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulLetter.ㄱ,
                  HangeulLetter.ㅜ,
                  HangeulLetter.ㄱ
                ),
                HangeulSyllabicBlock.TwoLetter(
                  HangeulLetter.ㅅ,
                  HangeulLetter.ㅏ
                )
              )
            )
          )
        )

        val expectedOutput = RomanizedText(
          Vector(
            RomanizedWord(
              Vector(
                RomanLetter.B,
                RomanLetter.U,
                RomanLetter.L,
                RomanLetter.G,
                RomanLetter.U,
                RomanLetter.K,
                RomanLetter.S,
                RomanLetter.A
              )
            )
          )
        )

        NaiveHangeulRomaniser.transliterate(input) must beRight(expectedOutput)
      }

      "'묵호' to 'mukho'" in {
        val input = HangeulText(
          Vector(
            HangeulWord(
              Vector(
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulLetter.ㅁ,
                  HangeulLetter.ㅜ,
                  HangeulLetter.ㄱ
                ),
                HangeulSyllabicBlock.TwoLetter(
                  HangeulLetter.ㅎ,
                  HangeulLetter.ㅗ
                )
              )
            )
          )
        )

        val expectedOutput = RomanizedText(
          Vector(
            RomanizedWord(
              Vector(
                RomanLetter.M,
                RomanLetter.U,
                RomanLetter.K,
                RomanLetter.H,
                RomanLetter.O
              )
            )
          )
        )

        NaiveHangeulRomaniser.transliterate(input) must beRight(expectedOutput)
      }

      "'울산' to 'ulsan'" in {
        val input = HangeulText(
          Vector(
            HangeulWord(
              Vector(
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulLetter.ㅇ,
                  HangeulLetter.ㅜ,
                  HangeulLetter.ㄹ
                ),
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulLetter.ㅅ,
                  HangeulLetter.ㅏ,
                  HangeulLetter.ㄴ
                )
              )
            )
          )
        )

        val expectedOutput = RomanizedText(
          Vector(
            RomanizedWord(
              Vector(
                RomanLetter.U,
                RomanLetter.L,
                RomanLetter.S,
                RomanLetter.A,
                RomanLetter.N
              )
            )
          )
        )

        NaiveHangeulRomaniser.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanise words in which consonant position with respect to adjacent blocks is significant, such as" in {

      "'설악' to 'seorak'" in pending {
        val input = HangeulText(
          Vector(
            HangeulWord(
              Vector(
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulLetter.ㅅ,
                  HangeulLetter.ㅓ,
                  HangeulLetter.ㄹ
                ),
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulLetter.ㅇ,
                  HangeulLetter.ㅏ,
                  HangeulLetter.ㄱ
                )
              )
            )
          )
        )

        val expectedOutput = RomanizedText(
          Vector(
            RomanizedWord(
              Vector(
                RomanLetter.S,
                RomanLetter.E,
                RomanLetter.O,
                RomanLetter.R,
                RomanLetter.A,
                RomanLetter.K
              )
            )
          )
        )

        NaiveHangeulRomaniser.transliterate(input) must beRight(expectedOutput)
      }

      "'칠곡' to 'chilgok'" in pending {
        val input = HangeulText(
          Vector(
            HangeulWord(
              Vector(
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulLetter.ㅈ,
                  HangeulLetter.ㅣ,
                  HangeulLetter.ㄹ
                ),
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulLetter.ㄱ,
                  HangeulLetter.ㅗ,
                  HangeulLetter.ㄱ
                )
              )
            )
          )
        )

        val expectedOutput = RomanizedText(
          Vector(
            RomanizedWord(
              Vector(
                RomanLetter.C,
                RomanLetter.H,
                RomanLetter.I,
                RomanLetter.L,
                RomanLetter.G,
                RomanLetter.O,
                RomanLetter.K
              )
            )
          )
        )

        NaiveHangeulRomaniser.transliterate(input) must beRight(expectedOutput)
      }

      "'울릉' to 'ulleung'" in pending {
        val input = HangeulText(
          Vector(
            HangeulWord(
              Vector(
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulLetter.ㅇ,
                  HangeulLetter.ㅜ,
                  HangeulLetter.ㄹ
                ),
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulLetter.ㄹ,
                  HangeulLetter.ㅡ,
                  HangeulLetter.ㅇ
                )
              )
            )
          )
        )

        val expectedOutput = RomanizedText(
          Vector(
            RomanizedWord(
              Vector(
                RomanLetter.U,
                RomanLetter.L,
                RomanLetter.L,
                RomanLetter.E,
                RomanLetter.U,
                RomanLetter.N,
                RomanLetter.G
              )
            )
          )
        )

        NaiveHangeulRomaniser.transliterate(input) must beRight(expectedOutput)
      }

    }

  }

}
