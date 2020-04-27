package io.dfarquharson

import org.scalatest.FunSuite

class RegexPlayground extends FunSuite {

  test("simple regex groups") {
    val string = "A=B=>C"
    val pattern = "(.*)=(.*)=>(.*)".r
    val pattern(nodeA, edge, nodeB) = string
    assert(pattern.matches(string))
    println(s"nodeA: $nodeA")
    println(s"nodeB: $nodeB")
    println(s"edge: $edge")
    assert(nodeA == "A")
    assert(nodeB == "C")
    assert(edge == "B")
  }

  test("regex does not fully match") {
    val pattern = "(.*)=(.*)=>(.*)".r
    assert(pattern.matches("A=>B=>C"))
    assert(!pattern.matches("AB=>C"))
  }

  test("string contains") {
    val good = "A=B=>C"
    val badTooMuch = "A=>B=>C"
    val badNotEnough = "A=B=C"

    // good
    assert(good.contains("="))
    assert(good.contains("=>"))
    assert(good.split("=").length == 3)
    assert(good.split("=>").length == 2)

    // badTooMuch
    assert(badTooMuch.contains("="))
    assert(badTooMuch.contains("=>"))
    assert(badTooMuch.split("=").length == 3)
    assert(badTooMuch.split("=>").length == 3)

    // badNotEnough
    assert(badNotEnough.contains("="))
    assert(!badNotEnough.contains("=>"))
    assert(badNotEnough.split("=").length == 3)
    assert(badNotEnough.split("=>").length == 1)
  }

}
