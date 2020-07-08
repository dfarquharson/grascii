package io.dfarquharson

import org.scalatest.FunSuite

class GrasciiHelpersTest extends FunSuite {

  test("boxNode test") {
    assert(GrasciiHelpers.boxNode("A") ==
      List(
        "+---+",
        "| A |",
        "+---+")
    )
  }

  test("createGrid test") {
    assert(GrasciiHelpers.createGrid(
      GrasciiParse.parse(List(
        "A=0=>B",
        "B=1=>C"
      ))
    )
      ==
      List(
        List('+', '-', '-', '-', '+'),
        List('|', ' ', 'A', ' ', '|'),
        List('+', '-', '-', '-', '+'),
        List('+', '-', '-', '-', '+'),
        List('|', ' ', 'B', ' ', '|'),
        List('+', '-', '-', '-', '+'),
        List('+', '-', '-', '-', '+'),
        List('|', ' ', 'C', ' ', '|'),
        List('+', '-', '-', '-', '+')
      ))
  }

  test("increaseGridSizeForEdges test") {
    val graph = GrasciiParse.parse(List(
      "A=0=>B",
      "B=1=>C"
    ))
    val grid = GrasciiHelpers.createGrid(graph)
    assert(GrasciiHelpers.increaseGridSizeForEdges(graph, grid)
      ==
      List(
        List('+', '-', '-', '-', '+', ' ', ' ', ' ', ' ', ' ', ' '),
        List('|', ' ', 'A', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' '),
        List('+', '-', '-', '-', '+', ' ', ' ', ' ', ' ', ' ', ' '),
        List('+', '-', '-', '-', '+', ' ', ' ', ' ', ' ', ' ', ' '),
        List('|', ' ', 'B', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' '),
        List('+', '-', '-', '-', '+', ' ', ' ', ' ', ' ', ' ', ' '),
        List('+', '-', '-', '-', '+', ' ', ' ', ' ', ' ', ' ', ' '),
        List('|', ' ', 'C', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' '),
        List('+', '-', '-', '-', '+', ' ', ' ', ' ', ' ', ' ', ' ')
      ))
  }

}
