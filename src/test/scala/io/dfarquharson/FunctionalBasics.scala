package io.dfarquharson

import org.scalatest.FunSuite

class FunctionalBasics extends FunSuite {

  test("map") {
    assert(List(1, 2, 3, 4).map(x => x + 1) == List(2, 3, 4, 5))
    assert(List(1, 2, 3, 4).map(_ + 1) == List(2, 3, 4, 5))
  }

  test("filter") {
    assert(List(1, 2, 3, 4).filter(x => x % 2 == 0) == List(2, 4))
    assert(List(1, 2, 3, 4).filter(_ % 2 == 0) == List(2, 4))
  }

  test("fold/reduce") {
    assert(List(1, 2, 3, 4).foldLeft(0)(_ + _) == 10)
    assert(List(1, 2, 3, 4).foldRight(0)(_ + _) == 10)
    assert(List(1, 2, 3, 4).sum == 10)
  }

  test("just printing things") {
    List("Hello", "There", "Human") foreach println
  }

  test("numbers") {
    assert(1 == 1)
    assert(1 != 2)
  }

  test("booleans") {
    assert(true == true)
    assert(false == false)
    assert(true != false)
  }

  test("Partition") {
    val (evens, odds) = List(1, 2, 3, 4).partition(_ % 2 == 0)
    assert(evens == List(2, 4))
    assert(odds == List(1, 3))
  }

}
