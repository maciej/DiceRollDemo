import Dependencies._

name := "DiceRollDemo"

version := "0.1"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.0" % "test"
) ++ metrics

javacOptions in Compile ++= Seq("-source", "1.7", "-target", "1.7")
