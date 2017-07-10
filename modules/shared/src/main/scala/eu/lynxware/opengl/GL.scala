package eu.lynxware.opengl

trait GL {
  val Texture2D: Int
  val PackAlignment: Int
  val ColorBufferBit: Int

  def clear(mask: Int): Unit
  def clearColor(red: Float, green: Float, blue: Float, alpha: Float): Unit
  def viewport(x: Int, y: Int, width: Int, height: Int): Unit
}
