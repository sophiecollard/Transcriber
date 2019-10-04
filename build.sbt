name := "transliterator"

scalaVersion := "2.13.0"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "2.0.0",
  "org.specs2" %% "specs2-core" % "4.6.0" % "test"
)

parallelExecution in Test := false

scalacOptions in Test ++= Seq("-Yrangepos") // specs2

resolvers += Resolver.sonatypeRepo("releases") // required by kind-projector plugin

addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")
addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.10.3")
