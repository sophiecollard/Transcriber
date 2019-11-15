package hangeul4s.examples

import cats.effect.{IO, Sync}
import cats.implicits._
import fs2.{Pipe, Pure, Stream}
import hangeul4s.implicits._
import hangeul4s.model.hangeul.HangeulTextElement
import hangeul4s.model.romanization.RomanizedTextElement
import org.specs2.mutable.Specification

class TextStreamTransliterationExample extends Specification {

  /**
    * First paragraph of the Korean Wikipedia article on Seoul (retrieved 2019-10-19)
    *
    * See https://ko.wikipedia.org/wiki/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C
    */
  private val input = Vector(
    "서울특별시(서울特別市)는 대한민국의 수도이자 도시이다. ",
    "백제의 첫 수도인 위례성이었고, 고려 때는 남경(南京)이었으며, 조선의 수도가 된 이후로 현재까지 대한민국 정치·경제·사회·문화의 중심지 역할을 하고 있다. ",
    "중앙으로 한강이 흐르고, 북한산, 관악산, 도봉산, 불암산, 인능산, 인왕산, 청계산 등의 여러 산들로 둘러싸인 분지 지형의 도시이다. ",
    "넓이는 605.2 km²으로 대한민국 전 국토의 0.6%도 못 미치지만, 천만 명 정도의 인구가 살고 있어 인구밀도는 현저히 높다."
  )

  private val expectedResult = Vector(
    "seoulteukbyeolsi", "(", "seoul", "特別市)", "neun", " ", "daehanmingugui", " ", "sudoija", " ",
    "dosiida", ". ", "baekjeui", " ", "cheot", " ", "sudoin", " ", "wiryeseongieotgo", ", ", "goryeo", " ",
    "ttaeneun", " ", "namgyeong", "(南京)", "ieosseumyeo", ", ", "joseonui",  " ", "sudoga", " ", "doen", " ",
    "ihuro", " ", "hyeonjaekkaji", " ", "daehanminguk", " ", "jeongchi", "·", "gyeongje", "·", "sahoe", "·",
    "munhwaui", " ", "jungsimji", " ", "yeokhareul", " ", "hago", " ", "itda", ". ", "jungangeuro", " ", "hangangi",
    " ", "heureugo", ", ", "bukhansan", ", ", "gwanaksan", ", ", "dobongsan", ", ", "buramsan", ", ", "inneungsan",
    ", ", "inwangsan", ", ", "cheonggyesan", " ", "deungui", " ", "yeoreo", " ", "sandeullo", " ", "dulleossain",
    " ", "bunji", " ", "jihyeongui", " ", "dosiida", ". ", "neolbineun", " 605.2 km²", "euro", " ", "daehanminguk",
    " ", "jeon", " ", "guktoui", " 0.6%", "do", " ", "mot", " ", "michijiman", ", ", "cheonman", " ", "myeong", " ",
    "jeongdoui", " ", "inguga", " ", "salgo", " ", "isseo", " ", "ingumildoneun", " ", "hyeonjeohi", " ", "nopda",
    "."
  )

  "This library" should {

    "tokenize&parse, transliterate and unparse&untokenize a stream of Hangeul text" in {
      def parse[F[_]](implicit F: Sync[F]): Pipe[F, String, HangeulTextElement] =
        stream => stream
          .map(_.parseToF[Vector, HangeulTextElement])
          .flatMap {
            case Right(elements)      => Stream.emits(elements).covary[F]
            case Left(parsingFailure) => Stream.raiseError[F](throw new RuntimeException(parsingFailure.message))
          }

      def romanize[F[_]](implicit F: Sync[F]): Pipe[F, HangeulTextElement, RomanizedTextElement] =
        stream => stream
          .map(_.transliterateTo[RomanizedTextElement])
          .evalMap[F, RomanizedTextElement] {
            case Right(romanizedTextElement)  => F.pure(romanizedTextElement)
            case Left(transliterationFailure) => F.raiseError(throw new RuntimeException(transliterationFailure.message))
          }

      def unparse: Pipe[Pure, RomanizedTextElement, String] =
        stream => stream
          .map(_.unparseTo[String])

      val result = Stream
        .emits[IO, String](input)
        .through(parse)
        .through(romanize)
        .through(unparse)
        .compile.toVector
        .unsafeRunSync

      result ==== expectedResult
    }

  }

}
