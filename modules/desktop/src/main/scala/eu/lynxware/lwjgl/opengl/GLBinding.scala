/*
package eu.lynxware.lwjgl.opengl

import java.nio.IntBuffer

import eu.lynxware.opengl.{GL, GLSLShader}
import org.lwjgl.opengl.{GL11, GL20, GL32}

object GLBinding extends GL {
  override val Texture2D: Int = GL11.GL_TEXTURE_2D
  override val PackAlignment: Int = GL11.GL_PACK_ALIGNMENT
  override val ColorBufferBit: Int = GL11.GL_COLOR_BUFFER_BIT

  override val VertexShader: Int = GL20.GL_VERTEX_SHADER
  override val FragmentShader: Int = GL20.GL_FRAGMENT_SHADER
  override val GeometryShader: Int = GL32.GL_GEOMETRY_SHADER

  override val CompileStatus: Int = GL20.GL_COMPILE_STATUS

  override def clear(mask: Int): Unit = GL11.glClear(mask)

  override def clearColor(red: Float, green: Float, blue: Float, alpha: Float): Unit = GL11.glClearColor(red, green, blue, alpha)

  override def viewport(x: Int, y: Int, width: Int, height: Int): Unit = GL11.glViewport(x, y, width, height)

  override def createShader(shaderType: Int): GLSLShader[_] = {
    val handle = GL20.glCreateShader(shaderType)
    Shader(handle)
  }

  override def compileShader[T <: Int](shader: GLSLShader[T]): Unit = GL20.glCompileShader(shader.handle)

  /*override def createShader[T <: GLSLShader[Int]](shaderType: Int): GLSLShader[Int] = {
    val handle = GL20.glCreateShader(shaderType)
    Shader(handle)
  }*/

  /*override def compileShader[T <: GLSLShader[Int]](shader: T): Unit = GL20.glCompileShader(shader.handle)

  override def shaderSource[T <: GLSLShader[Int]](shader: T, source: String): Unit = GL20.glShaderSource(shader.handle, source)

  override def getShaderiv[T <: GLSLShader[Int]](shader: T, name: Int, params: IntBuffer): Unit = GL20.glGetShaderiv(shader.handle, name, params)

  override def getShaderInfoLog[T <: GLSLShader[Int]](shader: T): String = GL20.glGetShaderInfoLog(shader.handle)*/
}
*/
