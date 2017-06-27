import sbt._
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

object Dependencies {

  private val appCompat = "com.android.support" % "appcompat-v7" % "23.+"
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

  val htmlDependencies: Seq[ModuleID] = Seq(scalajsDom, scalajsJQuery, scalaTags)
  val desktopDependencies: Seq[ModuleID] = Seq() ++ lwjgl
  val androidDependencies: Seq[ModuleID] = Seq(appCompat)
}
