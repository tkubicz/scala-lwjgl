package eu.lynxware.math

class Ray(val direction: Vec3, val position: Vec3) {
  def intersectSphere(radius: Double, center: Vec3): Option[(Double, Double)] = {
    val l = position - center
    val a = direction.dotProduct(direction)
    val b = 2 * direction.dotProduct(l)
    val c = l.dotProduct(l) - (radius * radius)

    // TODO: Finish that
    None
  }

  override def toString: String = s"Ray(direction = ${direction}, position = ${position})"
}

object Ray {
  def apply(direction: Vec3, position: Vec3): Ray = new Ray(direction, position)

  // debug - those are debug methods
  /*def buildRay(ray: Ray, length: Double = 1000.0)(implicit gl: WebGLRenderingContext, ext: OESVertexArrayObject): WebGLVertexArrayObjectOES = {
    val vertices = new Float32Array(js.Array(
      ray.position.x, ray.position.y, ray.position.z,
      ray.direction.x * length, ray.direction.y * length, ray.direction.z * length
    ))

    val vertexBuffer = gl.createBuffer()
    gl.bindBuffer(ARRAY_BUFFER, vertexBuffer)
    gl.bufferData(ARRAY_BUFFER, vertices, STATIC_DRAW)

    val vertexArray = ext.createVertexArrayOES()
    ext.bindVertexArrayOES(vertexArray)

    gl.bindBuffer(ARRAY_BUFFER, vertexBuffer)
    gl.vertexAttribPointer(0, 3, FLOAT, false, 0, 0)
    gl.enableVertexAttribArray(0)

    ext.bindVertexArrayOES(null)
    vertexArray
  }

  def renderRay(va: WebGLVertexArrayObjectOES)(implicit gl: WebGLRenderingContext, ext: OESVertexArrayObject): Unit = {
    ext.bindVertexArrayOES(va)
    gl.drawArrays(WebGLRenderingContext.LINES, 0, 2)
    ext.bindVertexArrayOES(null)
  }*/
}
