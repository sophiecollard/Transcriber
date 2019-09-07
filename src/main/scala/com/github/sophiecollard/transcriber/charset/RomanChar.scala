package com.github.sophiecollard.transcriber.charset

sealed abstract class RomanChar(value: Char)

object RomanChar {

  final case object A extends RomanChar('a')
  final case object B extends RomanChar('b')
  final case object C extends RomanChar('c')
  final case object D extends RomanChar('d')
  final case object E extends RomanChar('e')
  final case object F extends RomanChar('f')
  final case object G extends RomanChar('g')
  final case object H extends RomanChar('h')
  final case object I extends RomanChar('i')
  final case object J extends RomanChar('j')
  final case object K extends RomanChar('k')
  final case object L extends RomanChar('l')
  final case object M extends RomanChar('m')
  final case object N extends RomanChar('n')
  final case object O extends RomanChar('o')
  final case object P extends RomanChar('p')
  final case object Q extends RomanChar('q')
  final case object R extends RomanChar('r')
  final case object S extends RomanChar('s')
  final case object T extends RomanChar('t')
  final case object U extends RomanChar('u')
  final case object V extends RomanChar('v')
  final case object W extends RomanChar('w')
  final case object X extends RomanChar('x')
  final case object Y extends RomanChar('y')
  final case object Z extends RomanChar('z')

}
