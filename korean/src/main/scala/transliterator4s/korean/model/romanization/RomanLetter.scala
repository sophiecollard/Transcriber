package transliterator4s.korean.model.romanization

sealed abstract class RomanLetter(val char: Char)

object RomanLetter {

  final case object A extends RomanLetter('a')
  final case object B extends RomanLetter('b')
  final case object C extends RomanLetter('c')
  final case object D extends RomanLetter('d')
  final case object E extends RomanLetter('e')
  final case object F extends RomanLetter('f')
  final case object G extends RomanLetter('g')
  final case object H extends RomanLetter('h')
  final case object I extends RomanLetter('i')
  final case object J extends RomanLetter('j')
  final case object K extends RomanLetter('k')
  final case object L extends RomanLetter('l')
  final case object M extends RomanLetter('m')
  final case object N extends RomanLetter('n')
  final case object O extends RomanLetter('o')
  final case object P extends RomanLetter('p')
  final case object Q extends RomanLetter('q')
  final case object R extends RomanLetter('r')
  final case object S extends RomanLetter('s')
  final case object T extends RomanLetter('t')
  final case object U extends RomanLetter('u')
  final case object V extends RomanLetter('v')
  final case object W extends RomanLetter('w')
  final case object X extends RomanLetter('x')
  final case object Y extends RomanLetter('y')
  final case object Z extends RomanLetter('z')

}
