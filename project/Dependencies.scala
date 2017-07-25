import sbt._
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

object Dependencies {

  // common depndencies
  private val slf4jApi = "org.slf4j" % "slf4j-api" % "1.7.+"

  // desktop depndencies
  private val logback = "ch.qos.logback" % "logback-classic" % "1.2.3"
  private val slogging = "biz.enef" %% "slogging" % "0.5.3"
  private val sloggingSlf4j = "biz.enef" %% "slogging-slf4j" % "0.5.3"

  // android depndencies
  private val logbackAndroidCore = "com.github.tony19" % "logback-android-core" % "1.1.1-6"
  private val logbackAndroidClassic = "com.github.tony19" % "logback-android-classic" % "1.1.1-6" exclude("com.google.android", "android")
  private val appCompat = "com.android.support" % "appcompat-v7" % "23.+"

  // html dependencies
  private val sloggingJs = "biz.enef" %%%! "slogging" % "0.5.3"
  private val scalajsDom = "org.scala-js" %%%! "scalajs-dom" % "0.9.1"
  private val scalajsJQuery = "be.doeraene" %%%! "scalajs-jquery" % "0.9.1"
  private val scalaTags = "com.lihaoyi" %%%! "scalatags" % "0.6.3"

  lazy val printOsInfo = taskKey[Unit]("Task that prints OS info")

  printOsInfo := {
    println("OS: " + OS.current.name.toString)
  }

  val lwjglVersion = "3.1.0"
  val lwjglNatives: String = {
    OS.current.name match {
      case OS.Name.Windows => "natives-windows"
      case OS.Name.Linux => "natives-linux"
      case OS.Name.Mac => "natives-macos"
    }
  }

  private val lwjgl = Seq(
    "org.lwjgl" % "lwjgl" % lwjglVersion % "compile",
    "org.lwjgl" % "lwjgl-bgfx" % lwjglVersion % "compile",
    "org.lwjgl" % "lwjgl-egl" % lwjglVersion,
    "org.lwjgl" % "lwjgl-glfw" % lwjglVersion % "compile",
    "org.lwjgl" % "lwjgl-jawt" % lwjglVersion,
    "org.lwjgl" % "lwjgl-jemalloc" % lwjglVersion % "compile",
    "org.lwjgl" % "lwjgl-lmdb" % lwjglVersion % "compile",
    "org.lwjgl" % "lwjgl-nanovg" % lwjglVersion % "compile",
    "org.lwjgl" % "lwjgl-nfd" % lwjglVersion % "compile",
    "org.lwjgl" % "lwjgl-nuklear" % lwjglVersion % "compile",
    "org.lwjgl" % "lwjgl-openal" % lwjglVersion % "compile",
    "org.lwjgl" % "lwjgl-opencl" % lwjglVersion,
    "org.lwjgl" % "lwjgl-opengl" % lwjglVersion,
    "org.lwjgl" % "lwjgl-opengles" % lwjglVersion,
    "org.lwjgl" % "lwjgl-par" % lwjglVersion % "compile",
    "org.lwjgl" % "lwjgl-sse" % lwjglVersion % "compile",
    "org.lwjgl" % "lwjgl-stb" % lwjglVersion % "compile",
    "org.lwjgl" % "lwjgl-tinyfd" % lwjglVersion % "compile",
    "org.lwjgl" % "lwjgl-vulkan" % lwjglVersion,
    "org.lwjgl" % "lwjgl-xxhash" % lwjglVersion % "compile",

    "org.lwjgl" % "lwjgl" % lwjglVersion % "runtime" classifier lwjglNatives,
    "org.lwjgl" % "lwjgl-bgfx" % lwjglVersion % "runtime" classifier lwjglNatives,
    "org.lwjgl" % "lwjgl-glfw" % lwjglVersion % "runtime" classifier lwjglNatives,
    "org.lwjgl" % "lwjgl-jemalloc" % lwjglVersion % "runtime" classifier lwjglNatives,
    "org.lwjgl" % "lwjgl-lmdb" % lwjglVersion % "runtime" classifier lwjglNatives,
    "org.lwjgl" % "lwjgl-nanovg" % lwjglVersion % "runtime" classifier lwjglNatives,
    "org.lwjgl" % "lwjgl-nfd" % lwjglVersion % "runtime" classifier lwjglNatives,
    "org.lwjgl" % "lwjgl-nuklear" % lwjglVersion % "runtime" classifier lwjglNatives,
    "org.lwjgl" % "lwjgl-openal" % lwjglVersion % "runtime" classifier lwjglNatives,
    "org.lwjgl" % "lwjgl-par" % lwjglVersion % "runtime" classifier lwjglNatives,
    "org.lwjgl" % "lwjgl-sse" % lwjglVersion % "runtime" classifier lwjglNatives,
    "org.lwjgl" % "lwjgl-stb" % lwjglVersion % "runtime" classifier lwjglNatives,
    "org.lwjgl" % "lwjgl-tinyfd" % lwjglVersion % "runtime" classifier lwjglNatives,
    "org.lwjgl" % "lwjgl-xxhash" % lwjglVersion % "runtime" classifier lwjglNatives
  )

  val htmlDependencies: Seq[ModuleID] = Seq(sloggingJs, scalajsDom, scalajsJQuery, scalaTags)
  val desktopDependencies: Seq[ModuleID] = Seq(sloggingSlf4j, logback) ++ lwjgl
  val androidDependencies: Seq[ModuleID] = Seq(logbackAndroidCore, logbackAndroidClassic, appCompat)

  val sharedDependencies: Seq[ModuleID] = Seq(slf4jApi, slogging)
  val sharedDependenciesJs: Seq[ModuleID] = Seq(slf4jApi, sloggingJs)
}
