package io.dfarquharson

object Hello extends App {
  val p = new Person("David")
  println(s"Hello ${p.name}")
}

class Person(val name: String)