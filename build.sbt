addCommandAlias("fm", "all compile:scalafmt test:scalafmt")
addCommandAlias("cc", "all clean compile")
addCommandAlias("c", "compile")
addCommandAlias("r", "run")
addCommandAlias("t", "test")
addCommandAlias("to", "testOnly")
addCommandAlias("ps", "projects")
addCommandAlias("p", "project")



lazy val global = project
  .in(file("."))
  .settings(
    name:="functional programming in scala take 2",
    settings)
  .aggregate(chapter2, chapter3)

lazy val chapter2 = project
  .settings(
    name := "chapter2",
    settings
  )
lazy val chapter3 = project
  .settings(
    name := "chapter3",
    settings
  )

lazy val settings = Seq(
  organization := "org.xaos",
  scalaVersion := "2.13.2",
  version := "0.1.0-SNAPSHOT",
  scalacOptions ++= scalacSettings,
  resolvers ++= resolversSettings,
  libraryDependencies ++= libsSettings,
  testFrameworks += new TestFramework("minitest.runner.Framework"),
)

lazy val scalacSettings = Seq(
  "-encoding",
  "UTF-8",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-explaintypes",
  "-opt-warnings",
  "-language:existentials",
  "-language:higherKinds",
  "-opt:l:inline",
  "-opt-inline-from:<source>",
  "-Yrangepos",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Ywarn-extra-implicit",
  "-Xsource:2.13",
  "-Xlint:_,-type-parameter-shadow,-unused",
  "-Xfatal-warnings"
)

lazy val resolversSettings = Seq(
  Resolver.sonatypeRepo("public"),
  Resolver.sonatypeRepo("snapshots"),
  Resolver.sonatypeRepo("releases")
)

lazy val Http4sVersion  = "0.20.1"
lazy val LogbackVersion = "1.2.3"
lazy val CirceVersion   = "0.11.1"

lazy val libsSettings = Seq(
  "io.monix"       %% "minitest"            % "2.8.2" % Test
)