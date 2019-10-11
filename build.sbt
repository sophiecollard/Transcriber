lazy val root = project
  .in(file("."))
  .settings(metadataSettings)
  .settings(scalaSettings)
  .settings(resolverSettings)
  .settings(pluginSettings)
  .settings(testSettings)
  .withDependencies

lazy val metadataSettings = Seq(
  organization := "com.github.sophiecollard",
  organizationHomepage := Some(url("https://github.com/sophiecollard")),
  name := "hangeul4s"
)

lazy val pluginSettings = Seq(
  addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1"),
  addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.10.3")
)

lazy val resolverSettings = Seq(
  resolvers += Resolver.sonatypeRepo("releases") // required by kind-projector plugin
)

lazy val scalaSettings = Seq(
  scalaVersion := "2.13.0"
)

lazy val testSettings = Seq(
  parallelExecution in Test := false,
  scalacOptions in Test ++= Seq("-Yrangepos") // required by specs2
)
