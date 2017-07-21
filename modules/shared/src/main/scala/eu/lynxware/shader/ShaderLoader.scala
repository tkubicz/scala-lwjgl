package eu.lynxware.shader

import java.nio.{ByteBuffer, ByteOrder}

import eu.lynxware.opengl.{GL, GLSLProgram, GLSLShader}

case class Shader(name: String, program: Any, uniforms: Map[String, Any], attributes: Map[String, Int])

abstract class ShaderLoader[ShaderHandleType, ProgramHandleType]()(implicit gl: GL[ShaderHandleType, ProgramHandleType]) {
  private val uniformRegexp = "(?:uniform)(\\s+[A-Za-z0-9_.-]*\\s+)([^;]+)".r
  private val attributeRegexp = "(?:attribute)(\\s+[A-Za-z0-9_.-]*\\s+)([^;]+)".r

  def loadFromSource(name: String, vertexShaderSource: String, fragmentShaderSource: String): Option[Shader] =
    init(name, vertexShaderSource, fragmentShaderSource)

  protected def init(name: String, vertexShaderSource: String, fragmentShaderSource: String): Option[Shader] = {
    val (vertexShader, framgnetShader) = createShaders()
  }

  protected def createShaders(): (GLSLShader[ShaderHandleType], GLSLShader[ShaderHandleType]) =
    (gl.createShader(gl.VertexShader), gl.createShader(gl.FragmentShader))

  protected def compileShader(shader: GLSLShader[ShaderHandleType], shaderSource: String): Boolean = {
    gl.shaderSource(shader, shaderSource)
    gl.compileShader(shader)

    // TODO: This probably can be shared
    val buffer = ByteBuffer.allocateDirect(4)
    buffer.order(ByteOrder.nativeOrder())
    val intBuffer = buffer.asIntBuffer()
    gl.getShaderiv(shader, gl.CompileStatus, intBuffer)

    if (intBuffer.get(0) != 0) {
      println("Shader compilation failed: " + gl.getShaderInfoLog(shader))
      gl.deleteShader(shader)
      false
    } else true
  }

  protected def linkShaders(vertexShader: GLSLShader[ShaderHandleType], fragmentShader: GLSLShader[ShaderHandleType]): Option[GLSLProgram[ProgramHandleType]] = {
    val program = gl.createProgram()
    gl.attachShader(program, vertexShader)
    gl.attachShader(program, fragmentShader)
    gl.linkProgram(program)

    val buffer = ByteBuffer.allocateDirect(4)
    buffer.order(ByteOrder.nativeOrder())
    val intBuffer = buffer.asIntBuffer()
    gl.getProgramiv(program, gl.LinkStatus, intBuffer)

    if (intBuffer.get(0) != 0) {
      println("Could not link shaders into a program")
      gl.deleteProgram(program)
      None
    } else Some(program)
  }
}
