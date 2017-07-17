package eu.lynxware.opengl

import java.nio.IntBuffer

import eu.lynxware.opengl.Types.ShaderHandleType
import org.scalajs.dom.raw.{WebGLRenderingContext, WebGLShader}

object Types {
  type ShaderHandleType = WebGLShader
}

case class Shader(handle: ShaderHandleType) extends GLSLShader[ShaderHandleType]

class WebGLBinding(gl: WebGLRenderingContext) extends GL[ShaderHandleType] {
  override val Texture2D: Int = WebGLRenderingContext.TEXTURE_2D
  override val PackAlignment: Int = WebGLRenderingContext.PACK_ALIGNMENT
  override val ColorBufferBit: Int = WebGLRenderingContext.COLOR_BUFFER_BIT
  override val VertexShader: Int = WebGLRenderingContext.VERTEX_SHADER
  override val FragmentShader: Int = WebGLRenderingContext.FRAGMENT_SHADER
  override val GeometryShader: Int = -1 //throw new Exception("Not supported in this version")
  override val CompileStatus: Int = WebGLRenderingContext.COMPILE_STATUS

  override def clear(mask: Int): Unit = gl.clear(mask)

  override def clearColor(red: Float, green: Float, blue: Float, alpha: Float): Unit = gl.clearColor(red, green, blue, alpha)

  override def viewport(x: Int, y: Int, width: Int, height: Int): Unit = gl.viewport(x, y, width, height)

  override def createShader(shaderType: Int): GLSLShader[WebGLShader] = Shader(gl.createShader(shaderType))

  override def compileShader(shader: GLSLShader[WebGLShader]): Unit = gl.compileShader(shader.handle)

  override def shaderSource(shader: GLSLShader[WebGLShader], source: String): Unit = gl.shaderSource(shader.handle, source)

  override def getShaderiv(shader: GLSLShader[WebGLShader], name: Int, params: IntBuffer): Unit = gl.getShaderParameter(shader.handle, name)

  override def getShaderInfoLog(shader: GLSLShader[WebGLShader]): String = gl.getShaderInfoLog(shader.handle)
}
