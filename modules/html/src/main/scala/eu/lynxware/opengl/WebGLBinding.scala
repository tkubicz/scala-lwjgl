package eu.lynxware.opengl

import org.scalajs.dom.raw.WebGLRenderingContext

class WebGLBinding(gl: WebGLRenderingContext) extends GL {
  override val Texture2D: Int = WebGLRenderingContext.TEXTURE_2D
  override val PackAlignment: Int = WebGLRenderingContext.PACK_ALIGNMENT
  override val ColorBufferBit: Int = WebGLRenderingContext.COLOR_BUFFER_BIT

  override val VertexShader: Int = WebGLRenderingContext.VERTEX_SHADER
  override val FragmentShader: Int = WebGLRenderingContext.FRAGMENT_SHADER
  override val GeometryShader: Int = throw new Exception("Not supported in this version")

  override def clear(mask: Int): Unit = gl.clear(mask)

  override def clearColor(red: Float, green: Float, blue: Float, alpha: Float): Unit = gl.clearColor(red, green, blue, alpha)

  override def viewport(x: Int, y: Int, width: Int, height: Int): Unit = gl.viewport(x, y, width, height)

  override def createShader(shaderType: Int): Unit = gl.createShader(shaderType)

  override def compileShader(shader: Int): Unit = gl.compileShader(shader)

  override def shaderSource(shader: Int, source: String): Unit = ???
}
