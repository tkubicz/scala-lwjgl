package eu.lynxware.lwjgl.opengl

import eu.lynxware.opengl.GL
import eu.lynxware.texture.Texture
import org.lwjgl.opengl.GL11

class LwjGLBinding extends GL {
  override val Texture2D: Int = GL11.GL_TEXTURE_2D
  override val PackAlignment: Int = GL11.GL_PACK_ALIGNMENT
  override val ColorBufferBit: Int = GL11.GL_COLOR_BUFFER_BIT

  override def clear(mask: Int): Unit = GL11.glClear(mask)
  override def clearColor(red: Float, green: Float, blue: Float, alpha: Float): Unit = GL11.glClearColor(red, green, blue, alpha)
  override def viewport(x: Int, y: Int, width: Int, height: Int): Unit = GL11.glViewport(x, y, width, height)
}
