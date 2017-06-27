package eu.lynxware.camera

import eu.lynxware.math.Mat4

case class Projection(fov: Double, width: Double, height: Double, nearDistance: Double, farDistance: Double) {
  val ratio: Double = width / height
  val perspectiveMatrix: Mat4 = Mat4.perspectiveProjection(fov, ratio, nearDistance, farDistance)
  //val perspectiveMatrixJsArray = perspectiveMatrix.toJsArray
}
