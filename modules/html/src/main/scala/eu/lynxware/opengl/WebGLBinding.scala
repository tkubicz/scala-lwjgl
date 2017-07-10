package eu.lynxware.opengl
import eu.lynxware.texture.Texture
import org.scalajs.dom.raw.{WebGLRenderingContext, WebGLTexture}

class WebGLBinding(gl: WebGLRenderingContext) extends GL {
  override val Texture2D: Int = WebGLRenderingContext.TEXTURE_2D
  override val PackAlignment: Int = WebGLRenderingContext.PACK_ALIGNMENT
  override val ColorBufferBit: Int = WebGLRenderingContext.COLOR_BUFFER_BIT

  override def clear(mask: Int): Unit = gl.clear(mask)
  override def clearColor(red: Float, green: Float, blue: Float, alpha: Float): Unit = gl.clearColor(red, green, blue, alpha)
  override def viewport(x: Int, y: Int, width: Int, height: Int): Unit = gl.viewport(x, y, width, height)
}
