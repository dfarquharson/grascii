package io.dfarquharson

trait GrasciiDraw {

  // TODO:
  //  - [ ] max 80 character width (make that a parameter?)
  //  - [ ] as vertical as needed, but minimize vertical space usage while letting it "breathe"
  //    - [ ] parameterize what it means to "let it breathe"
  def draw(graph: Graph): String

  def drawOut(graph: Graph): Unit // TODO: better value for IO() in scala?

}

// TODO:
//  - [x] wrap long node names (what parameters?)
//  - [ ] put ascii boxes around node names
//  - [ ] arrows between boxes
//  - [ ] finite perimeter
//  - [ ] finite "breathing room" buffer space to make things "readable" (what parameters? play with these and see what looks good/cool)

object WordWrap {
  def wordWrap(s: String, maxLength: Int = 80): String = {
    // collect lines in a List[String], with each line being < maxLength
    // then rejoin lines into a string that will print prettily
    val shorterLines: List[String] = s.split(" ")
      .foldLeft(List(""))(((a, b) =>
        if ((a.last.length() + (" " + b).length()) < maxLength) {
          a.init ++ List((a.last + (" " + b)).trim())
        } else {
          a ++ List(b)
        }): (List[String], String) => List[String])
    shorterLines.foldLeft("")(_ + "\n" + _).trim()
  }
}