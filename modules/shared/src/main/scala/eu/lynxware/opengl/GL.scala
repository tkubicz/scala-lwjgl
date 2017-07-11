package eu.lynxware.opengl

trait GL {
  val Texture2D: Int
  val PackAlignment: Int
  val ColorBufferBit: Int

  val VertexShader: Int
  val FragmentShader: Int
  val GeometryShader: Int

  def clear(mask: Int): Unit
  def clearColor(red: Float, green: Float, blue: Float, alpha: Float): Unit
  def viewport(x: Int, y: Int, width: Int, height: Int): Unit

  def createShader(shaderType: Int): Unit
  def compileShader(shader: Int): Unit
}
