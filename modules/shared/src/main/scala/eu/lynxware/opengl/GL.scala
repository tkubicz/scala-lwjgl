package eu.lynxware.opengl

import eu.lynxware.texture.Texture

trait GL {
  val TEXTURE_2D: Int

  val PACK_ALIGNMENT: Int

  def bindTexture(target: Int, texture: Texture): Unit
}
