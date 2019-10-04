package com.github.sophiecollard.hangeul4s

import com.github.sophiecollard.hangeul4s.error.DecodingError

package object encoding {

  type DecodingResult[A] = Either[DecodingError, A]

}
