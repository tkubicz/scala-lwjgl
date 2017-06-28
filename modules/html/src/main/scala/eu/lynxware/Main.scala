package eu.lynxware

import org.scalajs.dom.raw.WebGLRenderingContext

import scala.scalajs.js.JSApp
import org.scalajs.dom.raw.WebGLRenderingContext._

class Main extends JSApp {
  override def main(gl: WebGLRenderingContext): Unit = {
    gl.activeTexture()
  }


}
