package hangeul4s.implicits

trait Implicits
  extends hangeul4s.encoding.syntax.EncodingSyntax
    with hangeul4s.parsing.generics.ParsingGenerics
    with hangeul4s.parsing.syntax.ParsingSyntax
    with hangeul4s.syntax.Syntax
    with hangeul4s.transliteration.generics.TransliterationGenerics
    with hangeul4s.transliteration.instances.TransliterationInstances
    with hangeul4s.transliteration.syntax.TransliterationSyntax
