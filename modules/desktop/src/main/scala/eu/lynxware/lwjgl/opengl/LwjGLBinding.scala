package eu.lynxware.lwjgl.opengl

import eu.lynxware.opengl.GL
import eu.lynxware.texture.Texture
import org.lwjgl.opengl.GL11

class LwjGLBinding extends GL {
  override val TEXTURE_2D: Int = GL11.GL_TEXTURE_2D
  override val PACK_ALIGNMENT: Int = GL11.GL_PACK_ALIGNMENT

  override def bindTexture(target: Int, texture: Texture): Unit = GL11.glBindTexture(target, texture.asInstanceOf[Int])
}
