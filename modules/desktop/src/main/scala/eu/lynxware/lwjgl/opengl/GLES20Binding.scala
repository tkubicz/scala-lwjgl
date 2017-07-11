package eu.lynxware.lwjgl.opengl

import java.nio.IntBuffer

import eu.lynxware.opengl.GL
import org.lwjgl.opengles.GLES20

object GLES20Binding extends GL {
  override val Texture2D: Int = GLES20.GL_TEXTURE_2D
  override val PackAlignment: Int = GLES20.GL_PACK_ALIGNMENT
  override val ColorBufferBit: Int = GLES20.GL_COLOR_BUFFER_BIT
  override val VertexShader: Int = GLES20.GL_VERTEX_SHADER
  override val FragmentShader: Int = GLES20.GL_FRAGMENT_SHADER
  override val GeometryShader: Int = -1
  override val CompileStatus: Int = GLES20.GL_COMPILE_STATUS

  override def clear(mask: Int): Unit = GLES20.glClear(mask)

  override def clearColor(red: Float, green: Float, blue: Float, alpha: Float): Unit = GLES20.glClearColor(red, green, blue, alpha)

  override def viewport(x: Int, y: Int, width: Int, height: Int): Unit = GLES20.glViewport(x, y, width, height)

  override def createShader(shaderType: Int): Int = GLES20.glCreateShader(shaderType)

  override def compileShader(shader: Int): Unit = GLES20.glCompileShader(shader)

  override def shaderSource(shader: Int, source: String): Unit = GLES20.glShaderSource(shader, source)

  override def getShaderiv(shader: Int, name: Int, params: IntBuffer): Unit = GLES20.glGetShaderiv(shader, name, params)

  override def getShaderInfoLog(shader: Int): String = GLES20.glGetShaderInfoLog(shader)
}