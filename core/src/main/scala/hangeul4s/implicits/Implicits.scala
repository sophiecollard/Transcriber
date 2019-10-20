package hangeul4s.implicits

trait Implicits
  extends hangeul4s.encoding.syntax.Syntax
    with hangeul4s.parsing.generic.Generic
    with hangeul4s.parsing.syntax.Syntax
    with hangeul4s.syntax.Syntax
    with hangeul4s.transliteration.generic.Generic
    with hangeul4s.transliteration.syntax.Syntax
