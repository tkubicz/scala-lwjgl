package eu.lynxware.opengl

import java.nio.IntBuffer

trait GLSLShader[A] {
  val handle: A
}

trait GLSLProgram[A, B, C] {
  val handle: A
  val uniforms: Map[String, B] = Map()
  val attribs: Map[String, C] = Map()
}

trait GL[ShaderHandleType, ProgramHandleType, UniformLocationType, AttribLocationType] {
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

  def createProgram(): GLSLProgram[ProgramHandleType, UniformLocationType, AttribLocationType]
  def deleteProgram(program: GLSLProgram[ProgramHandleType, UniformLocationType, AttribLocationType]): Unit
  def attachShader(program: GLSLProgram[ProgramHandleType, UniformLocationType, AttribLocationType], shader: GLSLShader[ShaderHandleType]): Unit
  def linkProgram(program: GLSLProgram[ProgramHandleType, UniformLocationType, AttribLocationType]): Unit
  def getProgramiv(program: GLSLProgram[ProgramHandleType, UniformLocationType, AttribLocationType], name: Int, params: IntBuffer): Unit
  def getProgramInfoLog(program: GLSLProgram[ProgramHandleType, UniformLocationType, AttribLocationType]): String

  def getUniformLocation(program: GLSLProgram[ProgramHandleType, UniformLocationType, AttribLocationType], name: String): UniformLocationType
  def getAttribLocation(program: GLSLProgram[ProgramHandleType, UniformLocationType, AttribLocationType], name: String): AttribLocationType
}
