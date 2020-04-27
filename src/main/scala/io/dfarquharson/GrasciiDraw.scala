package io.dfarquharson

trait GrasciiDraw {

  // TODO:
  //  - [ ] max 80 character width (make that a parameter?)
  //  - [ ] as vertical as needed, but minimize vertical space usage while letting it "breathe"
  //    - [ ] parameterize what it means to "let it breathe"
  def draw(graph: Graph): String

  def drawOut(graph: Graph): Unit // TODO: better value for IO() in scala?

}
