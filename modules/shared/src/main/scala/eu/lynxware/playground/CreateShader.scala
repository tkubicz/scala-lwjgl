package eu.lynxware.playground

import java.nio.{ByteBuffer, ByteOrder, IntBuffer}

import eu.lynxware.opengl.GL

class CreateShader[S, P, U, A](gl: GL[S, P, U, A]) {
  val vertexShaderSource =
    """
      |attribute vec3 aVertex;
      |
      |uniform mat4 modelview_matrix;
      |uniform mat4 projection_matrix;
      |
      |void main(void) {
      |    vec4 pos = modelview_matrix * vec4(aVertex, 1.0);
      |    gl_Position = projection_matrix * pos;
      |}
    """.stripMargin

  val vertexShader = gl.createShader(gl.VertexShader)
  gl.shaderSource(vertexShader, vertexShaderSource)
  gl.compileShader(vertexShader)

  val buffer = ByteBuffer.allocateDirect(4)
  buffer.order(ByteOrder.nativeOrder())
  val intBuffer = buffer.asIntBuffer()

  gl.getShaderiv(vertexShader, gl.CompileStatus, intBuffer)

  val compilationStatus = intBuffer.get(0)

  println("compilation status: " +  compilationStatus)

  if (compilationStatus != 1) {
    val infoLog = gl.getShaderInfoLog(vertexShader)
    println("info log: " + infoLog)
  }

  gl.deleteShader(vertexShader)
}
