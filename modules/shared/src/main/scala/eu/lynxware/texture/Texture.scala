package eu.lynxware.texture

import eu.lynxware.misc.Disposable

class Texture(val textureId: Int, val size: Int, val typ: Int = ) extends Disposable {

  def bindToUnit(unit: Int): Unit = {
    glActiveTexture(GL_TEXTURE0 + unit)
    glBindTexture(typ, textureId)
  }

  override def dispose(): Unit = {
    glDeleteTextures(textureId)
  }

  def newTexture() = ???
}

object Texture {
  def decodeTextureFile(path: String): Unit = {
    val stream = getClass.getResourceAsStream(path)
  }
}
