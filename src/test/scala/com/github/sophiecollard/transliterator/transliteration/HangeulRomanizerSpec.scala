package com.github.sophiecollard.transliterator.transliteration

import com.github.sophiecollard.transliterator.model._
import org.specs2.mutable.Specification

class HangeulRomanizerSpec extends Specification {

  "HangeulRomanizer" should {

    "romanize words made of regular consonant clusters only, such as" in {

      "'안녕하세요' to 'annyeonghaseyo'" in {
        val input = HangeulText(
          Vector(
            HangeulWord(
              Vector(
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulLetter.ㅇ,
                  HangeulLetter.ㅏ,
                  HangeulLetter.ㄴ
                ),
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulLetter.ㄴ,
                  HangeulLetter.ㅕ,
                  HangeulLetter.ㅇ
                ),
                HangeulSyllabicBlock.TwoLetter(
                  HangeulLetter.ㅎ,
                  HangeulLetter.ㅏ
                ),
                HangeulSyllabicBlock.TwoLetter(
                  HangeulLetter.ㅅ,
                  HangeulLetter.ㅔ
                ),
                HangeulSyllabicBlock.TwoLetter(
                  HangeulLetter.ㅇ,
                  HangeulLetter.ㅛ
                )
              )
            )
          )
        )

        val expectedOutput = RomanizedText(
          Vector(
            RomanizedWord(
              Vector(
                RomanLetter.A,
                RomanLetter.N,
                RomanLetter.N,
                RomanLetter.Y,
                RomanLetter.E,
                RomanLetter.O,
                RomanLetter.N,
                RomanLetter.G,
                RomanLetter.H,
                RomanLetter.A,
                RomanLetter.S,
                RomanLetter.E,
                RomanLetter.Y,
                RomanLetter.O
              )
            )
          )
        )

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

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

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
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

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
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

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

    }

    "romanize words with irregular consonant clusters, such as" in {

      "'설악' to 'seorak'" in {
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

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      "'칠곡' to 'chilgok'" in {
        val input = HangeulText(
          Vector(
            HangeulWord(
              Vector(
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulLetter.ㅊ,
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

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      "'울릉' to 'ulleung'" in {
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

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

    }

  }

}
