package io.dfarquharson

import cats.implicits._
import io.dfarquharson.GraphMonoid._
import org.scalatest.FunSuite

class GrasciiParseTest extends FunSuite {

  test("Combine Graphs") {
    assert(List(
      Graph(
        List(Node("A"), Node("B")),
        List(Edge(Node("A"), Node("B"), "X"))
      ),
      Graph(
        List(Node("A"), Node("C")),
        List(Edge(Node("A"), Node("C"), "Y"))
      )
    ).combineAll == Graph(
      List(Node("A"), Node("B"), Node("C")),
      List(
        Edge(Node("A"), Node("B"), "X"),
        Edge(Node("A"), Node("C"), "Y"))
    ))
  }

  test("Combine Graph with Empty") {
    assert(List(
      Graph(List(), List()),
      Graph(
        List(Node("A")),
        List(Edge(Node("A"), Node("A"), "B")))
    ).combineAll ==
      Graph(
        List(Node("A")),
        List(Edge(Node("A"), Node("A"), "B"))))
  }

  test("Combine Empty Graphs") {
    assert(List(
      Graph(List(), List()),
      Graph(List(), List())
    ).combineAll ==
      Graph(List(), List()))
  }

  test("Monoidal Combination") {
    assert(List(
      Graph(List(Node("A")), List()),
      Graph(List(Node("B")), List())
    ).combineAll ==
      Graph(List(Node("A"), Node("B")), List()))
  }

  test("One thing pointing to another") {
    assert(GrasciiParse.parse(List("A=B=>C")) == Graph(
      List(Node("A"), Node("C")),
      List(Edge(Node("A"), Node("C"), "B"))
    ))
  }

  test("A few things pointing at each other") {
    assert(GrasciiParse.parse(
      List(
        "A=HTTP=>B",
        "B=GRPC=>C",
        "C=D=>A"
      )
    ) == Graph(
      List(
        Node("A"),
        Node("B"),
        Node("C")
      ),
      List(
        Edge(Node("A"), Node("B"), "HTTP"),
        Edge(Node("B"), Node("C"), "GRPC"),
        Edge(Node("C"), Node("A"), "D")
      )))
  }

}
