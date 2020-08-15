package io.dfarquharson

trait Function[A, B] {
  def apply(a: A): B
}

trait Program[A, B] extends Function[A, B]

// A "pipline" is just a function too, right?
// It's just a thing that takes some thing of some input type and produces some thing of some output type
// Those things can have the same type, but they can have different types too if desired.
trait Pipeline[A, B] extends Function[A, B]

trait UpperCaseString extends Function[String, String] {
  def toUpperCase(s: String): String = s.toUpperCase()

  def apply(a: String): String = toUpperCase(a)
}

trait Repo {
  def makefile(): Makefile

  def dockerfile(): Dockerfile

  def readme(): Readme

  def tests(): Set[Test]
}

trait Makefile

trait Dockerfile

trait Readme

trait Test

trait MonoRepo {
  def components(): Set[Repo]
}

trait TGraph[A, B] {
  def nodes: Set[A]

  def edges: Set[B]
}

// TODO: how to enforce the directed acyclic nature of this at the type level?
trait TGraphDAG[A, B] extends TGraph[A, B]