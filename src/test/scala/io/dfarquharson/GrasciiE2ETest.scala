package io.dfarquharson

import org.scalatest.{FunSuite, Ignore}

@Ignore
class GrasciiE2ETest extends FunSuite {

  test("E2E Example 1") {
    assert(
      Grascii.convert(List(
        "A=0=>B",
        "B=1=>C"
      )) ==
        """+---+
          || A 00
          |+---+0
          |+---+0
          || B 00
          ||   11
          |+---+1
          |+---+1
          || C 11
          |+---+
          |""".stripMargin)
  }

  test("E2E Example 2") {
    assert(
      Grascii.convert(List(
        "A=0=>B",
        "A=1=>C"
      )) ==
        """+---+
          || A 111
          ||   001
          |+---+01
          |+---+01
          || B 001
          |+---+11
          |+---+1
          || C 11
          |+---+
          |""".stripMargin
    )
  }

  test("E2E Example 3") {
    assert(
      Grascii.convert(List(
        "A=0=>B",
        "B=1=>C",
        "A=2=>C"
      )) ==
        """+---+
          || A 222
          ||   002
          |+---+02
          |+---+02
          || B 002
          ||   112
          |+---+12
          |+---+12
          || C 112
          ||   222
          |+---+
          |""".stripMargin
    )
  }

  test("E2E Example 4") {
    assert(
      Grascii.convert(List(
        "A=0=>B",
        "B=1=>C",
        "C=2=>D",
        "D=3=>E",
        "A=4=>C",
        "A=5=>D"
      )) ==
        """+---+
          || A 5555
          ||   4445
          ||   0045
          |+---+045
          |+---+045
          || B 0045
          ||   1145
          |+---+145
          |+---+145
          || C 1145
          ||   4445
          ||   2255
          |+---+25
          |+---+25
          || D 225
          ||   555
          ||   33
          |+---+3
          |+---+3
          || E 33
          |+---+
          |""".stripMargin
    )
  }

}
