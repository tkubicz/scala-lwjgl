package eu.lynxware

import eu.lynxware.opengl.WebGLBinding
import eu.lynxware.playground.CreateShader
import org.scalajs.dom
import org.scalajs.dom.html.Canvas
import org.scalajs.dom.raw.{HTMLElement, WebGLRenderingContext}
import slogging._

object HtmlMain extends LazyLogging {

  LoggerConfig.factory = PrintLoggerFactory()
  LoggerConfig.level = LogLevel.DEBUG

  def main(args: Array[String]): Unit = {

    logger.info("Hello Engine, nice to meet you!")
    val canvas = createCanvas()
    val gl = initWebGlContext(canvas)

    logger.debug("Creating WebGLBinding")
    val binding = new WebGLBinding(gl)
    new CreateShader(binding)

    logger.debug("Setting up blue colour")
    gl.viewport(0, 0, 1024, 768)
    gl.clearColor(0.0, 0.5, 1.0, 1.0)
    gl.clear(WebGLRenderingContext.COLOR_BUFFER_BIT | WebGLRenderingContext.DEPTH_BUFFER_BIT)
  }

  def createCanvas(width: Int = 1024, height: Int = 768, elementToAppend: HTMLElement = dom.document.body): Canvas = {
    val can: Canvas = dom.document.createElement("canvas").asInstanceOf[Canvas]
    can.id = "webgl-canvas"
    elementToAppend.appendChild(can)
    can.width = width
    can.height = height
    can
  }

  def initWebGlContext(canvas: Canvas, name: String = "webgl"): WebGLRenderingContext =
    canvas.getContext(name).asInstanceOf[WebGLRenderingContext]
}
