package transliterator4s.japanese.model

sealed abstract class HiraganaDigraph(char: Char)

object HiraganaDigraph {

  sealed abstract class Vowel(char: Char) extends HiraganaDigraph(char)

  final case object あ extends Vowel('あ')
  final case object い extends Vowel('い')
  final case object う extends Vowel('う')
  final case object え extends Vowel('え')
  final case object お extends Vowel('お')

  sealed abstract class KUnion(char: Char) extends HiraganaDigraph(char)

  final case object か extends KUnion('か')
  final case object き extends KUnion('き')
  final case object く extends KUnion('く')
  final case object け extends KUnion('け')
  final case object こ extends KUnion('こ')

  sealed abstract class SUnion(char: Char) extends HiraganaDigraph(char)

  final case object さ extends SUnion('さ')
  final case object し extends SUnion('し')
  final case object す extends SUnion('す')
  final case object せ extends SUnion('せ')
  final case object そ extends SUnion('そ')

  sealed abstract class TUnion(char: Char) extends HiraganaDigraph(char)

  final case object た extends TUnion('た')
  final case object ち extends TUnion('ち')
  final case object つ extends TUnion('つ')
  final case object て extends TUnion('て')
  final case object と extends TUnion('と')

  sealed abstract class NUnion(char: Char) extends HiraganaDigraph(char)

  final case object な extends NUnion('な')
  final case object に extends NUnion('に')
  final case object ぬ extends NUnion('ぬ')
  final case object ね extends NUnion('ね')
  final case object の extends NUnion('の')

  sealed abstract class HUnion(char: Char) extends HiraganaDigraph(char)

  final case object は extends HUnion('は')
  final case object ひ extends HUnion('ひ')
  final case object ふ extends HUnion('ふ')
  final case object へ extends HUnion('へ')
  final case object ほ extends HUnion('ほ')

  sealed abstract class MUnion(char: Char) extends HiraganaDigraph(char)

  final case object ま extends MUnion('ま')
  final case object み extends MUnion('み')
  final case object む extends MUnion('む')
  final case object め extends MUnion('め')
  final case object も extends MUnion('も')

  sealed abstract class YUnion(char: Char) extends HiraganaDigraph(char)

  final case object や extends YUnion('や')
  final case object ゆ extends YUnion('ゆ')
  final case object よ extends YUnion('よ')

  sealed abstract class RUnion(char: Char) extends HiraganaDigraph(char)

  final case object ら extends RUnion('ら')
  final case object り extends RUnion('り')
  final case object る extends RUnion('る')
  final case object れ extends RUnion('れ')
  final case object ろ extends RUnion('ろ')

  sealed abstract class WUnion(char: Char) extends HiraganaDigraph(char)

  final case object わ extends WUnion('わ')
  final case object ゐ extends WUnion('ゐ')
  final case object ゑ extends WUnion('ゑ')
  final case object を extends WUnion('を')

  sealed abstract class Consonant(char: Char) extends HiraganaDigraph(char)

  final case object ん extends Consonant('ん')

}
