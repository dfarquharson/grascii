package io.dfarquharson

import org.scalatest.FunSuite

class PlaygroundTest extends FunSuite {

  test("List concat") {
    assert(List(1, 2, 3) ++ List(4, 5, 6) == List(1, 2, 3, 4, 5, 6))
  }

  test("Sets and Case Classes") {
    assert(Set(Person("A"), Person("B"), Person("C")) ==
      (List(Person("A"), Person("B")) ++ List(Person("B"), Person("C"))).toSet)
    assert(List(Person("A"), Person("B"), Person("C")) ==
      (List(Person("A"), Person("B")) ++ List(Person("B"), Person("C"))).distinct)
  }

}

case class Person(name: String)
