package io.dfarquharson

import org.scalatest.FunSuite

class RegexPlayground extends FunSuite {

  test("simple regex groups") {
    val string = "A=B=>C"
    val pattern = "(.*)=(.*)=>(.*)".r
    val pattern(nodeA, edge, nodeB) = string
    println(s"nodeA: $nodeA")
    println(s"nodeB: $nodeB")
    println(s"edge: $edge")
    assert(nodeA == "A")
    assert(nodeB == "C")
    assert(edge == "B")
  }

}
