import sbt._
import Keys._
import sbtassembly.Plugin.AssemblyKeys

object TestBuild extends Build {

  def scalaVersionText = "2.9.2"

  lazy val root = Project("root", file("."), settings = projectSettings) aggregate(
      data)

  def projectSettings = Defaults.defaultSettings ++ Seq(
    organization := "test",
    version := "0.1-SNAPSHOT",
    scalaVersion := scalaVersionText,
    fork := true
  )

  def javaProjectSettings = projectSettings ++ Seq(
    javacOptions := Seq("-source", "1.7", "-target", "1.7"),
    javaOptions := Seq("-server", "-XX:MaxPermSize=192m", "-XX:ReservedCodeCacheSize=128m")
  )

  def scalaProjectSettings = javaProjectSettings ++ Seq(
    libraryDependencies ++= sharedScalaDependencies,
    compileOrder := CompileOrder.JavaThenScala,
    scalacOptions := Seq("-deprecation", "-optimize", "-unchecked" , "-encoding", "utf8")
  )

  lazy val data = Project("data", file("data"),
    settings = scalaProjectSettings
  )

  lazy val sharedScalaDependencies = Seq(
    scalaTest % "test" intransitive
  )

  def scalaTest = "org.scalatest" % "scalatest_2.9.2" % "2.0.M3"

}
