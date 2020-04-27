package io.dfarquharson

import fastparse.NoWhitespace._
import fastparse._
import org.scalatest.FunSuite

class FastParsePlayground extends FunSuite {

  test("Hello FastParse!") {
    def eval(tree: (Int, Seq[(String, Int)])): Int = ???

    def number[_: P] = P(CharIn("0-9").rep(1).!.map(_.toInt))

    def parens[_: P] = P("(" ~/ addSub ~ ")")

    def factor[_: P] = P(number | parens)

    def divMul[_: P]: P[Int] = P(factor ~ (CharIn("*/").! ~/ factor).rep).map(eval)

    def addSub[_: P]: P[Int] = P(divMul ~ (CharIn("+\\-").! ~/ divMul).rep).map(eval)

    def expr[_: P]: P[Int] = P(addSub ~ End)
  }

  test("One Line Grascii Success") {

  }

  test("One Line Grascii Failure") {

  }

  test("Multi Line Grascii Success") {

  }

  test("Multi Line Grascii Failure") {

  }

  test("Multi Line Grascii Some Success Some Failure (Net Failure)") {

  }

}
