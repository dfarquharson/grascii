package io.dfarquharson

import org.scalatest.FunSuite

class PersonTest extends FunSuite {

  test("The name is set correctly in constructor") {
    val p = new Person("Billy")
    assert(p.name == "Billy")
  }

  test("The name cannot be changed") {
    val p = new Person("Bob")
    assert(p.name == "Bob")
    //    p.name = "Jim"  // doesn't even compile because name is a val. Nailed it.
    assert(p.name == "Bob")
  }

}
