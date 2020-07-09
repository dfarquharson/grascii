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
        Coordinate(0, 1) -> Cell(Coordinate(0, 1), "x"),
        Coordinate(1, 1) -> Cell(Coordinate(1, 1), "x"),
        Coordinate(1, 1) -> Cell(Coordinate(2, 1), " "))))
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

  test("neighborCoordinates") {
    assert(
      Functions.neighborCoordinates(Coordinate(1, 1)) ==
        Set(
          Coordinate(1, 0),
          Coordinate(1, 2),
          Coordinate(0, 1),
          Coordinate(2, 1)))
  }

  test("Best Available Coordinate One Reasonable Choice") {
    assert(
      Functions.bestAvailableCoordinate(
        currentCoordinate = Coordinate(1, 1),
        targetCoordinate = Coordinate(2, 2),
        Functions.gridToGridMap(
          Grid(
            List(
              Cell(Coordinate(0, 0), " "),
              Cell(Coordinate(0, 1), " "),
              Cell(Coordinate(0, 2), " "),
              Cell(Coordinate(1, 0), " "),
              Cell(Coordinate(1, 1), " "),
              Cell(Coordinate(1, 2), "X"),
              Cell(Coordinate(2, 0), " "),
              Cell(Coordinate(2, 1), " "),
              Cell(Coordinate(2, 2), " ")))))
        .contains(Coordinate(2, 1)))
  }

  test("Best Available Coordinate Two Reasonable Choices") {
    val bestAvailable =
      Functions.bestAvailableCoordinate(
        currentCoordinate = Coordinate(1, 1),
        targetCoordinate = Coordinate(2, 2),
        Functions.gridToGridMap(
          Grid(
            List(
              Cell(Coordinate(0, 0), " "),
              Cell(Coordinate(0, 1), " "),
              Cell(Coordinate(0, 2), " "),
              Cell(Coordinate(1, 0), " "),
              Cell(Coordinate(1, 1), " "),
              Cell(Coordinate(1, 2), " "),
              Cell(Coordinate(2, 0), " "),
              Cell(Coordinate(2, 1), " "),
              Cell(Coordinate(2, 2), " ")))))
    // Current implementation yields Coordinate(1, 2),
    // but that's incidental and not necessary,
    // and I only want to encode the necessary in this test.
    assert(bestAvailable.contains(Coordinate(2, 1))
      || bestAvailable.contains(Coordinate(1, 2)))
  }

  test("Best Available Coordinate The Long Way Around") {
    val bestAvailable =
      Functions.bestAvailableCoordinate(
        currentCoordinate = Coordinate(1, 1),
        targetCoordinate = Coordinate(2, 2),
        Functions.gridToGridMap(
          Grid(
            List(
              Cell(Coordinate(0, 0), " "),
              Cell(Coordinate(0, 1), " "),
              Cell(Coordinate(0, 2), " "),
              Cell(Coordinate(1, 0), " "),
              Cell(Coordinate(1, 1), " "),
              Cell(Coordinate(1, 2), "X"),
              Cell(Coordinate(2, 0), " "),
              Cell(Coordinate(2, 1), "X"),
              Cell(Coordinate(2, 2), " ")))))
    assert(bestAvailable.contains(Coordinate(1, 0))
      || bestAvailable.contains(Coordinate(0, 1)))
  }

  test("Best Available Coordinate Only One Choice") {
    assert(
      Functions.bestAvailableCoordinate(
        currentCoordinate = Coordinate(1, 1),
        targetCoordinate = Coordinate(2, 2),
        Functions.gridToGridMap(
          Grid(
            List(
              Cell(Coordinate(0, 0), " "),
              Cell(Coordinate(0, 1), " "),
              Cell(Coordinate(0, 2), " "),
              Cell(Coordinate(1, 0), "X"),
              Cell(Coordinate(1, 1), " "),
              Cell(Coordinate(1, 2), "X"),
              Cell(Coordinate(2, 0), " "),
              Cell(Coordinate(2, 1), "X"),
              Cell(Coordinate(2, 2), " ")))))
        .contains(Coordinate(0, 1)))
  }

  test("Best Available Coordinate Dead End") {
    assert(
      Functions.bestAvailableCoordinate(
        currentCoordinate = Coordinate(1, 1),
        targetCoordinate = Coordinate(2, 2),
        Functions.gridToGridMap(
          Grid(
            List(
              Cell(Coordinate(0, 0), " "),
              Cell(Coordinate(0, 1), "X"),
              Cell(Coordinate(0, 2), " "),
              Cell(Coordinate(1, 0), "X"),
              Cell(Coordinate(1, 1), " "),
              Cell(Coordinate(1, 2), "X"),
              Cell(Coordinate(2, 0), " "),
              Cell(Coordinate(2, 1), "X"),
              Cell(Coordinate(2, 2), " ")))))
        .isEmpty)
  }

  test("Point A to Point B Clear Path") {
    val result: Set[Cell[String]] =
      Functions.gridMapToGrid(
        Functions.findPath(
          fillContent = "X",
          currentCoordinate = Coordinate(0, 0),
          targetCoordinate = Coordinate(2, 2),
          gridMap = Functions.gridToGridMap(Grid(List()))))
        .cells
        .toSet
    println(result)
    assert(
      result ==
        Set(
          Cell(Coordinate(0, 0), "X"),
          Cell(Coordinate(0, 1), "X"),
          Cell(Coordinate(0, 2), "X"),
          Cell(Coordinate(1, 2), "X"),
          Cell(Coordinate(2, 2), "X")))
  }

  test("Point A to Point B with an Obstacle") {
    val result: Set[Cell[String]] =
      Functions.gridMapToGrid(
        Functions.findPath(
          fillContent = "X",
          currentCoordinate = Coordinate(0, 0),
          targetCoordinate = Coordinate(2, 2),
          gridMap = Functions.gridToGridMap(
            Grid(
              List(
                Cell(Coordinate(1, 2), "O"))))))
        .cells
        .toSet
    println(result)
    // This illustrates the failure of taking "first best available coordinate"
    // without working that out over a longer distance and seeing what is truly "best" in the long run.
    assert(
      result ==
        Set(
          Cell(Coordinate(0, 0), "X"),
          Cell(Coordinate(0, 1), "X"),
          Cell(Coordinate(0, 2), "X"),
          Cell(Coordinate(0, 3), "X"),
          Cell(Coordinate(1, 2), "O"),
          Cell(Coordinate(1, 3), "X"),
          Cell(Coordinate(2, 3), "X"),
          Cell(Coordinate(2, 2), "X")))
  }

  test("Point A to Point B forced down a Specific Path") {
    val result: Set[Cell[String]] =
      Functions.gridMapToGrid(
        Functions.findPath(
          fillContent = "X",
          currentCoordinate = Coordinate(0, 0),
          targetCoordinate = Coordinate(2, 2),
          gridMap = Functions.gridToGridMap(
            Grid(
              List(
                Cell(Coordinate(0, 2), "O"),
                Cell(Coordinate(1, 2), "O"),
                Cell(Coordinate(1, 0), "O"),
                Cell(Coordinate(2, 0), "O"))))))
        .cells
        .toSet
    println(result)
    assert(
      result ==
        Set(
          Cell(Coordinate(0, 0), "X"),
          Cell(Coordinate(0, 1), "X"),
          Cell(Coordinate(1, 1), "X"),
          Cell(Coordinate(2, 1), "X"),
          Cell(Coordinate(2, 2), "X"),
          Cell(Coordinate(0, 2), "O"),
          Cell(Coordinate(1, 2), "O"),
          Cell(Coordinate(1, 0), "O"),
          Cell(Coordinate(2, 0), "O")))
  }

  test("Merge Grids No Overlap") {
    assert(
      Functions.mergeGrids(
        emptyContent = 0,
        Grid(
          List(
            Cell(Coordinate(0, 1), 1),
            Cell(Coordinate(0, 2), 1))),
        Grid(
          List(
            Cell(Coordinate(1, 0), 1),
            Cell(Coordinate(2, 0), 1))))
        .cells
        .toSet ==
        Grid(
          List(
            Cell(Coordinate(0, 1), 1),
            Cell(Coordinate(0, 2), 1),
            Cell(Coordinate(1, 0), 1),
            Cell(Coordinate(2, 0), 1)))
          .cells
          .toSet)
  }

  test("Merge Grids Some Overlap") {
    assert(
      Functions.mergeGrids(
        emptyContent = 0,
        Grid(
          List(
            Cell(Coordinate(0, 1), 1),
            Cell(Coordinate(1, 1), 0))),
        Grid(
          List(
            Cell(Coordinate(1, 0), 1),
            Cell(Coordinate(1, 1), 1))))
        .cells
        .toSet ==
        Grid(
          List(
            Cell(Coordinate(0, 1), 1),
            Cell(Coordinate(1, 1), 1),
            Cell(Coordinate(1, 0), 1)))
          .cells
          .toSet)
  }

  test("Rectangle Single Cell") {
    assert(
      Functions.makeRectangleGrid(
        0,
        Grid(
          List(
            Cell(Coordinate(0, 0), 1)))) ==
        Grid(
          List(
            Cell(Coordinate(0, 0), 1))))
  }

  test("Rectangle 2 * 2") {
    assert(
      Functions.makeRectangleGrid(
        0,
        Grid(
          List(
            Cell(Coordinate(0, 0), 1),
            Cell(Coordinate(1, 1), 1))))
        .cells
        .toSet ==
        Grid(
          List(
            Cell(Coordinate(0, 0), 1),
            Cell(Coordinate(0, 1), 0),
            Cell(Coordinate(1, 0), 0),
            Cell(Coordinate(1, 1), 1)))
          .cells
          .toSet)
  }

  test("Rectangle 2 * 3") {
    assert(
      Functions.makeRectangleGrid(
        0,
        Grid(
          List(
            Cell(Coordinate(0, 0), 1),
            Cell(Coordinate(2, 1), 1))))
        .cells
        .toSet ==
        Grid(
          List(
            Cell(Coordinate(0, 0), 1),
            Cell(Coordinate(0, 1), 0),
            Cell(Coordinate(1, 0), 0),
            Cell(Coordinate(1, 1), 0),
            Cell(Coordinate(2, 0), 0),
            Cell(Coordinate(2, 1), 1)))
          .cells
          .toSet)
  }

  test("Rectangle 3 * 3") {
    assert(
      Functions.makeRectangleGrid(
        0,
        Grid(
          List(
            Cell(Coordinate(0, 0), 1),
            Cell(Coordinate(2, 2), 1))))
        .cells
        .toSet ==
        Grid(
          List(
            Cell(Coordinate(0, 0), 1),
            Cell(Coordinate(0, 1), 0),
            Cell(Coordinate(0, 2), 0),
            Cell(Coordinate(1, 0), 0),
            Cell(Coordinate(1, 1), 0),
            Cell(Coordinate(1, 2), 0),
            Cell(Coordinate(2, 0), 0),
            Cell(Coordinate(2, 1), 0),
            Cell(Coordinate(2, 2), 1)))
          .cells
          .toSet)
  }

  test("Grascii Literally One Cell") {
    val result: String =
      Functions.dumpGrascii(
        Grid(
          List(
            Cell(Coordinate(0, 0), " "))))
    println(result)
    assert(result == " ")
  }

  test("Grascii 2 * 2") {
    val result: String =
      Functions.dumpGrascii(
        Grid(
          List(
            Cell(Coordinate(0, 0), "1"),
            Cell(Coordinate(0, 1), "0"),
            Cell(Coordinate(1, 0), "0"),
            Cell(Coordinate(1, 1), "1"))))
    println(result)
    assert(result == "01\n10\n")
  }

}

object Functions {
  val border: Border[String] = Border(
    corner = "+",
    horizontal = "+",
    vertical = "|",
    empty = " ")

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
        .view
        .mapValues(_.head)
        .toMap)
  }

  def gridMapToGrid[A](gridMap: GridMap[A]): Grid[A] = {
    Grid(gridMap.cells.values.toList)
  }

  def mergeGrids[A](emptyContent: A,
                    gridA: Grid[A],
                    gridB: Grid[A]): Grid[A] = {
    Grid(
      (gridA.cells ++ gridB.cells)
        .groupBy(_.coordinate)
        .view
        .mapValues(xs => xs.find(x => x.content != emptyContent)
          .getOrElse(Cell(xs.head.coordinate, emptyContent)))
        .values
        .toList)
  }

  def makeRectangleGrid[A](emptyContent: A,
                           grid: Grid[A]): Grid[A] = {
    val xs = grid.cells.map(_.coordinate.x)
    val ys = grid.cells.map(_.coordinate.y)
    val newXs = xs.min to xs.max
    val newYs = ys.min to ys.max
    mergeGrids(
      emptyContent,
      grid,
      Grid(
        // you'll sound cool if you call this a "Cartesian Product", because it is
        newXs
          .flatMap(x => newYs
            .map(y => Cell(Coordinate(x, y), emptyContent)))
          .toList))
  }

  def dumpGrascii[A](grid: Grid[A]): String = {
    // TODO: the thing properly (proper newlines)
    grid
      .cells
      .map(_.content)
      .map(_.toString)
      .mkString
  }

  // Oof: concrete type commitment
  // What would the "borders" look like if we weren't dealing with String?
  def nodeToGridNode(node: Node): GridNode[String] = {
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

  def neighborCoordinates(coordinate: Coordinate): Set[Coordinate] = {
    Set(
      Coordinate(coordinate.x, coordinate.y - 1),
      Coordinate(coordinate.x, coordinate.y + 1),
      Coordinate(coordinate.x - 1, coordinate.y),
      Coordinate(coordinate.x + 1, coordinate.y))
  }

  def bestAvailableCoordinate(currentCoordinate: Coordinate,
                              targetCoordinate: Coordinate,
                              gridMap: GridMap[String]): Option[Coordinate] = {
    // when there are more than one "best available coordinate",
    // we should recursively pursue each one in "branched universes"
    // and see which coordinate truly ends up being "best" in the end
    neighborCoordinates(currentCoordinate)
      .map(x => (
        distanceBetweenCoordinates(targetCoordinate, x),
        occupiedCell(gridMap.cells.getOrElse(x, Cell(Coordinate(-1, -1), " "))),
        x))
      .toList
      .sortBy(x => x._1)
      .find(x => !x._2)
      .map(x => x._3)
  }

  @tailrec
  def findPath(fillContent: String,
               currentCoordinate: Coordinate,
               targetCoordinate: Coordinate,
               gridMap: GridMap[String]): GridMap[String] = {
    val gridWithCurrent = GridMap(gridMap.cells + (currentCoordinate -> Cell(currentCoordinate, fillContent)))
    if (currentCoordinate == targetCoordinate) {
      gridWithCurrent
    } else {
      val best = bestAvailableCoordinate(currentCoordinate, targetCoordinate, gridWithCurrent)
      if (best.isEmpty) {
        gridWithCurrent
      } else {
        findPath(
          fillContent,
          best.get,
          targetCoordinate,
          GridMap(gridWithCurrent.cells + (best.get -> Cell(best.get, fillContent))))
      }
    }
  }

  @deprecated("Was an initial idea that didn't pan out. Replaced by findPath")
  @tailrec
  def createEdgeOnGrid[A](gridMap: GridMap[A],
                          sourceNode: GridNode[A],
                          targetNode: GridNode[A],
                          edge: GridEdge[A],
                          edgeProbe: GridEdgeProbe[A]
                         ): GridMap[A] = {
    if (edgeProbe.lastCell == edgeProbe.gridEdge.destinationCell) {
      gridMap
    } else {
      // TODO: modify these values such that we productively recur
      createEdgeOnGrid(gridMap, sourceNode, targetNode, edge, edgeProbe)
    }
  }
}

case class Coordinate(x: Int, y: Int)

case class Cell[A](coordinate: Coordinate, content: A)

// TODO: make this a Set instead
case class Grid[A](cells: List[Cell[A]])

case class GridMap[A](cells: Map[Coordinate, Cell[A]])

case class GridNode[A](occupiedCells: List[Cell[A]],
                       // interestingly, availableCellsForEdgeConnections are always
                       // verticalSides of the GridNode.
                       // Figure out the truth lurking there.
                       // Or maybe that's just arbitrary and we should include horizontalSides as well?
                       availableCellsForEdgeConnections: List[Cell[A]])

@deprecated("not needed")
case class GridEdge[A](edgeContent: A,
                       occupiedCells: List[Cell[A]],
                       sourceCell: Cell[A],
                       destinationCell: Cell[A], // unfair? Should be given the Set[Coordinate] instead?
                       sourceNode: GridNode[A],
                       destinationNode: GridNode[A])

@deprecated("not needed")
case class GridEdgeProbe[A](grid: Grid[A],
                            gridEdge: GridEdge[A],
                            lastCell: Cell[A],
                            potentialNextCells: List[Cell[A]],
                            distanceToGoal: Int)

case class Border[A](corner: A,
                     horizontal: A,
                     vertical: A,
                     empty: A)