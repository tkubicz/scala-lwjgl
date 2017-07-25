package eu.lynxware.opengl

import java.nio.IntBuffer

import android.opengl.GLES20
import eu.lynxware.opengl.Types.{AttribLocationType, ProgramHandleType, ShaderHandleType, UniformLocationType}

object Types {
  type ShaderHandleType = Int
  type ProgramHandleType = Int
  type UniformLocationType = Int
  type AttribLocationType = Int
}

case class Shader(handle: ShaderHandleType) extends GLSLShader[ShaderHandleType]

case class Program(handle: ProgramHandleType) extends GLSLProgram[ProgramHandleType]

object GL20Binding extends GL[ShaderHandleType, ProgramHandleType, UniformLocationType, AttribLocationType] {
  override val Texture2D: Int = GLES20.GL_TEXTURE_2D
  override val PackAlignment: Int = GLES20.GL_PACK_ALIGNMENT
  override val ColorBufferBit: Int = GLES20.GL_COLOR_BUFFER_BIT
  override val FragmentShader: Int = GLES20.GL_FRAGMENT_SHADER
  override val VertexShader: Int = GLES20.GL_VERTEX_SHADER
  override val GeometryShader: Int = -1
  override val CompileStatus: Int = GLES20.GL_COMPILE_STATUS
  override val DeleteStatus: AttribLocationType = GLES20.GL_DELETE_STATUS
  override val LinkStatus: AttribLocationType = GLES20.GL_LINK_STATUS
  override val ValidateStatus: AttribLocationType = GLES20.GL_VALIDATE_STATUS

  override def clear(mask: Int): Unit = GLES20.glClear(mask: Int)

  override def clearColor(red: Float, green: Float, blue: Float, alpha: Float): Unit = GLES20.glClearColor(red, green, blue, alpha)

  override def viewport(x: Int, y: Int, width: Int, height: Int): Unit = GLES20.glViewport(x, y, width, height)

  override def createShader(shaderType: Int): GLSLShader[ShaderHandleType] = Shader(GLES20.glCreateShader(shaderType))

  override def deleteShader(shader: GLSLShader[ShaderHandleType]): Unit = GLES20.glDeleteShader(shader.handle)

  override def compileShader(shader: GLSLShader[ShaderHandleType]): Unit = GLES20.glCompileShader(shader.handle)

  override def shaderSource(shader: GLSLShader[ShaderHandleType], source: String): Unit = GLES20.glShaderSource(shader.handle, source)

  override def getShaderiv(shader: GLSLShader[ShaderHandleType], name: Int, params: IntBuffer): Unit = GLES20.glGetShaderiv(shader.handle, name, params)

  override def getShaderInfoLog(shader: GLSLShader[ShaderHandleType]): String = GLES20.glGetShaderInfoLog(shader.handle)

  override def createProgram(): GLSLProgram[ProgramHandleType] = Program(GLES20.glCreateProgram())

  override def deleteProgram(program: GLSLProgram[ProgramHandleType]): Unit = GLES20.glDeleteProgram(program.handle)

  override def attachShader(program: GLSLProgram[ProgramHandleType], shader: GLSLShader[ShaderHandleType]): Unit = GLES20.glAttachShader(program.handle, shader.handle)

  override def linkProgram(program: GLSLProgram[ProgramHandleType]): Unit = GLES20.glLinkProgram(program.handle)

  override def getProgramiv(program: GLSLProgram[ProgramHandleType], name: Int, params: IntBuffer): Unit = GLES20.glGetProgramiv(program.handle, name, params)

  override def getProgramInfoLog(program: GLSLProgram[ProgramHandleType]): String = GLES20.glGetProgramInfoLog(program.handle)

  override def getUniformLocation(program: GLSLProgram[ProgramHandleType], name: String): UniformLocationType = GLES20.glGetUniformLocation(program.handle, name)

  override def getAttribLocation(program: GLSLProgram[ProgramHandleType], name: String): AttribLocationType = GLES20.glGetAttribLocation(program.handle, name)
}
