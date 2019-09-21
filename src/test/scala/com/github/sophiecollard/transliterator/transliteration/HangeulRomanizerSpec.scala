package com.github.sophiecollard.transliterator.transliteration

import com.github.sophiecollard.transliterator.model.hangeul._
import com.github.sophiecollard.transliterator.model.romanization.{RomanLetter, RomanizedText, RomanizedWord}
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
                  HangeulJamo.Initial.ㅇ,
                  HangeulJamo.Medial.ㅏ,
                  HangeulJamo.Final.ㄴ
                ),
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulJamo.Initial.ㄴ,
                  HangeulJamo.Medial.ㅕ,
                  HangeulJamo.Final.ㅇ
                ),
                HangeulSyllabicBlock.TwoLetter(
                  HangeulJamo.Initial.ㅎ,
                  HangeulJamo.Medial.ㅏ
                ),
                HangeulSyllabicBlock.TwoLetter(
                  HangeulJamo.Initial.ㅅ,
                  HangeulJamo.Medial.ㅔ
                ),
                HangeulSyllabicBlock.TwoLetter(
                  HangeulJamo.Initial.ㅇ,
                  HangeulJamo.Medial.ㅛ
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
                  HangeulJamo.Initial.ㅂ,
                  HangeulJamo.Medial.ㅜ,
                  HangeulJamo.Final.ㄹ
                ),
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulJamo.Initial.ㄱ,
                  HangeulJamo.Medial.ㅜ,
                  HangeulJamo.Final.ㄱ
                ),
                HangeulSyllabicBlock.TwoLetter(
                  HangeulJamo.Initial.ㅅ,
                  HangeulJamo.Medial.ㅏ
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
                  HangeulJamo.Initial.ㅁ,
                  HangeulJamo.Medial.ㅜ,
                  HangeulJamo.Final.ㄱ
                ),
                HangeulSyllabicBlock.TwoLetter(
                  HangeulJamo.Initial.ㅎ,
                  HangeulJamo.Medial.ㅗ
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
                  HangeulJamo.Initial.ㅇ,
                  HangeulJamo.Medial.ㅜ,
                  HangeulJamo.Final.ㄹ
                ),
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulJamo.Initial.ㅅ,
                  HangeulJamo.Medial.ㅏ,
                  HangeulJamo.Final.ㄴ
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
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulJamo.Initial.ㅅ,
                  HangeulJamo.Medial.ㅓ,
                  HangeulJamo.Final.ㅇ
                ),
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulJamo.Initial.ㅅ,
                  HangeulJamo.Medial.ㅏ,
                  HangeulJamo.Final.ㄴ
                ),
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulJamo.Initial.ㅇ,
                  HangeulJamo.Medial.ㅡ,
                  HangeulJamo.Final.ㅂ
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
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulJamo.Initial.ㅅ,
                  HangeulJamo.Medial.ㅓ,
                  HangeulJamo.Final.ㄹ
                ),
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulJamo.Initial.ㅇ,
                  HangeulJamo.Medial.ㅏ,
                  HangeulJamo.Final.ㄱ
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
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulJamo.Initial.ㄱ,
                  HangeulJamo.Medial.ㅏ,
                  HangeulJamo.Final.ㅁ
                ),
                HangeulSyllabicBlock.TwoLetter(
                  HangeulJamo.Initial.ㅅ,
                  HangeulJamo.Medial.ㅏ
                ),
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulJamo.Initial.ㅎ,
                  HangeulJamo.Medial.ㅏ,
                  HangeulJamo.Final.ㅂ
                ),
                HangeulSyllabicBlock.TwoLetter(
                  HangeulJamo.Initial.ㄴ,
                  HangeulJamo.Medial.ㅣ
                ),
                HangeulSyllabicBlock.TwoLetter(
                  HangeulJamo.Initial.ㄷ,
                  HangeulJamo.Medial.ㅏ
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
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulJamo.Initial.ㅊ,
                  HangeulJamo.Medial.ㅣ,
                  HangeulJamo.Final.ㄹ
                ),
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulJamo.Initial.ㄱ,
                  HangeulJamo.Medial.ㅗ,
                  HangeulJamo.Final.ㄱ
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
                  HangeulJamo.Initial.ㅇ,
                  HangeulJamo.Medial.ㅜ,
                  HangeulJamo.Final.ㄹ
                ),
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulJamo.Initial.ㄹ,
                  HangeulJamo.Medial.ㅡ,
                  HangeulJamo.Final.ㅇ
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
                HangeulSyllabicBlock.ThreeLetter(
                  HangeulJamo.Initial.ㅈ,
                  HangeulJamo.Medial.ㅗ,
                  HangeulJamo.Final.ㅇ
                ),
                HangeulSyllabicBlock.TwoLetter(
                  HangeulJamo.Initial.ㄹ,
                  HangeulJamo.Medial.ㅗ
                ),
                HangeulSyllabicBlock.TwoLetter(
                  HangeulJamo.Initial.ㄱ,
                  HangeulJamo.Medial.ㅜ
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
