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
      Cell(Coordinate(0, 1), " "))
  }

  test("Grid") {
    pretty(
      Grid(List(
        Cell(Coordinate(0, 0), " "),
        Cell(Coordinate(1, 0), " "),
        Cell(Coordinate(2, 0), " "),
        Cell(Coordinate(3, 0), " "),
        Cell(Coordinate(4, 0), " "),
        Cell(Coordinate(0, 1), " "),
        Cell(Coordinate(1, 1), " "),
        Cell(Coordinate(2, 1), " "),
        Cell(Coordinate(3, 1), " "),
        Cell(Coordinate(4, 1), " "),
        Cell(Coordinate(0, 2), " "),
        Cell(Coordinate(1, 2), " "),
        Cell(Coordinate(2, 2), " "),
        Cell(Coordinate(3, 2), " "),
        Cell(Coordinate(4, 2), " "),
        Cell(Coordinate(0, 3), " "),
        Cell(Coordinate(1, 3), " "),
        Cell(Coordinate(2, 3), " "),
        Cell(Coordinate(3, 3), " "),
        Cell(Coordinate(4, 3), " "),
        Cell(Coordinate(0, 4), " "),
        Cell(Coordinate(1, 4), " "),
        Cell(Coordinate(2, 4), " "),
        Cell(Coordinate(3, 4), " "),
        Cell(Coordinate(4, 4), " "))))
  }

  test("GridNode") {
    pretty(
      GridNode(
        List(
          Cell(Coordinate(0, 0), "+"),
          Cell(Coordinate(0, 1), "|"),
          Cell(Coordinate(0, 2), "+"),
          Cell(Coordinate(1, 0), "-"),
          Cell(Coordinate(1, 1), " "),
          Cell(Coordinate(1, 2), "-"),
          Cell(Coordinate(2, 0), "-"),
          Cell(Coordinate(2, 1), "A"),
          Cell(Coordinate(2, 2), "-"),
          Cell(Coordinate(3, 0), "-"),
          Cell(Coordinate(3, 1), " "),
          Cell(Coordinate(3, 2), "-"),
          Cell(Coordinate(4, 0), "+"),
          Cell(Coordinate(4, 1), "|"),
          Cell(Coordinate(4, 2), "+")),
        List(
          Cell(Coordinate(0, 1), "|"),
          Cell(Coordinate(4, 1), "|"))
      ))
  }

  test("GridEdge") {
    pretty(
      GridEdge(
        "0",
        List(
          Cell(Coordinate(4, 1), "0"),
          Cell(Coordinate(5, 1), "0"),
          Cell(Coordinate(6, 1), "0"),
          Cell(Coordinate(7, 1), "0"),
          Cell(Coordinate(8, 1), "0")),
        Cell(Coordinate(4, 1), "0"),
        Cell(Coordinate(8, 1), "0"),
        GridNode(
          List(Cell(Coordinate(4, 1), "0")),
          List(Cell(Coordinate(4, 1), "0"))),
        GridNode(
          List(Cell(Coordinate(8, 1), "0")),
          List(Cell(Coordinate(8, 1), "0")))))
  }

  test("GridEdgeProbe") {
    pretty(
      GridEdgeProbe(
        Grid(List(
          Cell(Coordinate(0, 0), " "),
          Cell(Coordinate(1, 0), " "),
          Cell(Coordinate(2, 0), " "),
          Cell(Coordinate(3, 0), " "),
          Cell(Coordinate(4, 0), " "),
          Cell(Coordinate(0, 1), " "),
          Cell(Coordinate(1, 1), " "),
          Cell(Coordinate(2, 1), " "),
          Cell(Coordinate(3, 1), " "),
          Cell(Coordinate(4, 1), " "))),
        GridEdge(
          "0",
          List(
            Cell(Coordinate(4, 1), "0"),
            Cell(Coordinate(5, 1), "0"),
            Cell(Coordinate(6, 1), "0"),
            Cell(Coordinate(7, 1), "0"),
            Cell(Coordinate(8, 1), "0")),
          Cell(Coordinate(4, 1), "0"),
          Cell(Coordinate(8, 1), "0"),
          GridNode(
            List(Cell(Coordinate(4, 1), "0")),
            List(Cell(Coordinate(4, 1), "0"))),
          GridNode(
            List(Cell(Coordinate(8, 1), content = "0")),
            List(Cell(Coordinate(8, 1), content = "0")))),
        lastCell = Cell(Coordinate(8, 1), content = "0"),
        potentialNextCells = List(),
        distanceToGoal = 0))
  }

  test("GridMap") {
    pretty(
      GridMap(Map(
        Coordinate(0, 0) -> Cell(Coordinate(0, 0), " "),
        Coordinate(0, 1) -> Cell(Coordinate(0, 1), " "),
        Coordinate(1, 1) -> Cell(Coordinate(1, 1), " "))))
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

  test("occupiedCell(Cell[String])") {
    assert(!Functions.occupiedCell(Cell(Coordinate(0, 0), " ")))
    assert(!Functions.occupiedCell(Cell(Coordinate(0, 0), null)))
    assert(!Functions.occupiedCell(Cell(Coordinate(0, 0), "")))
    assert(Functions.occupiedCell(Cell(Coordinate(0, 0), "1")))
    assert(Functions.occupiedCell(Cell(Coordinate(0, 0), "0")))
  }

}

object Functions {
  def graphToGrid(graph: Graph): Grid[String] = {
    // Ensure nodes "breathe"
    // Ensure nodes have sufficient availableCellsForEdgeConnections
    // Placement on Grid? That's fluid, right? Figure out the data that represents that decision
    //
    // Doesn't matter yet, because the grid will expand as-needed while edges are being drawn,
    // so, fuck it? Pack it tight?
    // Vertical stacks on stacks on stacks
    //    graph.nodes.map()
    //    Grid(
    //      graph.nodes.map(nodeToGridNode),
    //      0, 0)
    Grid(
      graph.nodes
        .map(nodeToGridNode)
        .flatMap(_.occupiedCells))
  }

  def gridToGridMap[A](grid: Grid[A]): GridMap[A] = {
    GridMap(
      grid.cells
        .distinctBy(_.coordinate)
        .groupBy(_.coordinate)
        .view.mapValues(_.head).toMap)
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
        Cell(Coordinate(0, 0), border.corner),
        Cell(Coordinate(0, 1), border.vertical),
        Cell(Coordinate(0, 2), border.corner),
        Cell(Coordinate(1, 0), border.horizontal),
        Cell(Coordinate(1, 1), border.empty),
        Cell(Coordinate(1, 2), border.horizontal),
        Cell(Coordinate(2, 0), border.horizontal),
        Cell(Coordinate(2, 1), node.name),
        Cell(Coordinate(2, 2), border.horizontal),
        Cell(Coordinate(3, 0), border.horizontal),
        Cell(Coordinate(3, 1), border.empty),
        Cell(Coordinate(3, 2), border.horizontal),
        Cell(Coordinate(4, 0), border.corner),
        Cell(Coordinate(4, 1), border.vertical),
        Cell(Coordinate(4, 2), border.corner)),
      List(
        Cell(Coordinate(0, 1), border.vertical),
        Cell(Coordinate(4, 1), border.vertical)))
  }

  def distanceBetweenCoordinates(coord1: Coordinate, coord2: Coordinate): Int = {
    Math.abs(coord1.x - coord2.x) + Math.abs(coord1.y - coord2.y)
  }

  def occupiedCell(cell: Cell[String]): Boolean = {
    !(cell.content == null || cell.content.isEmpty || cell.content.isBlank)
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

case class Cell[A](coordinate: Coordinate, content: A)

case class Grid[A](cells: List[Cell[A]])

case class GridMap[A](cells: Map[Coordinate, Cell[A]])

case class GridNode[A](occupiedCells: List[Cell[A]],
                       // interestingly, availableCellsForEdgeConnections are always
                       // verticalSides of the GridNode.
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