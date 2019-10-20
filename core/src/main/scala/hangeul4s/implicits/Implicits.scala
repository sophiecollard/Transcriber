package hangeul4s.implicits

trait Implicits
  extends hangeul4s.encoding.syntax.EncodingSyntax
    with hangeul4s.parsing.generics.ParsingGenerics
    with hangeul4s.parsing.syntax.Syntax
    with hangeul4s.syntax.Syntax
    with hangeul4s.transliteration.generic.Generic
    with hangeul4s.transliteration.instances.Instances
    with hangeul4s.transliteration.syntax.Syntax
