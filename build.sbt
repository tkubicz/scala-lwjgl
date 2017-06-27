name := "lwjgl_test"
version in ThisBuild := "0.0.1-SNAPSHOT"
scalaVersion in ThisBuild := "2.11.8"
javacOptions in Compile ++= Seq("-source", "1.7", "-target", "1.7")

lazy val sharedJvm = shared.jvm
lazy val sharedJs = shared.js

lazy val android = Project(id = "android", base = file("modules/android"))
    .enablePlugins(AndroidApp)
    .settings(
      libraryDependencies ++= Dependencies.androidDependencies,
      platformTarget := "android-23"
    )
    .dependsOn(sharedJvm)

lazy val html = Project(id = "html", base = file("modules/html"))
    .enablePlugins(ScalaJSPlugin)
    .settings(
      libraryDependencies ++= Dependencies.htmlDependencies
      //persistLauncher in Compile := true,
      //persistLauncher in Test := false
    )
    .dependsOn(sharedJs)

lazy val desktop = Project(id = "desktop", base = file("modules/desktop"))
    .settings(
      libraryDependencies ++= Dependencies.desktopDependencies,
      fork in run := true
      //javaOptions in run += "-XstartOnFirstThread"
    )
    .dependsOn(sharedJvm)

lazy val shared = crossProject.crossType(CrossType.Pure) in file("modules/shared")
