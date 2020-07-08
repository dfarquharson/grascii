package io.dfarquharson

import java.io.File

import org.scalatest.FunSuite

import scala.annotation.tailrec

class TypesTest extends FunSuite {

  def pretty[A](caseClass: A): Unit = {
    println(s"${caseClass.getClass.getName}:")
    println(caseClass)
    println()
  }

  def lotsOfPretty[A](caseClasses: A*): Unit = {
    caseClasses.foreach(pretty)
  }

  test("Coordinate") {
    lotsOfPretty(
      Coordinate(1, 1),
      Coordinate(0, 0),
      Coordinate(0, 1),
      Coordinate(1, 0))
  }

  test("Cell") {
    pretty(
      Cell(Coordinate(0, 1), occupied = false, " "))
  }

  test("Grid") {
    pretty(
      Grid(List(
        List(
          Cell(Coordinate(0, 0), occupied = false, " "),
          Cell(Coordinate(1, 0), occupied = false, " "),
          Cell(Coordinate(2, 0), occupied = false, " "),
          Cell(Coordinate(3, 0), occupied = false, " "),
          Cell(Coordinate(4, 0), occupied = false, " ")),
        List(
          Cell(Coordinate(0, 1), occupied = false, " "),
          Cell(Coordinate(1, 1), occupied = false, " "),
          Cell(Coordinate(2, 1), occupied = false, " "),
          Cell(Coordinate(3, 1), occupied = false, " "),
          Cell(Coordinate(4, 1), occupied = false, " ")),
        List(
          Cell(Coordinate(0, 2), occupied = false, " "),
          Cell(Coordinate(1, 2), occupied = false, " "),
          Cell(Coordinate(2, 2), occupied = false, " "),
          Cell(Coordinate(3, 2), occupied = false, " "),
          Cell(Coordinate(4, 2), occupied = false, " ")),
        List(
          Cell(Coordinate(0, 3), occupied = false, " "),
          Cell(Coordinate(1, 3), occupied = false, " "),
          Cell(Coordinate(2, 3), occupied = false, " "),
          Cell(Coordinate(3, 3), occupied = false, " "),
          Cell(Coordinate(4, 3), occupied = false, " ")),
        List(
          Cell(Coordinate(0, 4), occupied = false, " "),
          Cell(Coordinate(1, 4), occupied = false, " "),
          Cell(Coordinate(2, 4), occupied = false, " "),
          Cell(Coordinate(3, 4), occupied = false, " "),
          Cell(Coordinate(4, 4), occupied = false, " ")),
      ), 5, 5))
  }

  test("GridNode") {
    pretty(
      GridNode(
        List(
          Cell(Coordinate(0, 0), occupied = true, "+"),
          Cell(Coordinate(0, 1), occupied = true, "|"),
          Cell(Coordinate(0, 2), occupied = true, "+"),
          Cell(Coordinate(1, 0), occupied = true, "-"),
          Cell(Coordinate(1, 1), occupied = true, " "),
          Cell(Coordinate(1, 2), occupied = true, "-"),
          Cell(Coordinate(2, 0), occupied = true, "-"),
          Cell(Coordinate(2, 1), occupied = true, "A"),
          Cell(Coordinate(2, 2), occupied = true, "-"),
          Cell(Coordinate(3, 0), occupied = true, "-"),
          Cell(Coordinate(3, 1), occupied = true, " "),
          Cell(Coordinate(3, 2), occupied = true, "-"),
          Cell(Coordinate(4, 0), occupied = true, "+"),
          Cell(Coordinate(4, 1), occupied = true, "|"),
          Cell(Coordinate(4, 2), occupied = true, "+")),
        List(
          Cell(Coordinate(0, 1), occupied = true, "|"),
          Cell(Coordinate(4, 1), occupied = true, "|"))
      ))
  }

  test("GridEdge") {
    pretty(
      GridEdge(
        "0",
        List(
          Cell(Coordinate(4, 1), occupied = true, "0"),
          Cell(Coordinate(5, 1), occupied = true, "0"),
          Cell(Coordinate(6, 1), occupied = true, "0"),
          Cell(Coordinate(7, 1), occupied = true, "0"),
          Cell(Coordinate(8, 1), occupied = true, "0")),
        Cell(Coordinate(4, 1), occupied = true, "0"),
        Cell(Coordinate(8, 1), occupied = true, "0"),
        GridNode(
          List(Cell(Coordinate(4, 1), occupied = true, "0")),
          List(Cell(Coordinate(4, 1), occupied = true, "0"))),
        GridNode(
          List(Cell(Coordinate(8, 1), occupied = true, "0")),
          List(Cell(Coordinate(8, 1), occupied = true, "0")))))
  }

  test("GridEdgeProbe") {
    pretty(
      GridEdgeProbe(
        Grid(List(
          List(
            Cell(Coordinate(0, 0), occupied = false, " "),
            Cell(Coordinate(1, 0), occupied = false, " "),
            Cell(Coordinate(2, 0), occupied = false, " "),
            Cell(Coordinate(3, 0), occupied = false, " "),
            Cell(Coordinate(4, 0), occupied = false, " ")),
          List(
            Cell(Coordinate(0, 1), occupied = false, " "),
            Cell(Coordinate(1, 1), occupied = false, " "),
            Cell(Coordinate(2, 1), occupied = false, " "),
            Cell(Coordinate(3, 1), occupied = false, " "),
            Cell(Coordinate(4, 1), occupied = false, " "))),
          2, 5),
        GridEdge(
          "0",
          List(
            Cell(Coordinate(4, 1), occupied = true, "0"),
            Cell(Coordinate(5, 1), occupied = true, "0"),
            Cell(Coordinate(6, 1), occupied = true, "0"),
            Cell(Coordinate(7, 1), occupied = true, "0"),
            Cell(Coordinate(8, 1), occupied = true, "0")),
          Cell(Coordinate(4, 1), occupied = true, "0"),
          Cell(Coordinate(8, 1), occupied = true, "0"),
          GridNode(
            List(Cell(Coordinate(4, 1), occupied = true, "0")),
            List(Cell(Coordinate(4, 1), occupied = true, "0"))),
          GridNode(
            List(Cell(Coordinate(8, 1), occupied = true, content = "0")),
            List(Cell(Coordinate(8, 1), occupied = true, content = "0")))),
        lastCell = Cell(Coordinate(8, 1), occupied = true, content = "0"),
        potentialNextCells = List(),
        distanceToGoal = 0))
  }

}

class FunctionPlaygroundTest extends FunSuite {

  test("sanity") {
    assert(true)
  }

  test("cwd") {
    println(new File(".").getAbsolutePath)
  }

  test("simple distances") {
    assert(Functions.distanceBetweenCoordinates(Coordinate(0, 0), Coordinate(1, 1)) == 2)
    assert(Functions.distanceBetweenCoordinates(Coordinate(0, 0), Coordinate(2, 1)) == 3)
    assert(Functions.distanceBetweenCoordinates(Coordinate(0, 0), Coordinate(1, 2)) == 3)
    assert(Functions.distanceBetweenCoordinates(Coordinate(1, 1), Coordinate(0, 0)) == 2)
    assert(Functions.distanceBetweenCoordinates(Coordinate(2, 1), Coordinate(0, 0)) == 3)
    assert(Functions.distanceBetweenCoordinates(Coordinate(1, 2), Coordinate(0, 0)) == 3)
    assert(Functions.distanceBetweenCoordinates(Coordinate(1, 1), Coordinate(1, 1)) == 0)
    assert(Functions.distanceBetweenCoordinates(Coordinate(1, 1), Coordinate(1, 0)) == 1)
    assert(Functions.distanceBetweenCoordinates(Coordinate(1, 1), Coordinate(2, 2)) == 2)
  }

}

object Functions {
  def distanceBetweenCoordinates(coord1: Coordinate, coord2: Coordinate): Int = {
    Math.abs(coord1.x - coord2.x) + Math.abs(coord1.y - coord2.y)
  }

  @tailrec
  def createEdgeOnGrid[A](grid: Grid[A],
                          sourceNode: GridNode[A],
                          targetNode: GridNode[A],
                          edge: GridEdge[A],
                          edgeProbe: GridEdgeProbe[A]
                         ): Grid[A] = {
    if (edgeProbe.lastCell == edgeProbe.gridEdge.destinationCell) {
      grid
    } else {
      // TODO: modify these values such that we productively recur
      createEdgeOnGrid(grid, sourceNode, targetNode, edge, edgeProbe)
    }
  }
}

case class Coordinate(x: Int, y: Int)

case class Cell[A](coordinate: Coordinate, occupied: Boolean, content: A)

case class Grid[A](cells: List[List[Cell[A]]], height: Int, width: Int)

case class GridNode[A](occupiedCells: List[Cell[A]],
                       availableCellsForEdgeConnections: List[Cell[A]])

case class GridEdge[A](edgeContent: A,
                       occupiedCells: List[Cell[A]],
                       sourceCell: Cell[A],
                       destinationCell: Cell[A],
                       sourceNode: GridNode[A],
                       destinationNode: GridNode[A])

case class GridEdgeProbe[A](grid: Grid[A],
                            gridEdge: GridEdge[A],
                            lastCell: Cell[A],
                            potentialNextCells: List[Cell[A]],
                            distanceToGoal: Int)