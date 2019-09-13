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
                HangeulSyllabicBlock.IMF(
                  HangeulLetter.ㅇ,
                  HangeulLetter.ㅏ,
                  HangeulLetter.ㄴ
                ),
                HangeulSyllabicBlock.IMF(
                  HangeulLetter.ㄴ,
                  HangeulLetter.ㅕ,
                  HangeulLetter.ㅇ
                ),
                HangeulSyllabicBlock.IM(
                  HangeulLetter.ㅎ,
                  HangeulLetter.ㅏ
                ),
                HangeulSyllabicBlock.IM(
                  HangeulLetter.ㅅ,
                  HangeulLetter.ㅔ
                ),
                HangeulSyllabicBlock.IM(
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
                HangeulSyllabicBlock.IMF(
                  HangeulLetter.ㅂ,
                  HangeulLetter.ㅜ,
                  HangeulLetter.ㄹ
                ),
                HangeulSyllabicBlock.IMF(
                  HangeulLetter.ㄱ,
                  HangeulLetter.ㅜ,
                  HangeulLetter.ㄱ
                ),
                HangeulSyllabicBlock.IM(
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
                HangeulSyllabicBlock.IMF(
                  HangeulLetter.ㅁ,
                  HangeulLetter.ㅜ,
                  HangeulLetter.ㄱ
                ),
                HangeulSyllabicBlock.IM(
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
                HangeulSyllabicBlock.IMF(
                  HangeulLetter.ㅇ,
                  HangeulLetter.ㅜ,
                  HangeulLetter.ㄹ
                ),
                HangeulSyllabicBlock.IMF(
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

      "'성산읍' to 'seongsaneup'" in {
        val input = HangeulText(
          Vector(
            HangeulWord(
              Vector(
                HangeulSyllabicBlock.IMF(
                  HangeulLetter.ㅅ,
                  HangeulLetter.ㅓ,
                  HangeulLetter.ㅇ
                ),
                HangeulSyllabicBlock.IMF(
                  HangeulLetter.ㅅ,
                  HangeulLetter.ㅏ,
                  HangeulLetter.ㄴ
                ),
                HangeulSyllabicBlock.IMF(
                  HangeulLetter.ㅇ,
                  HangeulLetter.ㅡ,
                  HangeulLetter.ㅂ
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
                RomanLetter.N,
                RomanLetter.G,
                RomanLetter.S,
                RomanLetter.A,
                RomanLetter.N,
                RomanLetter.E,
                RomanLetter.U,
                RomanLetter.P
              )
            )
          )
        )

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

      "'설악' to 'seorak'" in {
        val input = HangeulText(
          Vector(
            HangeulWord(
              Vector(
                HangeulSyllabicBlock.IMF(
                  HangeulLetter.ㅅ,
                  HangeulLetter.ㅓ,
                  HangeulLetter.ㄹ
                ),
                HangeulSyllabicBlock.IMF(
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

      "'감사합니다' to 'gamsahamnida'" in {
        val input = HangeulText(
          Vector(
            HangeulWord(
              Vector(
                HangeulSyllabicBlock.IMF(
                  HangeulLetter.ㄱ,
                  HangeulLetter.ㅏ,
                  HangeulLetter.ㅁ
                ),
                HangeulSyllabicBlock.IM(
                  HangeulLetter.ㅅ,
                  HangeulLetter.ㅏ
                ),
                HangeulSyllabicBlock.IMF(
                  HangeulLetter.ㅎ,
                  HangeulLetter.ㅏ,
                  HangeulLetter.ㅂ
                ),
                HangeulSyllabicBlock.IM(
                  HangeulLetter.ㄴ,
                  HangeulLetter.ㅣ
                ),
                HangeulSyllabicBlock.IM(
                  HangeulLetter.ㄷ,
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
                RomanLetter.G,
                RomanLetter.A,
                RomanLetter.M,
                RomanLetter.S,
                RomanLetter.A,
                RomanLetter.H,
                RomanLetter.A,
                RomanLetter.M,
                RomanLetter.N,
                RomanLetter.I,
                RomanLetter.D,
                RomanLetter.A
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
                HangeulSyllabicBlock.IMF(
                  HangeulLetter.ㅊ,
                  HangeulLetter.ㅣ,
                  HangeulLetter.ㄹ
                ),
                HangeulSyllabicBlock.IMF(
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
                HangeulSyllabicBlock.IMF(
                  HangeulLetter.ㅇ,
                  HangeulLetter.ㅜ,
                  HangeulLetter.ㄹ
                ),
                HangeulSyllabicBlock.IMF(
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

      "'종로구' to 'jongnogu'" in {
        val input = HangeulText(
          Vector(
            HangeulWord(
              Vector(
                HangeulSyllabicBlock.IMF(
                  HangeulLetter.ㅈ,
                  HangeulLetter.ㅗ,
                  HangeulLetter.ㅇ
                ),
                HangeulSyllabicBlock.IM(
                  HangeulLetter.ㄹ,
                  HangeulLetter.ㅗ
                ),
                HangeulSyllabicBlock.IM(
                  HangeulLetter.ㄱ,
                  HangeulLetter.ㅜ
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
                RomanLetter.O,
                RomanLetter.N,
                RomanLetter.G,
                RomanLetter.N,
                RomanLetter.O,
                RomanLetter.G,
                RomanLetter.U
              )
            )
          )
        )

        HangeulRomanizer.transliterate(input) must beRight(expectedOutput)
      }

    }

  }

}
