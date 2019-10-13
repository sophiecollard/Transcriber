package hangeul4s.transliteration.romanization

import hangeul4s.error.TransliterationFailure
import hangeul4s.model.hangeul.HangeulJamo
import hangeul4s.model.hangeul.HangeulJamo.{Final, Initial, Medial}
import hangeul4s.model.romanization.RomanLetter
import hangeul4s.model.romanization.RomanLetter._
import hangeul4s.transliteration.TransliterationResult

private [transliteration] object RomanLetterHangeulizer {

  type PartialTransliterationResult[O, I] = (TransliterationResult[O], I)

  // This takes into account the order in which we expect to see Jamos
  def transliterate(letters: List[RomanLetter]): TransliterationResult[List[HangeulJamo]] = {
    @scala.annotation.tailrec
    def recurse(remainder: List[RomanLetter], accumulator: List[HangeulJamo]): TransliterationResult[List[HangeulJamo]] =
      accumulator match {
        case (_: Initial) :: _ =>
          nextMedial(remainder) match {
            case (Left(failure), _)             => Left(failure)
            case (Right(medial), nextRemainder) => recurse(nextRemainder, medial :: accumulator)
          }
        case (_: Medial) :: _ =>
          val nextInitialResult = nextInitial(remainder)
          val nextFinalResult = nextFinal(remainder)
          (nextInitialResult._1, nextInitialResult._2, nextFinalResult._1, nextFinalResult._2) match {
            case (Left(_), _, Left(failure), _)        => Left(failure)
            case (Right(initial), nextRemainder, _, _) => recurse(nextRemainder, initial :: accumulator)
            case (_, _, Right(final_), nextRemainder)  => recurse(nextRemainder, final_ :: accumulator)
          }
        case Nil | (_: Final) :: _ =>
          nextInitial(remainder) match {
            case (Left(failure), _)              => Left(failure)
            case (Right(initial), nextRemainder) => recurse(nextRemainder, initial :: accumulator)
          }
      }

    recurse(remainder = letters, accumulator = Nil)
  }

  def nextInitial(letters: List[RomanLetter]): PartialTransliterationResult[Initial, List[RomanLetter]] =
    letters match {
      case Nil            => (TransliterationResult.failure(TransliterationFailure.EmptyInput), Nil) // TODO this should be checked upstream
      //case Nil => HangeulJamo.Initial.ㅇ // Silent in initial position
      case B :: tail      => (TransliterationResult.success(Initial.ㅂ), tail)
      case C :: H :: tail => (TransliterationResult.success(Initial.ㅊ), tail)
      case D :: tail      => (TransliterationResult.success(Initial.ㄷ), tail)
      case G :: tail      => (TransliterationResult.success(Initial.ㄱ), tail)
      case H :: tail      => (TransliterationResult.success(Initial.ㅎ), tail)
      case J :: J :: tail => (TransliterationResult.success(Initial.ㅉ), tail)
      case J :: tail      => (TransliterationResult.success(Initial.ㅈ), tail)
      case K :: K :: tail => (TransliterationResult.success(Initial.ㄲ), tail)
      case K :: tail      => (TransliterationResult.success(Initial.ㅋ), tail)
      case M :: tail      => (TransliterationResult.success(Initial.ㅁ), tail)
      case N :: tail      => (TransliterationResult.success(Initial.ㄴ), tail)
      case P :: P :: tail => (TransliterationResult.success(Initial.ㅃ), tail)
      case P :: tail      => (TransliterationResult.success(Initial.ㅍ), tail)
      case R :: tail      => (TransliterationResult.success(Initial.ㄹ), tail)
      case S :: S :: tail => (TransliterationResult.success(Initial.ㅆ), tail)
      case S :: tail      => (TransliterationResult.success(Initial.ㅅ), tail)
      case T :: T :: tail => (TransliterationResult.success(Initial.ㄸ), tail)
      case T :: tail      => (TransliterationResult.success(Initial.ㅌ), tail)
      case notMatched     => (TransliterationResult.failure(TransliterationFailure.InvalidInput("")), notMatched) // TODO add message
    }

  def nextMedial(letters: List[RomanLetter]): PartialTransliterationResult[Medial, List[RomanLetter]] =
    letters match {
      case Nil                 => (TransliterationResult.failure(TransliterationFailure.EmptyInput), Nil) // TODO this should be checked upstream
      case A :: E :: tail      => (TransliterationResult.success(Medial.ㅐ), tail)
      case A :: tail           => (TransliterationResult.success(Medial.ㅏ), tail)
      case E :: O :: tail      => (TransliterationResult.success(Medial.ㅓ), tail)
      case E :: U :: tail      => (TransliterationResult.success(Medial.ㅡ), tail)
      case E :: tail           => (TransliterationResult.success(Medial.ㅔ), tail)
      case I :: tail           => (TransliterationResult.success(Medial.ㅣ), tail)
      case O :: E :: tail      => (TransliterationResult.success(Medial.ㅚ), tail)
      case O :: tail           => (TransliterationResult.success(Medial.ㅗ), tail)
      case U :: I :: tail      => (TransliterationResult.success(Medial.ㅢ), tail)
      case U :: tail           => (TransliterationResult.success(Medial.ㅜ), tail)
      case Y :: A :: E :: tail => (TransliterationResult.success(Medial.ㅒ), tail)
      case Y :: A :: tail      => (TransliterationResult.success(Medial.ㅑ), tail)
      case Y :: E :: O :: tail => (TransliterationResult.success(Medial.ㅕ), tail)
      case Y :: E :: tail      => (TransliterationResult.success(Medial.ㅖ), tail)
      case Y :: O :: tail      => (TransliterationResult.success(Medial.ㅛ), tail)
      case Y :: U :: tail      => (TransliterationResult.success(Medial.ㅠ), tail)
      case W :: A :: E :: tail => (TransliterationResult.success(Medial.ㅙ), tail)
      case W :: A :: tail      => (TransliterationResult.success(Medial.ㅘ), tail)
      case W :: E :: tail      => (TransliterationResult.success(Medial.ㅞ), tail)
      case W :: I :: tail      => (TransliterationResult.success(Medial.ㅟ), tail)
      case W :: O :: tail      => (TransliterationResult.success(Medial.ㅝ), tail)
      case notMatched          => (TransliterationResult.failure(TransliterationFailure.InvalidInput("")), notMatched) // TODO add message
    }

  // TODO How to decide between finals that map to the same roman letter?
  def nextFinal(letters: List[RomanLetter]): PartialTransliterationResult[Final, List[RomanLetter]] =
    letters match {
      case Nil            => (TransliterationResult.failure(TransliterationFailure.EmptyInput), Nil) // TODO this should be checked upstream
      case K :: tail      => (TransliterationResult.success(Final.ㄱ), tail)
//      case K :: tail      => (TransliterationResult.success(Final.ㄲ), tail)
//      case K :: tail      => (TransliterationResult.success(Final.ㄳ), tail)
//      case K :: tail      => (TransliterationResult.success(Final.ㄺ), tail)
//      case K :: tail      => (TransliterationResult.success(Final.ㅋ), tail)
      case L :: tail      => (TransliterationResult.success(Final.ㄹ), tail)
//      case L :: tail      => (TransliterationResult.success(Final.ㄽ), tail)
//      case L :: tail      => (TransliterationResult.success(Final.ㄾ), tail)
//      case L :: tail      => (TransliterationResult.success(Final.ㅀ), tail)
      case M :: tail      => (TransliterationResult.success(Final.ㄻ), tail)
//      case M :: tail      => (TransliterationResult.success(Final.ㅁ), tail)
      case N :: G :: tail => (TransliterationResult.success(Final.ㅇ), tail)
      case N :: tail      => (TransliterationResult.success(Final.ㄴ), tail)
//      case N :: tail      => (TransliterationResult.success(Final.ㄵ), tail)
//      case N :: tail      => (TransliterationResult.success(Final.ㄶ), tail)
      case P :: tail      => (TransliterationResult.success(Final.ㄼ), tail)
//      case P :: tail      => (TransliterationResult.success(Final.ㄿ), tail)
//      case P :: tail      => (TransliterationResult.success(Final.ㅂ), tail)
//      case P :: tail      => (TransliterationResult.success(Final.ㅄ), tail)
//      case P :: tail      => (TransliterationResult.success(Final.ㅍ), tail)
      case T :: tail      => (TransliterationResult.success(Final.ㄷ), tail)
//      case T :: tail      => (TransliterationResult.success(Final.ㅅ), tail)
//      case T :: tail      => (TransliterationResult.success(Final.ㅆ), tail)
//      case T :: tail      => (TransliterationResult.success(Final.ㅈ), tail)
//      case T :: tail      => (TransliterationResult.success(Final.ㅊ), tail)
//      case T :: tail      => (TransliterationResult.success(Final.ㅌ), tail)
//      case T :: tail      => (TransliterationResult.success(Final.ㅎ), tail)
      case notMatched     => (TransliterationResult.failure(TransliterationFailure.InvalidInput("")), notMatched) // TODO add message
    }

}
