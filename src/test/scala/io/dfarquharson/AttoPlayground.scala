package io.dfarquharson

import atto._
import Atto._
import cats.implicits._
import atto.ParseResult.Done
import org.scalatest.FunSuite

class AttoPlayground extends FunSuite {
  // Reference: https://tpolecat.github.io/atto/docs/first-steps.html

  test("First Step") {
    assert((int parseOnly "123abc") == Done("abc", 123))
  }

  test("More simple ints") {
    assert((int parseOnly  "1ab23c") == Done("ab23c", 1))
  }

  test("First single letter") {
    assert((letter parse "ab12c3") == Done("b12c3", "a"))
  }

  test("Single Letter") {
    assert((letter parseOnly "abc") == Done("bc", "a"))
  }

  test("Many Letters") {
    println(many(letter).parse("abc").done)
  }

  test("Our Powers Combined!") {
    val res = (letter ~ digit).parse("a1")
    println(res)
  }

  test("Many!") {

  }

  test("My Custom Parser") {
    val s: String = "A=B=>C"
  }

}
