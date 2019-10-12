package hangeul4s.model

import scala.annotation.tailrec

/**
  * A keyboard input, part of which has been processed and part of which is still awaiting processing
  */
final case class KeyboardInput[A, B](pending: KeyboardInput.Pending[A], processed: KeyboardInput.Processed[B]) {

  import KeyboardInput._

  /**
    * Process pending input, if any
    */
  def process(f: Pending.Compose[A] => B): KeyboardInput[A, B] =
    pending match {
      case Pending.Empty         => KeyboardInput(Pending.Empty, processed)
      case c: Pending.Compose[A] => KeyboardInput(Pending.Empty, Processed.Compose(f(c), processed))
    }

  /**
    * Process pending input, if any
    */
  def processMany(f: Pending.Compose[A] => Processed.Compose[B]): KeyboardInput[A, B] =
    pending match {
      case Pending.Empty         => KeyboardInput(Pending.Empty, processed)
      case c: Pending.Compose[A] => KeyboardInput(Pending.Empty, f(c) ++ processed)
    }

  override def toString: String = {
    pending match {
      case Pending.Empty =>
        processed.toString
      case _             => {
        val pendingToString = pending.toString
        val truncatedPendingToString = pendingToString.take(pendingToString.length - Pending.Empty.toString.length)
        truncatedPendingToString ++ processed.toString
      }
    }
  }

}

object KeyboardInput {

  /**
    * The part of a keyboard input that is still awaiting processing
    */
  sealed trait Pending[+A] {

    /**
      * Prepends a new element to this instance
      */
    final def +:[AA >: A](head: AA): Pending[AA] =
      Pending.Compose(head, this)

    /**
      * Concatenates this instance with another one
      */
    final def ++[AA >: A](other: Pending[AA]): Pending[AA] = {
      @tailrec
      def recurse(remainder: Pending[AA], accumulator: Pending[AA]): Pending[AA] =
        remainder match {
          case Pending.Empty          => accumulator
          case Pending.Compose(a, as) => recurse(as, a +: accumulator)
        }

      other match {
        case Pending.Empty => this // shortcut to avoid calling this.reverse for nothing
        case _             => recurse(remainder = this.reverse, accumulator = other)
      }
    }

    /**
      * Reverses the order of elements in this instance
      */
    final def reverse: Pending[A] = {
      @tailrec
      def recurse(remainder: Pending[A], accumulator: Pending[A]): Pending[A] =
        remainder match {
          case Pending.Empty          => accumulator
          case Pending.Compose(a, as) => recurse(as, a +: accumulator)
        }

      recurse(remainder = this, accumulator = Pending.Empty)
    }

    /**
      * Converts this instance to a List, preserving elements order
      */
    final def toList: List[A] =
      this.toReversedList.reverse

    /**
      * Converts this instance to a List, reversing the elements in the process
      */
    final def toReversedList: List[A] = {
      @tailrec
      def recurse(remainder: Pending[A], accumulator: List[A]): List[A] =
        remainder match {
          case Pending.Empty          => accumulator
          case Pending.Compose(a, as) => recurse(as, a :: accumulator)
        }

      recurse(remainder = this, accumulator = Nil)
    }

    override def toString: String =
      this match {
        case Pending.Empty         => Pending.Empty.toString
        case c: Pending.Compose[A] => c.toString
      }

  }

  object Pending {

    /**
      * An empty keyboard input pending processing
      */
    final case object Empty extends Pending[Nothing] {

      override def toString: String =
        "Pending.Empty"

    }

    /**
      * A succession of keyboard inputs pending processing, stored in reverse order such that the head corresponds to
      * the last received input
      */
    final case class Compose[A](head: A, tail: Pending[A]) extends Pending[A] {

      override def toString: String = {
        @tailrec
        def recurse(remainder: Pending[A], accumulator: String): String =
          remainder match {
            case Empty          => accumulator ++ Empty.toString
            case Compose(a, as) => recurse(as, accumulator ++ s"Pending($a) +: ")
          }

        recurse(remainder = this, accumulator = "")
      }

    }

    def apply[A](as: A*): Pending[A] =
      fromList(as.toList)

    def empty[A]: Pending[A] = Empty

    def one[A](head: A): Pending[A] = Compose(head, Empty)

    def fromList[A](l: List[A]): Pending[A] =
      fromReversedList(l.reverse)

    def fromReversedList[A](l: List[A]): Pending[A] = {
      @tailrec
      def recurse(remainder: List[A], accumulator: Pending[A]): Pending[A] =
        remainder match {
          case Nil     => accumulator
          case a :: as => recurse(as, a +: accumulator)
        }

      recurse(remainder = l, accumulator = Pending.Empty)
    }

  }

  /**
    * The part of a keyboard input that has been processed
    */
  sealed trait Processed[+B] {

    /**
      * Prepends a new element to this instance
      */
    final def +:[BB >: B](head: BB): Processed[BB] =
      Processed.Compose(head, this)

    /**
      * Concatenates this instance with another one
      */
    final def ++[BB >: B](other: Processed[BB]): Processed[BB] = {
      @tailrec
      def recurse(remainder: Processed[BB], accumulator: Processed[BB]): Processed[BB] =
        remainder match {
          case Processed.Start          => accumulator
          case Processed.Compose(b, bs) => recurse(bs, b +: accumulator)
        }

      other match {
        case Processed.Start => this // shortcut to avoid calling this.reverse for nothing
        case _               => recurse(remainder = this.reverse, accumulator = other)
      }
    }

    /**
      * Reverses the order of elements in this instance
      */
    final def reverse: Processed[B] = {
      @tailrec
      def recurse(remainder: Processed[B], accumulator: Processed[B]): Processed[B] =
        remainder match {
          case Processed.Start          => accumulator
          case Processed.Compose(b, bs) => recurse(bs, b +: accumulator)
        }

      recurse(remainder = this, accumulator = Processed.Start)
    }

    /**
      * Converts this instance to a List, preserving elements order
      */
    final def toList: List[B] =
      this.toReversedList.reverse

    /**
      * Converts this instance to a List, reversing the elements in the process
      */
    final def toReversedList: List[B] = {
      @tailrec
      def recurse(remainder: Processed[B], accumulator: List[B]): List[B] =
        remainder match {
          case Processed.Start          => accumulator
          case Processed.Compose(b, bs) => recurse(bs, b :: accumulator)
        }

      recurse(remainder = this, accumulator = Nil)
    }

    override def toString: String =
      this match {
        case Processed.Start         => Processed.Start.toString
        case c: Processed.Compose[B] => c.toString
      }

  }

  object Processed {

    /**
      * The start of a keyboard input
      */
    final case object Start extends Processed[Nothing] {

      override def toString: String =
        "Processed.Start"

    }

    /**
      * A succession of processed keyboard inputs, stored in reverse order such that the head corresponds to the last
      * processed input
      */
    final case class Compose[B](head: B, tail: Processed[B]) extends Processed[B] {

      override def toString: String = {
        @tailrec
        def recurse(remainder: Processed[B], accumulator: String): String =
          remainder match {
            case Start          => accumulator ++ Start.toString
            case Compose(b, bs) => recurse(bs, accumulator ++ s"Processed($b) +: ")
          }

        recurse(remainder = this, accumulator = "")
      }

    }

    def apply[B](bs: B*): Processed[B] =
      fromList(bs.toList)

    def start[B]: Processed[B] = Start

    def one[B](head: B): Processed[B] = Compose(head, Start)

    def fromList[B](l: List[B]): Processed[B] =
      fromReversedList(l.reverse)

    def fromReversedList[B](l: List[B]): Processed[B] = {
      @tailrec
      def recurse(remainder: List[B], accumulator: Processed[B]): Processed[B] =
        remainder match {
          case Nil     => accumulator
          case b :: bs => recurse(bs, b +: accumulator)
        }

      recurse(remainder = l, accumulator = Processed.Start)
    }

  }

  def blank[A, B]: KeyboardInput[A, B] = KeyboardInput(Pending.Empty, Processed.Start)

}
