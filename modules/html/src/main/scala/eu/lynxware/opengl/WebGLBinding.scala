package eu.lynxware.opengl
import eu.lynxware.texture.Texture
import org.scalajs.dom.raw.{WebGLRenderingContext, WebGLTexture}

class WebGLBinding(gl: WebGLRenderingContext) extends GL {
  override val TEXTURE_2D: Int = WebGLRenderingContext.TEXTURE_2D

  override val PACK_ALIGNMENT: Int = WebGLRenderingContext.PACK_ALIGNMENT

  def bindTexture(target: Int, texture: Texture): Unit = gl.bindTexture(target, texture.asInstanceOf[WebGLTexture])
}
