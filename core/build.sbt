val LogbackVersion = "1.2.3"
val ScalaTestVersion = "3.0.5"
val ScalaCheckVersion = "1.13.5"
val CatsVersion = "1.0.1"
val DogsVersion = "0.6.10"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core"
).map(_ % CatsVersion)

libraryDependencies ++= Seq(
  "org.typelevel" %% "dogs-core"
).map(_ % DogsVersion)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % ScalaTestVersion % "test",
  "org.scalacheck" %% "scalacheck" % ScalaCheckVersion % "test"
)
