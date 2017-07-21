package eu.lynxware.opengl

import java.nio.IntBuffer

/*trait GLSLShader[B] {
  val handle: B
}*/

abstract class GLSLShader[A] {
  val handle: A
}

abstract class GLSLProgram[A] {
  val handle: A
}

trait GL[ShaderHandleType <: Any, ProgramHandleType <: Any] {
  val Texture2D: Int
  val PackAlignment: Int
  val ColorBufferBit: Int

  val VertexShader: Int
  val FragmentShader: Int
  val GeometryShader: Int

  val CompileStatus: Int
  val DeleteStatus: Int
  val LinkStatus: Int
  val ValidateStatus: Int

  def clear(mask: Int): Unit
  def clearColor(red: Float, green: Float, blue: Float, alpha: Float): Unit
  def viewport(x: Int, y: Int, width: Int, height: Int): Unit

  def createShader(shaderType: Int): GLSLShader[ShaderHandleType]
  def deleteShader(shader: GLSLShader[ShaderHandleType]): Unit
  def compileShader(shader: GLSLShader[ShaderHandleType]): Unit
  def shaderSource(shader: GLSLShader[ShaderHandleType], source: String): Unit
  def getShaderiv(shader: GLSLShader[ShaderHandleType], name: Int, params: IntBuffer): Unit
  def getShaderInfoLog(shader: GLSLShader[ShaderHandleType]): String

  def createProgram(): GLSLProgram[ProgramHandleType]
  def deleteProgram(program: GLSLProgram[ProgramHandleType]): Unit
  def attachShader(program: GLSLProgram[ProgramHandleType], shader: GLSLShader[ShaderHandleType]): Unit
  def linkProgram(program: GLSLProgram[ProgramHandleType]): Unit
  def getProgramiv(program: GLSLProgram[ProgramHandleType], name: Int, params: IntBuffer)
}
