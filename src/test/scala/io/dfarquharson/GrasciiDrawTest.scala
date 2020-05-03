package io.dfarquharson

import org.scalatest.FunSuite

class GrasciiDrawTest extends FunSuite {

  test("playground list concat") {
    val xs = List("first", "second", "third")
    println(xs.map(_.length()))
    println(xs.last)
    println(xs.last + " fourth")
    println(xs.init)
    println(xs.tail)
    println(xs.init ++ List(xs.last + " fourth"))
    println(List("").init)
    println(List("").last + " hello")
  }

  test("playground foldLeft") {
    val xs = List("first", "second", "third")
    val ys = xs.foldLeft(List(""))((a, b) => {
      if ((a.last.length() + (" " + b).length()) < 13) {
        a.init ++ List((a.last + (" " + b)).trim())
      } else {
        a ++ List(b)
      }
    })
    println("ys: " + ys.toString())
    assert(ys == List("first second", "third"))
  }

  test("Split a big string into shorter lines") {
    val s = "this is a relatively long string. Longer than you'd probably want one to be, especially if your goal is to easily read it on a single, fixed-width page. It should be wrapped onto shorter lines so it can be more easily read and prettily displayed."
    println("total length: " + s.length())
    println("even lines: " + s.length() / 80)
    println("remainder: " + s.length() % 80)
    println("words: " + s.split(' ').length)

    val xs = s.split(" ")

    // collect lines in a List[String], with each line being < 80 length
    val ys: List[String] = xs.foldLeft(List(""))(((a: List[String], b: String) =>
      if ((a.last.length() + (" " + b).length()) < 80) {
        a.init ++ List((a.last + (" " + b)).trim())
      } else {
        a ++ List(b)
      }): (List[String], String) => List[String])
    ys.foreach(x => println("line length: " + x.length()))
    ys.foreach(println)
    println(ys.foldLeft("")(_ + "\n" + _).trim())
    println(ys.foldLeft("")(_ + "\n" + _).trim().length())
  }

  test("Wrap a long word based on params") {

  }

  test("Draw two node, one edge graph") {

  }

  test("Draw two node, two edge graph") {

  }

  test("Draw two node, three edge graph") {

  }

  test("Draw three node, two edge graph") {

  }

}
