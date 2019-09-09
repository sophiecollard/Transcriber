package com.github.sophiecollard.transcriber.text

import com.github.sophiecollard.transcriber.charset.HangeulSyllabicBlock

final case class HangeulWord(blocks: Vector[HangeulSyllabicBlock])
