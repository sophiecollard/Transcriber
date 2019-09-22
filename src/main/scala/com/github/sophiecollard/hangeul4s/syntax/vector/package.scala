package com.github.sophiecollard.hangeul4s.syntax

package object vector {

  implicit class VectorOps[A](vector: Vector[A]) {

    private type Neighbors[A] = (Option[A], A, Option[A])

    def zipWithNeighbors: Vector[Neighbors[A]] = {
      @scala.annotation.tailrec
      def loop(rem: List[A], prev: Option[A], acc: Vector[Neighbors[A]]): Vector[Neighbors[A]] =
        rem match {
          case Nil =>
            acc
          case fst :: Nil =>
            acc :+ (prev, fst, None)
          case fst :: snd :: tail =>
            loop(snd :: tail, Some(fst), acc :+ (prev, fst, Some(snd)))
        }

      loop(vector.toList, prev = None, acc = Vector.empty)
    }

  }

}
