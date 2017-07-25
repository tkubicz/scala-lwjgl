package eu.lynxware.lwjgl.opengl

import java.nio.IntBuffer

import eu.lynxware.lwjgl.opengl.Types.{AttribLocationType, ProgramHandleType, ShaderHandleType, UniformLocationType}
import eu.lynxware.opengl.{GL, GLSLProgram, GLSLShader}
import org.lwjgl.opengles.GLES20

object Types {
  type ShaderHandleType = Int
  type ProgramHandleType = Int
  type UniformLocationType = Int
  type AttribLocationType = Int
}

case class Shader(handle: ShaderHandleType) extends GLSLShader[ShaderHandleType]

case class Program(handle: ProgramHandleType,
                   override val uniforms: Map[String, UniformLocationType] = Map(),
                   override val attribs: Map[String, AttribLocationType] = Map()) extends GLSLProgram[ProgramHandleType, UniformLocationType, AttribLocationType]

object GLES20Binding extends GL[ShaderHandleType, ProgramHandleType, UniformLocationType, AttribLocationType] {
  override val Texture2D: Int = GLES20.GL_TEXTURE_2D
  override val PackAlignment: Int = GLES20.GL_PACK_ALIGNMENT
  override val ColorBufferBit: Int = GLES20.GL_COLOR_BUFFER_BIT
  override val VertexShader: Int = GLES20.GL_VERTEX_SHADER
  override val FragmentShader: Int = GLES20.GL_FRAGMENT_SHADER
  override val GeometryShader: Int = -1 //throw new Exception("Not supported in this version")
  override val CompileStatus: Int = GLES20.GL_COMPILE_STATUS
  override val DeleteStatus: Int = GLES20.GL_DELETE_STATUS
  override val LinkStatus: Int = GLES20.GL_LINK_STATUS
  override val ValidateStatus: Int = GLES20.GL_VALIDATE_STATUS

  override def clear(mask: Int): Unit = GLES20.glClear(mask)

  override def clearColor(red: Float, green: Float, blue: Float, alpha: Float): Unit = GLES20.glClearColor(red, green, blue, alpha)

  override def viewport(x: Int, y: Int, width: Int, height: Int): Unit = GLES20.glViewport(x, y, width, height)

  override def createShader(shaderType: Int): Shader = Shader(GLES20.glCreateShader(shaderType))

  override def deleteShader(shader: GLSLShader[ShaderHandleType]): Unit = GLES20.glDeleteShader(shader.handle)

  override def compileShader(shader: GLSLShader[Int]): Unit = GLES20.glCompileShader(shader.handle)

  override def shaderSource(shader: GLSLShader[Int], source: String): Unit = GLES20.glShaderSource(shader.handle)

  override def getShaderiv(shader: GLSLShader[Int], name: Int, params: IntBuffer): Unit = GLES20.glGetShaderiv(shader.handle, name, params)

  override def getShaderInfoLog(shader: GLSLShader[Int]): String = GLES20.glGetShaderInfoLog(shader.handle)

  override def createProgram(): GLSLProgram[ProgramHandleType, UniformLocationType, AttribLocationType] =
    Program(GLES20.glCreateProgram())

  override def deleteProgram(program: GLSLProgram[ProgramHandleType, UniformLocationType, AttribLocationType]): Unit =
    GLES20.glDeleteProgram(program.handle)

  override def attachShader(program: GLSLProgram[ProgramHandleType, UniformLocationType, AttribLocationType], shader: GLSLShader[ShaderHandleType]): Unit =
    GLES20.glAttachShader(program.handle, shader.handle)

  override def linkProgram(program: GLSLProgram[ProgramHandleType, UniformLocationType, AttribLocationType]): Unit =
    GLES20.glLinkProgram(program.handle)

  override def getProgramiv(program: GLSLProgram[ProgramHandleType, UniformLocationType, AttribLocationType], name: Int, params: IntBuffer): Unit =
    GLES20.glGetProgramiv(program.handle, name, params)

  override def getProgramInfoLog(program: GLSLProgram[ProgramHandleType, UniformLocationType, AttribLocationType]): String =
    GLES20.glGetProgramInfoLog(program.handle)

  override def getUniformLocation(program: GLSLProgram[ProgramHandleType, UniformLocationType, AttribLocationType], name: String): UniformLocationType =
    GLES20.glGetUniformLocation(program.handle, name)

  override def getAttribLocation(program: GLSLProgram[ProgramHandleType, UniformLocationType, AttribLocationType], name: String): AttribLocationType =
    GLES20.glGetAttribLocation(program.handle, name)
}
