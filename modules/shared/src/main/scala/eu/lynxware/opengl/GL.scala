package eu.lynxware.opengl

import java.nio.IntBuffer

trait GL {
  val Texture2D: Int
  val PackAlignment: Int
  val ColorBufferBit: Int

  val VertexShader: Int
  val FragmentShader: Int
  val GeometryShader: Int

  val CompileStatus: Int

  def clear(mask: Int): Unit
  def clearColor(red: Float, green: Float, blue: Float, alpha: Float): Unit
  def viewport(x: Int, y: Int, width: Int, height: Int): Unit

  def createShader(shaderType: Int): Int
  def compileShader(shader: Int): Unit
  def shaderSource(shader: Int, source: String): Unit

  def getShaderiv(shader: Int, name: Int, params: IntBuffer): Unit
  def getShaderInfoLog(shader: Int): String
}
