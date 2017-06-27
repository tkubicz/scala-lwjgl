package eu.lynxware

import eu.lynxware.math.Mat4

import scala.scalajs.js.JSApp

class Main extends JSApp {
  override def main(): Unit = {
    println("Hello world!")
    val mat = Mat4()
    println(mat)
  }
}
