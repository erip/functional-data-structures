scalaVersion in ThisBuild := "2.12.6"

val commonSettings = Seq(
  name := "functional-data-structures",
  version := "0.1"
)

lazy val `functional-data-structures` = (project in file("."))
  .settings(commonSettings: _*)



