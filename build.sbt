name := "graphics-engine"
version in ThisBuild := "0.0.1-SNAPSHOT"
scalaVersion in ThisBuild := "2.11.11"
javacOptions in ThisBuild ++= Seq("-source", "1.7", "-target", "1.7")

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
  .enablePlugins(ScalaJSPlugin, WorkbenchPlugin)
  .settings(
    libraryDependencies ++= Dependencies.htmlDependencies,
    scalaJSUseMainModuleInitializer := true
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

lazy val shared = crossProject.crossType(CrossType.Pure).in(file("modules/shared"))
  .jsSettings(
    libraryDependencies ++= Dependencies.sharedDependenciesJs
  )
  .jvmSettings(
    libraryDependencies ++= Dependencies.sharedDependencies,
    exportJars := true
  )
