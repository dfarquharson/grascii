name := "grascii-scala"
version := "0.1"
scalaVersion := "2.13.1"
sbtVersion := "1.3.10"

libraryDependencies ++= Seq(
  "org.tpolecat" %% "atto-core" % "0.7.0",
  "org.tpolecat" %% "atto-refined" % "0.7.0",
  "org.typelevel" %% "cats-core" % "2.1.1",
  "org.scalactic" %% "scalactic" % "3.0.8",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test"
)