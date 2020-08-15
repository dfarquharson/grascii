package io.dfarquharson

object Grascii {

  def convert(lines: List[String]): String = {
    return ""
  }

}

object GrasciiHelpers {

  def boxNode(nodeName: String): List[String] = {
    val nodeLine = s"| $nodeName |"
    val capLine = s"+${"-" * (nodeLine.length() - 2)}+"
    return List(capLine, nodeLine, capLine)
  }

  def createGrid(graph: Graph): List[List[Char]] =
    graph.nodes
      .map(_.name)
      .map(boxNode)
      .reduce(_ ++ _)
      .map(_.toList)

  def increaseGridSizeForEdges(graph: Graph, grid: List[List[Char]], spacePerEdge: Int = 3): List[List[Char]] =
    grid.map(_ ++ (" " * graph.edges.size * spacePerEdge).toList)

  def growNodesOnGridToFitEdges(graph: Graph, grid: List[List[Char]]): List[List[Char]] =
    List()

  def getCoordinate(coordinate: Coordinate, grid: List[List[Char]]): Char =
    ' '

  def getNodeInfo(grid: List[List[Char]], corner: Char = '+'): List[Any] =
    List()

  def findOpenSlotsForEdges(grid: List[List[Char]], nodeInfos: Any): List[Any] =
    List()

  def selectEquitableEdges(potentialEdges: Map[String, Any]): Map[String, Any] =
    Map()

  def selectEdgeDrawingCoordinates(graph: Graph, grid: List[List[Char]], nodeInfos: Map[String, Any]): Map[String, Any] =
    Map()

  def distanceBetweenCoordinates(coord1: Coordinate, coord2: Coordinate): Int =
    (math.max(coord1.x, coord2.x) - math.min(coord1.x, coord2.x)) +
      (math.max(coord1.y, coord2.y) - math.min(coord1.y, coord2.y))

  def drawEdges(grid: List[List[Char]], nodeInfos: Map[String, Any]): List[List[Char]] =
    List()

}

case class Coordinate(x: Int, y: Int)