package hangeul4s.model

import hangeul4s.model.KeyboardInput.{Pending, Processed}

import org.specs2.mutable.Specification

class KeyboardInputSpec extends Specification {

  "KeyboardInput#Pending" should {

    "be instantiated from 0, 1 or more elements" in {
      Pending.empty[Int] shouldEqual Pending.Empty
      Pending.one('A') shouldEqual Pending.Compose('A', Pending.Empty)
      Pending("ga", "bu", "zo", "meu") shouldEqual
        Pending.Compose(
          "ga", Pending.Compose(
            "bu", Pending.Compose(
              "zo", Pending.Compose(
                "meu", Pending.Empty
              )
            )
          )
        )
    }

    "be instantiated from a List" in {
      Pending.fromList(List(1, 2, 3, 4)) shouldEqual Pending(1, 2, 3, 4)
      Pending.fromReversedList(List(1, 2, 3, 4)) shouldEqual Pending(4, 3, 2, 1)
    }

    "prepend a new element to itself" in {
      1 +: Pending(2, 3) shouldEqual Pending(1, 2, 3)
    }

    "concatenate a new Pending instance to itself" in {
      Pending("ga", "bu") ++ Pending("zo", "meu") shouldEqual Pending("ga", "bu", "zo", "meu")
    }

    "reverse the order of its elements" in {
      Pending(1, 2, 3, 4).reverse shouldEqual Pending(4, 3, 2, 1)
    }

    "convert to a List" in {
      Pending("ga", "bu", "zo", "meu").toList shouldEqual List("ga", "bu", "zo", "meu")
    }

    "convert to a List while reversing the order of its elements" in {
      Pending(1, 2, 3, 4).toReversedList shouldEqual List(4, 3, 2, 1)
    }

    "be pretty-printed" in {
      Pending("ga", "bu", "zo", "meu").toString shouldEqual
        "Pending(ga) +: Pending(bu) +: Pending(zo) +: Pending(meu) +: Pending.Empty"
    }

  }

  "KeyboardInput#Processed" should {

    "be instantiated from 0, 1 or more elements" in {
      Processed.start[Int] shouldEqual Processed.Start
      Processed.one('A') shouldEqual Processed.Compose('A', Processed.Start)
      Processed("ga", "bu", "zo", "meu") shouldEqual
        Processed.Compose(
          "ga", Processed.Compose(
            "bu", Processed.Compose(
              "zo", Processed.Compose(
                "meu", Processed.Start
              )
            )
          )
        )
    }

    "be instantiated from a List" in {
      Processed.fromList(List(1, 2, 3, 4)) shouldEqual Processed(1, 2, 3, 4)
      Processed.fromReversedList(List(1, 2, 3, 4)) shouldEqual Processed(4, 3, 2, 1)
    }

    "prepend a new element to itself" in {
      1 +: Processed(2, 3) shouldEqual Processed(1, 2, 3)
    }

    "concatenate a new Processed instance to itself" in {
      Processed("ga", "bu") ++ Processed("zo", "meu") shouldEqual Processed("ga", "bu", "zo", "meu")
    }

    "reverse the order of its elements" in {
      Processed(1, 2, 3, 4).reverse shouldEqual Processed(4, 3, 2, 1)
    }

    "convert to a List" in {
      Processed("ga", "bu", "zo", "meu").toList shouldEqual List("ga", "bu", "zo", "meu")
    }

    "convert to a List while reversing the order of its elements" in {
      Processed(1, 2, 3, 4).toReversedList shouldEqual List(4, 3, 2, 1)
    }

    "be pretty-printed" in {
      Processed("ga", "bu", "zo", "meu").toString shouldEqual
        "Processed(ga) +: Processed(bu) +: Processed(zo) +: Processed(meu) +: Processed.Start"
    }

  }

  "KeyboardInput" should {

    "process" in todo

    "process many" in todo

    "be pretty-printed" in {
      KeyboardInput(Pending("four"), Processed(3, 2, 1)).toString shouldEqual
        "Pending(four) +: Processed(3) +: Processed(2) +: Processed(1) +: Processed.Start"
    }

  }

}
