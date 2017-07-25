package eu.lynxware.shader

import java.nio.{ByteBuffer, ByteOrder, IntBuffer}

import eu.lynxware.opengl.{GL, GLSLProgram, GLSLShader}
import slogging.LazyLogging

import scala.util.matching.Regex

abstract class ShaderLoader[ShaderHandleType, ProgramHandleType, UniformLocationType, AttribLocationType]
()(implicit gl: GL[ShaderHandleType, ProgramHandleType, UniformLocationType, AttribLocationType]) extends LazyLogging {

  private val uniformRegexp = "(?:uniform)(\\s+[A-Za-z0-9_.-]*\\s+)([^;]+)".r
  private val attributeRegexp = "(?:attribute)(\\s+[A-Za-z0-9_.-]*\\s+)([^;]+)".r

  def loadFromSource(name: String, vertexShaderSource: String, fragmentShaderSource: String): Option[GLSLProgram[ProgramHandleType, UniformLocationType, AttribLocationType]] =
    init(name, vertexShaderSource, fragmentShaderSource)

  protected def init(name: String, vertexShaderSource: String, fragmentShaderSource: String): Option[GLSLProgram[ProgramHandleType, UniformLocationType, AttribLocationType]] = {
    val (vertexShader, fragmentShader) = createShaders()
    if (!compileShader(vertexShader, vertexShaderSource) || !compileShader(fragmentShader, fragmentShaderSource)) None
    else {
      linkShaders(vertexShader, fragmentShader).map(p =>
        p
        /*GLSLPro(name, p,
          getUniformLocations(p, findUniformNames(vertexShaderSource, fragmentShaderSource)),
          getAttribLocations(p, findAttributeNames(vertexShaderSource, fragmentShaderSource))
        )*/
      ).orElse(None)
    }
  }

  protected def createShaders(): (GLSLShader[ShaderHandleType], GLSLShader[ShaderHandleType]) =
    (gl.createShader(gl.VertexShader), gl.createShader(gl.FragmentShader))

  protected def compileShader(shader: GLSLShader[ShaderHandleType], shaderSource: String): Boolean = {
    gl.shaderSource(shader, shaderSource)
    gl.compileShader(shader)

    val buffer = createIntBuffer()
    gl.getShaderiv(shader, gl.CompileStatus, buffer)

    if (buffer.get(0) != 0) {
      logger.error("Shader compilation failed: {}", gl.getShaderInfoLog(shader))
      gl.deleteShader(shader)
      false
    } else true
  }

  protected def linkShaders(vertexShader: GLSLShader[ShaderHandleType], fragmentShader: GLSLShader[ShaderHandleType]):
  Option[GLSLProgram[ProgramHandleType, UniformLocationType, AttribLocationType]] = {
    val program = gl.createProgram()
    gl.attachShader(program, vertexShader)
    gl.attachShader(program, fragmentShader)
    gl.linkProgram(program)

    val buffer = createIntBuffer()
    gl.getProgramiv(program, gl.LinkStatus, buffer)

    if (buffer.get(0) != 0) {
      logger.error("Could not link shaders into a program: {}", gl.getProgramInfoLog(program))
      gl.deleteProgram(program)
      None
    } else Some(program)
  }

  protected def findUniformNames(vertexShaderSource: String, fragmentShaderSource: String): Seq[String] =
    findNames(uniformRegexp, vertexShaderSource, fragmentShaderSource)

  protected def findAttributeNames(vertexShaderSource: String, fragmentShaderSource: String): Seq[String] =
    findNames(attributeRegexp, vertexShaderSource, fragmentShaderSource)

  protected def findNames(regex: Regex, vertexShaderSource: String, fragmentShaderSource: String): Seq[String] =
    (regex.findAllIn(vertexShaderSource).matchData.map(_.group(2)) ++ regex.findAllIn(fragmentShaderSource).matchData.map(_.group(2))).toSeq

  protected def getUniformLocations(program: GLSLProgram[ProgramHandleType, UniformLocationType, AttribLocationType], uniformNames: Seq[String]): Map[String, UniformLocationType] =
    uniformNames.map(name => name -> gl.getUniformLocation(program, name))(collection.breakOut)

  protected def getAttribLocations(program: GLSLProgram[ProgramHandleType, UniformLocationType, AttribLocationType], attribNames: Seq[String]): Map[String, AttribLocationType] =
    attribNames.map(name => name -> gl.getAttribLocation(program, name))(collection.breakOut)

  protected def createIntBuffer(size: Int = 4): IntBuffer = {
    val buffer = ByteBuffer.allocateDirect(4)
    buffer.order(ByteOrder.nativeOrder())
    buffer.asIntBuffer()
  }
}
