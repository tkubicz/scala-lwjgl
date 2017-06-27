package eu.lynxware

import org.scalajs.dom
import org.scalajs.dom.experimental.webgl.extensions.{ANGLEInstancedArrays, OESVertexArrayObject}
import org.scalajs.dom.html.Canvas
import org.scalajs.dom.raw._
import org.scalajs.dom.{WheelEvent, document}

import scala.concurrent.Future
import scala.scalajs.js
import scala.util.{Failure, Success}

abstract class WebGLApplication {
  protected var lastFrameTime = 0.0
  protected var isPaused: Boolean = false
  protected var canvas: Canvas = null

  import scala.concurrent.ExecutionContext.Implicits.global

  protected def init(): Seq[Future[Any]]

  protected def postInit(any: Seq[Any]): Unit

  protected def prepare(dt: Double): Unit

  protected def render(): Unit

  def startApp: Unit = {
    val initResult = init()
    Future.sequence(initResult) onComplete {
      case Success(r) =>
        if (canvas == null) {
          ErrorHandling.logMessage("WebGL canvas is not initialized")
          return
        }

        println("Starting the application")

        postInit(r)
        setupInputHandlers()
        eventLoop(0)

      case Failure(e) => ErrorHandling.logMessage("Could not start the application: " + e.getMessage)
    }
  }

  def pause: Unit = isPaused = true

  def unpause: Unit = isPaused = false

  def keyDown(event: KeyboardEvent): Unit = {}

  def keyUp(event: KeyboardEvent): Unit = {}

  def mouseMove(event: MouseEvent): Unit = {}

  def mouseDown(event: MouseEvent): Unit = {}

  def mouseUp(event: MouseEvent): Unit = {}

  def mouseWheel(deltaY: Double): Unit = {}

  protected def eventLoop(currentTime: Double): Unit = {
    val deltaTime = currentTime - lastFrameTime
    lastFrameTime = currentTime

    //def g: () => Unit = () => {}

    if (!isPaused) {
      dom.window.requestAnimationFrame(eventLoop _)
      //dom.window.setTimeout(g, 1000 / 60)
      prepare(deltaTime)
    }

    render()
  }

  protected def setupInputHandlers(): Unit = {
    document.onkeydown = keyDown _
    document.onkeyup = keyUp _
    document.onmousemove = mouseMove _
    document.onmousedown = mouseDown _
    document.onmouseup = mouseUp _

    canvas.onmousewheel = handleMouseEvent _ // For chrome
    canvas.addEventListener("DOMMouseScroll", handleFirefoxMouseEvent _) // For firefox

    // Disable right-click browser context menu on canvas
    canvas.addEventListener("contextmenu", (event: Event) => event.preventDefault())
  }

  protected def handleFirefoxMouseEvent(e: WheelEvent): Unit = {
    e.preventDefault()
    var delta = 0
    val wheel = e.asInstanceOf[js.Dynamic]
    if (!js.isUndefined(wheel.wheelDelta)) {
      // WebKit / Opera / IE ?
      delta = wheel.wheelDelta.asInstanceOf[Int]
    }
    else if (!js.isUndefined(wheel.detail)) {
      // Firefox
      delta = e.detail
    }
    mouseWheel(delta)
  }

  protected def handleMouseEvent(e: WheelEvent): Unit = {
    e.preventDefault()
    mouseWheel(e.deltaY)
  }
}

object WebGLApplication {

  def createCanvas(width: Int = 1024, height: Int = 768, elementToApped: HTMLElement = document.body): Canvas = {
    val can: Canvas = document.createElement("canvas").asInstanceOf[Canvas]
    can.id = "galaxy-canvas"
    elementToApped.appendChild(can)
    can.width = width
    can.height = height
    can
  }

  def initWebGlContext(canvas: Canvas, name: String = "webgl"): WebGLRenderingContext =
    canvas.getContext(name).asInstanceOf[WebGLRenderingContext]

  def initVaoExtension(gl: WebGLRenderingContext): Option[OESVertexArrayObject] = {
    val extensionName = "OES_vertex_array_object"
    loadExtension[OESVertexArrayObject](extensionName)(gl)
  }

  def initInstancedArraysExtension(gl: WebGLRenderingContext): Option[ANGLEInstancedArrays] = {
    val extensionName = "ANGLE_instanced_arrays"
    loadExtension[ANGLEInstancedArrays](extensionName)(gl)
  }

  protected def loadExtension[T](extensionName: String)(implicit gl: WebGLRenderingContext): Option[T] = {
    val extension = gl.getExtension(extensionName)
    if (extension == null) {
      ErrorHandling.logMessage(extensionName + " extension is not supported")
      return None
    }
    Option(extension.asInstanceOf[T])
  }
}
