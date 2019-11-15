import sbt.Keys._
import sbt._

object Dependencies extends AutoPlugin {

  object autoImport {
    implicit final class DependenciesProject(val project: Project) extends AnyVal {
      def withDependencies: Project =
        project.settings(dependencySettings)
    }
  }

  private val dependencySettings: Seq[Def.Setting[_]] = {
    val catsVersion = "2.0.0"

    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core"   % catsVersion,
      "org.typelevel" %% "cats-effect" % catsVersion % Test,
      "co.fs2"        %% "fs2-core"    % "2.0.0"     % Test,
      "org.specs2"    %% "specs2-core" % "4.6.0"     % Test
    )
  }

}
