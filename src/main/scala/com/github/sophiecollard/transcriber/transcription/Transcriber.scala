package com.github.sophiecollard.transcriber.transcription

import com.github.sophiecollard.transcriber.error.TranscriptionError

trait Transcriber[I, O] {

  def transcribe(text: I): Either[TranscriptionError, O]

}
