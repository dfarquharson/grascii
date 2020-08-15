package io.dfarquharson

import cats.implicits._
import io.dfarquharson.GraphMonoid._
import org.scalatest.FunSuite

class GrasciiParseTest extends FunSuite {

  test("Combine Graphs") {
    assert(List(
      Graph(
        Set(Node("A"), Node("B")),
        Set(Edge(Node("A"), Node("B"), "X"))
      ),
      Graph(
        Set(Node("A"), Node("C")),
        Set(Edge(Node("A"), Node("C"), "Y"))
      )
    ).combineAll == Graph(
      Set(Node("A"), Node("B"), Node("C")),
      Set(
        Edge(Node("A"), Node("B"), "X"),
        Edge(Node("A"), Node("C"), "Y"))
    ))
  }

  test("Combine Graph with Empty") {
    assert(List(
      Graph(Set(), Set()),
      Graph(
        Set(Node("A")),
        Set(Edge(Node("A"), Node("A"), "B")))
    ).combineAll ==
      Graph(
        Set(Node("A")),
        Set(Edge(Node("A"), Node("A"), "B"))))
  }

  test("Combine Empty Graphs") {
    assert(List(
      Graph(Set(), Set()),
      Graph(Set(), Set())
    ).combineAll ==
      Graph(Set(), Set()))
  }

  test("Monoidal Combination") {
    assert(List(
      Graph(Set(Node("A")), Set()),
      Graph(Set(Node("B")), Set())
    ).combineAll ==
      Graph(Set(Node("A"), Node("B")), Set()))
  }

  test("One thing pointing to another") {
    assert(GrasciiParse.parse(List("A=B=>C")) ==
      Graph(
        Set(Node("A"), Node("C")),
        Set(Edge(Node("A"), Node("C"), "B"))
      ))
  }

  test("A few things pointing at each other") {
    assert(GrasciiParse.parse(
      List(
        "A=HTTP=>B",
        "B=GRPC=>C",
        "C=D=>A"
      )) ==
      Graph(
        Set(
          Node("A"),
          Node("B"),
          Node("C")
        ),
        Set(
          Edge(Node("A"), Node("B"), "HTTP"),
          Edge(Node("B"), Node("C"), "GRPC"),
          Edge(Node("C"), Node("A"), "D")
        )))
  }

  // TODO: ScalaCheck for input validation?

  test("Single line of good input") {
    assert(GrasciiValid.valid(
      List(
        "A=B=>C"
      )))
  }

  test("Multiple lines of good input") {
    assert(GrasciiValid.valid(
      List(
        "A=B=>C",
        "A=D=>E"
      )))
  }

  test("Only bad input to parse") {
    assert(!GrasciiValid.valid(
      List(
        "A==B==>C"
      )))
  }

  test("Some bad input and some good input to parse") {
    assert(!GrasciiValid.valid(
      List(
        "A=B=>C",
        "A=D=C"
      )))
  }

}
