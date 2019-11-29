lazy val scala211 = "2.11.12"
lazy val scala212 = "2.12.10"
lazy val scala213 = "2.13.0"
lazy val supportedScalaVersions = List(scala211, scala212, scala213)

// Taken from https://github.com/circe/circe/blob/master/build.sbt
def priorTo213(scalaVersion: String): Boolean =
  CrossVersion.partialVersion(scalaVersion) match {
    case Some((2, minor)) if minor < 13 => true
    case _                              => false
  }

lazy val root = project
  .in(file("."))
  .aggregate(core)
  .settings(crossScalaVersions := Nil)
  .settings(publish / skip := true)

lazy val core = project
  .in(file("core"))
  .settings(crossScalaVersions := supportedScalaVersions)
  .settings(projectMetadataSettings)
  .settings(scalaSettings)
  .settings(scalacSettings)
  .settings(resolverSettings)
  .settings(testSettings)
  .withDependencies

lazy val projectMetadataSettings = Seq(
  name := "hangeul4s",
  organization := "com.github.sophiecollard",
  homepage := Some(url("https://github.com/sophiecollard")),
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/sophiecollard/hangeul4s"),
      "git@github.com:sophiecollard/hangeul4s.git"
    )
  ),
  developers := List(
    Developer(
      "sophiecollard",
      "Sophie Collard",
      "sophie<dot>collard<at>hotmail<dot>fr",
      url("https://github.com/sophiecollard")
    )
  ),
  licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")),
  publishMavenStyle := true
)

lazy val scalaSettings = Seq(
  scalaVersion := scala213
)

lazy val scalacSettings = Seq(
  scalacOptions ++= Seq(
    "-deprecation",
    "-feature",
    "-language:higherKinds",
    "-Xfatal-warnings"
  ) ++ {
    if (priorTo213(scalaVersion.value))
      Seq("-Ypartial-unification") // required by cats for Scala 2.11 and 2.12
    else
      Nil
  },
  addCompilerPlugin("org.typelevel" % "kind-projector" % "0.11.0" cross CrossVersion.full)
)

lazy val resolverSettings = Seq(
  resolvers += Resolver.sonatypeRepo("releases") // required by kind-projector plugin
)

lazy val testSettings = Seq(
  parallelExecution in Test := false,
  scalacOptions in Test ++= Seq("-Yrangepos") // required by specs2
)
