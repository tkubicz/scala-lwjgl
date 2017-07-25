package eu.lynxware.opengl

import java.nio.IntBuffer

import eu.lynxware.opengl.Types.{AttribLocationType, ProgramHandleType, ShaderHandleType, UniformLocationType}
import org.scalajs.dom.raw.{WebGLProgram, WebGLRenderingContext, WebGLShader, WebGLUniformLocation}

object Types {
  type ShaderHandleType = WebGLShader
  type ProgramHandleType = WebGLProgram
  type UniformLocationType = WebGLUniformLocation
  type AttribLocationType = Int
}

case class Shader(handle: ShaderHandleType) extends GLSLShader[ShaderHandleType]

case class Program(handle: ProgramHandleType) extends GLSLProgram[ProgramHandleType]

class WebGLBinding(gl: WebGLRenderingContext) extends GL[ShaderHandleType, ProgramHandleType, UniformLocationType, AttribLocationType] {

  override val Texture2D: Int = WebGLRenderingContext.TEXTURE_2D
  override val PackAlignment: Int = WebGLRenderingContext.PACK_ALIGNMENT
  override val ColorBufferBit: Int = WebGLRenderingContext.COLOR_BUFFER_BIT
  override val VertexShader: Int = WebGLRenderingContext.VERTEX_SHADER
  override val FragmentShader: Int = WebGLRenderingContext.FRAGMENT_SHADER
  override val GeometryShader: Int = -1 //throw new Exception("Not supported in this version")
  override val CompileStatus: Int = WebGLRenderingContext.COMPILE_STATUS
  override val DeleteStatus: Int = WebGLRenderingContext.DELETE_STATUS
  override val LinkStatus: Int = WebGLRenderingContext.LINK_STATUS
  override val ValidateStatus: Int = WebGLRenderingContext.VALIDATE_STATUS

  override def clear(mask: Int): Unit = gl.clear(mask)

  override def clearColor(red: Float, green: Float, blue: Float, alpha: Float): Unit = gl.clearColor(red, green, blue, alpha)

  override def viewport(x: Int, y: Int, width: Int, height: Int): Unit = gl.viewport(x, y, width, height)

  override def createShader(shaderType: Int): GLSLShader[WebGLShader] = Shader(gl.createShader(shaderType))

  override def deleteShader(shader: GLSLShader[ShaderHandleType]): Unit = gl.deleteShader(shader.handle)

  override def compileShader(shader: GLSLShader[WebGLShader]): Unit = gl.compileShader(shader.handle)

  override def shaderSource(shader: GLSLShader[WebGLShader], source: String): Unit = gl.shaderSource(shader.handle, source)

  override def getShaderiv(shader: GLSLShader[WebGLShader], name: Int, params: IntBuffer): Unit = {
    val result = gl.getShaderParameter(shader.handle, name)
    name match {
      case DeleteStatus | CompileStatus =>
        params.put(if (result.asInstanceOf[Boolean]) 1 else 0)
      case _ =>
    }
  }

  override def getShaderInfoLog(shader: GLSLShader[WebGLShader]): String = gl.getShaderInfoLog(shader.handle)

  override def createProgram(): GLSLProgram[ProgramHandleType] = Program(gl.createProgram())

  override def deleteProgram(program: GLSLProgram[ProgramHandleType]): Unit = gl.deleteProgram(program.handle)

  override def attachShader(program: GLSLProgram[ProgramHandleType], shader: GLSLShader[ShaderHandleType]): Unit = gl.attachShader(program.handle, shader.handle)

  override def linkProgram(program: GLSLProgram[ProgramHandleType]): Unit = gl.linkProgram(program.handle)

  override def getProgramiv(program: GLSLProgram[ProgramHandleType], name: Int, params: IntBuffer): Unit = {
    val result = gl.getProgramParameter(program.handle, name)
    name match {
      case DeleteStatus | LinkStatus | ValidateStatus =>
        val intResult = if (result.asInstanceOf[Boolean]) 1 else 0
        params.put(intResult)
      case _ =>
    }
  }

  override def getProgramInfoLog(program: GLSLProgram[ProgramHandleType]): String = gl.getProgramInfoLog(program.handle)

  override def getUniformLocation(program: GLSLProgram[ProgramHandleType], name: String): UniformLocationType =
    gl.getUniformLocation(program.handle, name)

  override def getAttribLocation(program: GLSLProgram[ProgramHandleType], name: String): Int =
    gl.getAttribLocation(program.handle, name)
}
