package eu.lynxware.shader

import eu.lynxware.opengl.GL

abstract class ShaderLoader()(implicit gl: GL[_]) {

  def loadFromFile(name: String, vertexShaderLocation: String, fragmentShaderLocation: String)

}
