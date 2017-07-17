package eu.lynxware.opengl

import java.nio.IntBuffer

import android.opengl.GLES20
import eu.lynxware.opengl.Types.ShaderHandleType

object Types {
  type ShaderHandleType = Int
}

case class Shader(handle: ShaderHandleType) extends GLSLShader[ShaderHandleType]

object GL20Binding extends GL[ShaderHandleType] {
  override val Texture2D: Int = GLES20.GL_TEXTURE_2D
  override val PackAlignment: Int = GLES20.GL_PACK_ALIGNMENT
  override val ColorBufferBit: Int = GLES20.GL_COLOR_BUFFER_BIT
  override val FragmentShader: Int = GLES20.GL_FRAGMENT_SHADER
  override val VertexShader: Int = GLES20.GL_VERTEX_SHADER
  override val GeometryShader: Int = -1
  override val CompileStatus: Int = GLES20.GL_COMPILE_STATUS

  override def clear(mask: Int): Unit = GLES20.glClear(mask: Int)

  override def clearColor(red: Float, green: Float, blue: Float, alpha: Float): Unit = GLES20.glClearColor(red, green, blue, alpha)

  override def viewport(x: Int, y: Int, width: Int, height: Int): Unit = GLES20.glViewport(x, y, width, height)

  override def createShader(shaderType: Int): GLSLShader[ShaderHandleType] = Shader(GLES20.glCreateShader(shaderType))

  override def compileShader(shader: GLSLShader[ShaderHandleType]): Unit = GLES20.glCompileShader(shader.handle)

  override def shaderSource(shader: GLSLShader[ShaderHandleType], source: String): Unit = GLES20.glShaderSource(shader.handle, source)

  override def getShaderiv(shader: GLSLShader[ShaderHandleType], name: Int, params: IntBuffer): Unit = GLES20.glGetShaderiv(shader.handle, name, params)

  override def getShaderInfoLog(shader: GLSLShader[ShaderHandleType]): String = GLES20.glGetShaderInfoLog(shader.handle)
}
