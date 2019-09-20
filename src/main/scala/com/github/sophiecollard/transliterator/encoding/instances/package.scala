package com.github.sophiecollard.transliterator.encoding

import java.text.Normalizer

import com.github.sophiecollard.transliterator.error.{DecodingError, EncodingError}
import com.github.sophiecollard.transliterator.model._
import com.github.sophiecollard.transliterator.syntax.string._

package object instances {

  /**
    * Instance for decoding/encoding [[HangeulSyllabicBlock]] from/to Char
    *
    * The decimal code point of precomposed Hangeul syllables in the Hangul Syllables Unicode block can be derived from
    * the indices of their constituent [[HangeulJamo]] characters using the following formula:
    *
    *   ((initial) × 588 + (medial) × 28 + (final)) + 44032
    *
    * See https://en.wikipedia.org/wiki/Korean_language_and_computers#Hangul_in_Unicode
    */
  implicit val HangeulSyllabicBlockCodec: Codec[Char, HangeulSyllabicBlock] =
    new Codec[Char, HangeulSyllabicBlock] {
      override def encode(decoded: HangeulSyllabicBlock): Either[EncodingError, Char] = {
        val composition = Normalizer.normalize(decoded.charSequence, Normalizer.Form.NFC)

        composition.safeCharAt(0) match {
          case Some(char) => Right(char)
          case None       => Left(EncodingError.FailedToEncodeHangeulSyllabicBlock(decoded))
        }
      }

      override def decode(encoded: Char): Either[DecodingError, HangeulSyllabicBlock] = {
        val decomposition: String = Normalizer.normalize(encoded.toString, Normalizer.Form.NFD)

        (
          decomposition.safeCharAt(0).flatMap(HangeulJamo.Initial.fromChar),
          decomposition.safeCharAt(1).flatMap(HangeulJamo.Medial.fromChar),
          decomposition.safeCharAt(2).flatMap(HangeulJamo.Final.fromChar)
        ) match {
          case (Some(initial), Some(medial), None) =>
            Right(HangeulSyllabicBlock.TwoLetter(initial, medial))
          case (Some(initial), Some(medial), Some(final_)) =>
            Right(HangeulSyllabicBlock.ThreeLetter(initial, medial, final_))
          case _ =>
            Left(DecodingError.FailedToDecodeHangeulSyllabicBlock(encoded))
        }
      }
    }

}
