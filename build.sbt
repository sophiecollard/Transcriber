name := "transliterator"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "4.6.0" % "test")

scalacOptions in Test ++= Seq("-Yrangepos") // specs2
