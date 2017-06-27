package eu.lynxware

import eu.lynxware.camera.Projection
import org.scalajs.dom
import org.scalajs.dom.experimental.webgl.extensions.{ANGLEInstancedArrays, OESVertexArrayObject}
import org.scalajs.dom.raw.{HTMLElement, WebGLRenderingContext}
import WebGLApplication._

import scala.concurrent.{ExecutionContextExecutor, Future}

abstract class Simulation extends WebGLApplication {
  implicit var gl: WebGLRenderingContext = _
  implicit var vaExt: OESVertexArrayObject = _
  implicit var iaExt: ANGLEInstancedArrays = _
  implicit val ec: ExecutionContextExecutor = scala.concurrent.ExecutionContext.Implicits.global

  val width = 1024.0
  val height = 768.0
  val projection = Projection(50.0, width, height, 0.1, 1000.0)

  override def init(): Seq[Future[Any]] = {
    canvas = createCanvas(width.toInt, height.toInt, dom.document.getElementById("page").asInstanceOf[HTMLElement])
    gl = initWebGlContext(canvas)

    val possibleVaoExt = initVaoExtension(gl)
    val possibleIaExt = initInstancedArraysExtension(gl)
    if (possibleVaoExt.isEmpty || possibleIaExt.isEmpty) {
      return Seq(Future.failed(new Exception("Not all necessary extensions found")))
    }

    vaExt = possibleVaoExt.get
    iaExt = possibleIaExt.get

    gl.viewport(0, 0, projection.width, projection.height)
    gl.clearColor(0.036f, 0.035f, 0.20f, 1.0f)
    gl.clear(WebGLRenderingContext.COLOR_BUFFER_BIT | WebGLRenderingContext.DEPTH_BUFFER_BIT)

    Seq(Future { true })
  }

  override def postInit(any: Seq[Any]): Unit = {
    gl.enable(WebGLRenderingContext.DEPTH_TEST)

  }
}
