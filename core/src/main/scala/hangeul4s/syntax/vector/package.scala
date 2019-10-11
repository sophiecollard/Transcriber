package hangeul4s.syntax

package object vector {

  implicit class VectorOps[A](vector: Vector[A]) {

    private type Neighbors[A] = (Option[A], A, Option[A])

    def zipWithNeighbors: Vector[Neighbors[A]] = {
      val builder = Vector.newBuilder[Neighbors[A]]

      @scala.annotation.tailrec
      def loop(
        remainder: List[A],
        previous: Option[A]
      ): Vector[Neighbors[A]] =
        remainder match {
          case Nil =>
            builder.result
          case fst :: Nil =>
            builder += ((previous, fst, None))
            builder.result
          case fst :: snd :: tail =>
            builder += ((previous, fst, Some(snd)))
            loop(snd :: tail, Some(fst))
        }

      loop(vector.toList, previous = None)
    }

  }

}
