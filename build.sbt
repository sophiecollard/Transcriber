name := "transliterator"

scalaVersion := "2.13.0"

libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "4.6.0" % "test")

parallelExecution in Test := false

scalacOptions in Test ++= Seq("-Yrangepos") // specs2

addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")
