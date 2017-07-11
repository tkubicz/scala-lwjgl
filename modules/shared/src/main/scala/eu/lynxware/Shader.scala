package eu.lynxware

import eu.lynxware.opengl.GL

abstract class Shader()(implicit gl: GL) {

  def loadFromFile(name: String, vertexShaderLocation: String, fragmentShaderLocation: String)

}
