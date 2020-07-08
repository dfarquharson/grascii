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
        Cell(Coordinate(0, 0), occupied = false, " "),
        Cell(Coordinate(1, 0), occupied = false, " "),
        Cell(Coordinate(2, 0), occupied = false, " "),
        Cell(Coordinate(3, 0), occupied = false, " "),
        Cell(Coordinate(4, 0), occupied = false, " "),
        Cell(Coordinate(0, 1), occupied = false, " "),
        Cell(Coordinate(1, 1), occupied = false, " "),
        Cell(Coordinate(2, 1), occupied = false, " "),
        Cell(Coordinate(3, 1), occupied = false, " "),
        Cell(Coordinate(4, 1), occupied = false, " "),
        Cell(Coordinate(0, 2), occupied = false, " "),
        Cell(Coordinate(1, 2), occupied = false, " "),
        Cell(Coordinate(2, 2), occupied = false, " "),
        Cell(Coordinate(3, 2), occupied = false, " "),
        Cell(Coordinate(4, 2), occupied = false, " "),
        Cell(Coordinate(0, 3), occupied = false, " "),
        Cell(Coordinate(1, 3), occupied = false, " "),
        Cell(Coordinate(2, 3), occupied = false, " "),
        Cell(Coordinate(3, 3), occupied = false, " "),
        Cell(Coordinate(4, 3), occupied = false, " "),
        Cell(Coordinate(0, 4), occupied = false, " "),
        Cell(Coordinate(1, 4), occupied = false, " "),
        Cell(Coordinate(2, 4), occupied = false, " "),
        Cell(Coordinate(3, 4), occupied = false, " "),
        Cell(Coordinate(4, 4), occupied = false, " ")),
        5, 5))
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
          Cell(Coordinate(0, 0), occupied = false, " "),
          Cell(Coordinate(1, 0), occupied = false, " "),
          Cell(Coordinate(2, 0), occupied = false, " "),
          Cell(Coordinate(3, 0), occupied = false, " "),
          Cell(Coordinate(4, 0), occupied = false, " "),
          Cell(Coordinate(0, 1), occupied = false, " "),
          Cell(Coordinate(1, 1), occupied = false, " "),
          Cell(Coordinate(2, 1), occupied = false, " "),
          Cell(Coordinate(3, 1), occupied = false, " "),
          Cell(Coordinate(4, 1), occupied = false, " ")),
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

  test("sanityPositive") {
    assert(true)
  }

  test("sanityNegative") {
    try {
      val impossible = 1 / 0
      assert(false)
    } catch {
      case _: Exception => assert(true)
    }
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
    assert(Functions.distanceBetweenCoordinates(Coordinate(0, 0), Coordinate(0, -1)) == 1)
  }

  test("Parse a Graph and turn it into a Grid?") {
    val graph = GrasciiParse.parse(
      List(
        "A=0=>B",
        "B=1=>C",
        "C=2=>A"
      )): Graph
    println(graph)
    val grid = Functions.graphToGrid(graph)
    println(grid)
  }

  test("Trouble Graph") {
    val graph = GrasciiParse.parse(
      List(
        "A=0=>B",
        "B=1=>C",
        "A=2=>C",
        "A=3=>D",
        "C=4=>D",
        "D=5=>E",
        "C=6=>E"))
    println(graph)
  }

}

object Functions {
  def graphToGrid(graph: Graph): Grid[String] = {
    // Ensure nodes "breathe"
    // Ensure nodes have sufficient availableCellsForEdgeConnections
    // Placement on Grid? That's fluid, right? Figure out the data that represents that decision
    //
    // Doesn't matter yet, because the grid will expand as-needed while edges are being drawn, so, fuck it? Pack it tight?
    // Vertical stacks on stacks on stacks
    //    graph.nodes.map()
    //    Grid(
    //      graph.nodes.map(nodeToGridNode),
    //      0, 0)
    Grid(
      graph.nodes
        .map(nodeToGridNode)
        .flatMap(_.occupiedCells),
      graph.nodes.length * 3,
      5)
  }

  // Oof: concrete type commitment
  // What would the "borders" look like if we weren't dealing with String?
  def nodeToGridNode(node: Node): GridNode[String] = {
    val border = Border(
      corner = "+",
      horizontal = "+",
      vertical = "|",
      empty = " ")
    GridNode(
      List(
        Cell(Coordinate(0, 0), occupied = true, border.corner),
        Cell(Coordinate(0, 1), occupied = true, border.vertical),
        Cell(Coordinate(0, 2), occupied = true, border.corner),
        Cell(Coordinate(1, 0), occupied = true, border.horizontal),
        Cell(Coordinate(1, 1), occupied = true, border.empty),
        Cell(Coordinate(1, 2), occupied = true, border.horizontal),
        Cell(Coordinate(2, 0), occupied = true, border.horizontal),
        Cell(Coordinate(2, 1), occupied = true, node.name),
        Cell(Coordinate(2, 2), occupied = true, border.horizontal),
        Cell(Coordinate(3, 0), occupied = true, border.horizontal),
        Cell(Coordinate(3, 1), occupied = true, border.empty),
        Cell(Coordinate(3, 2), occupied = true, border.horizontal),
        Cell(Coordinate(4, 0), occupied = true, border.corner),
        Cell(Coordinate(4, 1), occupied = true, border.vertical),
        Cell(Coordinate(4, 2), occupied = true, border.corner)),
      List(
        Cell(Coordinate(0, 1), occupied = true, border.vertical),
        Cell(Coordinate(4, 1), occupied = true, border.vertical)))
  }

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

// shouldn't occupied be derived from the content?
case class Cell[A](coordinate: Coordinate, occupied: Boolean, content: A)

// shouldn't height and width be derived from the dimensions of cells?
case class Grid[A](cells: List[Cell[A]],
                   height: Int,
                   width: Int)

case class GridNode[A](occupiedCells: List[Cell[A]],
                       // interestingly, availableCellsForEdgeConnections are always verticalSides of the GridNode.
                       // Figure out the truth lurking there.
                       // Or maybe that's just arbitrary and we should include horizontalSides as well?
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

case class Border[A](corner: A,
                     horizontal: A,
                     vertical: A,
                     empty: A)