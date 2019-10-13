# Hangeul Transliterator 4s

![CircleCI](https://img.shields.io/circleci/build/github/sophiecollard/hangeul-transliterator-4s/master) 
![codecov](https://codecov.io/gh/sophiecollard/hangeul-transliterator-4s/branch/master/graph/badge.svg)
![GitHub](https://img.shields.io/github/license/sophiecollard/hangeul-transliterator-4s)

An attempt at creating a library for transliteration between [Hangeul](https://en.wikipedia.org/wiki/Hangul), the [Latin alphabet](https://en.wikipedia.org/wiki/Latin_alphabet), and possibly more later.

## Status
This project is currently under development.

### Roadmap to first release
  - [x] Implement [romanization](https://en.wikipedia.org/wiki/Revised_Romanization_of_Korean) of [jamos](https://en.wikipedia.org/wiki/Hangul_Jamo_(Unicode_block))
  - [x] Implement [romanization](https://en.wikipedia.org/wiki/Revised_Romanization_of_Korean) of [syllabic blocks](https://en.wikipedia.org/wiki/Hangul_Syllables)
  - [x] Implement conversion between [jamos](https://en.wikipedia.org/wiki/Hangul_Jamo_(Unicode_block)) and [syllabic blocks](https://en.wikipedia.org/wiki/Hangul_Syllables)
  - [x] Implement parsing of `HangeulText`
  - [x] Add CircleCI integration
  - [x] Add Codecov integration
  - [x] Cross-build for Scala 2.11, 2.12 and 2.13
  - [x] Add Apache 2.0 licence
  - [ ] Assess performance of parsing and transliteration

## Examples
Single-word transliteration example:

```scala
import hangeul4s.model.hangeul.HangeulTextElement
import hangeul4s.model.romanization.RomanizedTextElement
import hangeul4s.parsing.syntax._
import hangeul4s.transliteration.hangeul.instances._
import hangeul4s.transliteration.syntax._

val input = "안녕하세요"
// input: String = 안녕하세요

val output = for {
  parsed <- input.parse[HangeulTextElement]
  transliterated <- parsed.transliterate[RomanizedTextElement]
} yield transliterated.unparse[String]
// output: scala.util.Either[hangeul4s.error.Hangeul4sError,String] = Right(annyeonghaseyo)
```

Text transliteration example:

```scala
import cats.instances.vector._
import hangeul4s.model.hangeul.HangeulTextElement
import hangeul4s.model.hangeul.HangeulTextElement._
import hangeul4s.model.romanization.RomanizedTextElement
import hangeul4s.model.romanization.RomanizedTextElement._
import hangeul4s.parsing.implicits._
import hangeul4s.transliteration.hangeul.instances._
import hangeul4s.transliteration.implicits._

// first sentence of second paragraph of the Korean Wikipedia article on Seoul (retrieved 2019-09-22)
// See https://ko.wikipedia.org/wiki/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C
val input = "시청 소재지는 중구이며, 25개의 자치구로 이루어져 있다."
// input: String = 시청 소재지는 중구이며, 25개의 자치구로 이루어져 있다.

val output = for {
  parsed <- input.parseF[Vector, HangeulTextElement]
  transliterated <- parsed.transliterateF[Vector, RomanizedTextElement]
} yield transliterated.unparse[String]
// output: scala.util.Either[hangeul4s.error.Hangeul4sError,String] = Right(sicheong sojaejineun jungguimyeo, 25gaeui jachiguro irueojyeo itda.)
```

## Transliteration rules
This project is an implementation of the [revised Hangeul romanization](https://en.wikipedia.org/wiki/Revised_Romanization_of_Korean). Transliteration rules currently supported are detailed in the tables below.

### Vowels
| Hangul           | ㅏ | ㅐ  | ㅑ | ㅒ  | ㅓ | ㅔ | ㅕ  | ㅖ | ㅗ  | ㅘ | ㅙ | ㅚ   | ㅛ  | ㅜ | ㅝ | ㅞ | ㅟ | ㅠ | ㅡ  | ㅢ | ㅣ |
| :--------------: | -- | -- | -- | --- | -- | -- | --- | -- | -- | -- | --- | --- | -- | -- | -- | -- | -- | -- | -- | -- | -- |
| **Romanization** | a  | ae | ya | yae | eo | e  | yeo | ye | o  | wa | wae | oe  | yo | u  | wo | we | wi | yu | eu | ui | i  |

### Initial consonants
| Hangul           | ㄱ | ㄲ | ㄴ | ㄷ | ㄸ | ㄹ | ㅁ | ㅂ | ㅃ | ㅅ  | ㅆ | ㅇ | ㅈ | ㅉ | ㅊ | ㅋ | ㅌ | ㅍ | ㅎ |
| :--------------: | -- | -- | -- | - | -- | -- | -- | -- | -- | -- | -- | -- | -- | -- | -- | - | -- | -- | -- |
| **Romanization** | g  | kk | n  | d | tt | r  | m  | b  | pp | s  | ss | –  | j  | jj | ch | k | t  | p  | h  |

### Final consonants
| Hangul           | ㄱ | ㄲ | ㄳ  | ㄴ | ㄵ | ㄶ | ㄷ | ㄹ | ㄺ | ㄻ | ㄼ | ㄽ | ㄾ | ㄿ | ㅀ | ㅁ | ㅂ | ㅄ | ㅅ  | ㅆ | ㅇ | ㅈ | ㅊ | ㅋ | ㅌ | ㅍ | ㅎ |
| :--------------: | -- | -- | -- | -- | - | -- | - | -- | - | -- | -- | - | -- | - | -- | -- | -- | - | -- | -- | -- | -- | -- | - | -- | -- | -- |
| **Romanization** | k  | k  | k  | n  | n | n  | t | l  | k | m  | p  | l | l  | p | l  | m  | p  | p | t  | t  | ng | t  | t  | k | t  | p  | t  |

### Special provisions for final / initial consonant pairs
Rows and columns correspond to final and initial consonants, respectively. Final / initial consonants pairs with irregular transliteration are displayed in bold.

| F/I    | ㅇ                    | ㄱ     | ㄴ                     | ㄷ    | ㄹ                      | ㅁ      |
| :----: | --------------------- | ----- | ---------------------- | ----- | ---------------------- | ------- |
| **ㄱ** | **g**                 | kg    | **ngn**                | kd    | **ngn**                | **ngm** |
| **ㄴ** | n                     | ng    | nn                     | nd    | **ll, nn**<sup>2</sup> | nm      |
| **ㄷ** | **d, j**<sup>1</sup>  | tg    | **nn**                 | td    | **nn**                 | **nm**  |
| **ㄹ** | **r**                 | lg    | **ll, nn**<sup>2</sup> | ld    | **ll**                 | lm      |
| **ㅁ** | m                     | mg    | mn                     | md    | **mn**                 | mm      |
| **ㅂ** | **b**                 | pg    | **mn**                 | pd    | **mn**                 | **mm**  |
| **ㅅ** | **s**                 | tg    | **nn**                 | td    | **nn**                 | **nm**  |
| **ㅇ** | ng                    | ngg   | ngn                    | ngd   | **ngn**                | ngm     |
| **ㅈ** | **j**                 | tg    | **nn**                 | td    | **nn**                 | **nm**  |
| **ㅊ** | **ch**                | tg    | **nn**                 | td    | **nn**                 | **nm**  |
| **ㅌ** | **t, ch**<sup>3</sup> | tg    | **nn**                 | td    | **nn**                 | **nm**  |
| **ㅎ** | **h**                 | **k** | **nn**                 | **t** | **nn**                 | **nm**  |

<sup>1</sup> Always transliterated as **d** in the current implementation  
<sup>2</sup> Always transliterated as **ll** in the current implementation  
<sup>3</sup> Always transliterated as **t** in the current implementation

## Licence
Copyright 2019 Sophie Collard \<https://github.com/sophiecollard>

Licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0) (the "License");
you may not use this software except in compliance with the License.

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
