package io.dfarquharson

import cats.Monoid
import cats.implicits._

trait GrasciiParse {
  def parse(lines: List[String]): Graph

  def parseLine(line: String): Graph
}

object GrasciiParse {

  import GraphMonoid._ // why is this necessary?
  def parse(lines: List[String]): Graph = {
    lines.map(parseLine).combineAll
  }

  def parseLine(s: String): Graph = {
    val pattern = "(.*)=(.*)=>(.*)".r
    val pattern(nodeA, edge, nodeB) = s
    Graph(List(Node(nodeA), Node(nodeB)),
      List(Edge(Node(nodeA), Node(nodeB), edge)))
  }
}

object GraphMonoid {
  implicit def graphMonoid: Monoid[Graph] = new Monoid[Graph] {
    def empty: Graph = Graph(List(), List())

    def combine(g1: Graph, g2: Graph): Graph =
      Graph(
        (g1.nodes ++ g2.nodes).distinct,
        (g1.edges ++ g2.edges).distinct
      )
  }
}

case class Graph(nodes: List[Node], edges: List[Edge])

case class Node(name: String)

case class Edge(from: Node, to: Node, kind: String)