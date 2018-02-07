resolvers += Resolver.sonatypeRepo("releases")

addCommandAlias("build", ";test:compile")
addCommandAlias("rebuild", ";reload;test:compile")
addCommandAlias("retest", ";reload;test:test")

libraryDependencies +=
  compilerPlugin("org.spire-math" %% "kind-projector" % "0.9.5")

organization := "party.functor"
name := "adt"
version := "0.0.1-SNAPSHOT"
scalaVersion in ThisBuild := "2.12.4"

scalafmtOnCompile := true

lazy val core = project
  .enablePlugins(TutPlugin)
lazy val root = project
  .in(file("."))
  .aggregate(core)
  .enablePlugins(TutPlugin)

// Turn on/off nagging compiler warnings from sbt-tpolecat, for debugging purposes:
scalacOptions.in(core, Compile) ~= filterConsoleScalacOptions
