package eu.lynxware.opengl

import android.opengl.GLES20

object GL20Binding extends GL {
  override val Texture2D: Int = GLES20.GL_TEXTURE_2D
  override val PackAlignment: Int = GLES20.GL_PACK_ALIGNMENT
  override val ColorBufferBit: Int = GLES20.GL_COLOR_BUFFER_BIT

  override val FragmentShader: Int = GLES20.GL_FRAGMENT_SHADER
  override val VertexShader: Int = GLES20.GL_VERTEX_SHADER
  override val GeometryShader: Int = throw new Exception("Not supported in this version")

  override def clearColor(red: Float, green: Float, blue: Float, alpha: Float): Unit = GLES20.glClearColor(red, green, blue, alpha)

  override def clear(mask: Int): Unit = GLES20.glClear(mask: Int)

  override def viewport(x: Int, y: Int, width: Int, height: Int): Unit = GLES20.glViewport(x, y, width, height)

  override def createShader(shaderType: Int): Unit = GLES20.glCreateShader(shaderType)

  override def compileShader(shader: Int): Unit = GLES20.glCompileShader(shader)
}