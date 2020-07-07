package io.dfarquharson

import java.io.File

import org.scalatest.FunSuite

class ModelingTest extends FunSuite {

  test("sanity") {
    assert(true)
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

  test("cwd") {
    println(new File(".").getAbsolutePath)
  }

}

object Functions {
  def distanceBetweenCoordinates(coord1: Coordinate, coord2: Coordinate): Int = {
    Math.abs(coord1.x - coord2.x) + Math.abs(coord1.y - coord2.y)
  }
}

case class Coordinate(x: Int, y: Int)

case class Cell(coordinate: Coordinate, occupied: Boolean, content: String)

case class Grid(cells: List[List[Cell]], height: Int, width: Int)

case class GridNode(occupiedCells: List[Cell],
                    availableCellsForEdgeConnections: List[Cell])

case class GridEdge(occupiedCells: List[Cell],
                    sourceCell: Cell,
                    destinationCell: Cell,
                    sourceNode: GridNode,
                    destinationNode: GridNode)

case class GridEdgeProbe(grid: Grid,
                         cellsSoFar: List[Cell],
                         originCell: Cell,
                         targetCell: Cell,
                         lastCell: Cell,
                         potentialNextCells: List[Cell],
                         distanceToGoal: Int)