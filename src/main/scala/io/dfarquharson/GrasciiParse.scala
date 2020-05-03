package io.dfarquharson

import cats.Monoid
import cats.implicits._
import io.dfarquharson.GraphMonoid._

trait GrasciiValid {
  def valid(lines: List[String]): Boolean
}

object GrasciiValid extends GrasciiValid {
  override def valid(lines: List[String]): Boolean =
    lines.forall(validLine)

  private def validLine(s: String): Boolean =
    s.contains("=") &&
      s.contains("=>") &&
      s.split("=").length == 3 &&
      s.split("=>").length == 2
}

trait GrasciiParse {
  def parse(lines: List[String]): Graph
}

object GrasciiParse extends GrasciiParse {
  override def parse(lines: List[String]): Graph = {
    lines.map(parseLine).combineAll
  }

  private def parseLine(s: String): Graph = {
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