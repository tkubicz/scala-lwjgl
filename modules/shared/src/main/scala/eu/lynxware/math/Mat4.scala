package eu.lynxware.math

//import scala.scalajs.js.JSConverters._
import scala.util.Random

object MathHelp {
  def degToRad(deg: Double): Double = deg * (Math.PI / 180.0)
  def radToDeg(rad: Double): Double = rad * (180.0 / Math.PI)
  def random(min: Double, max: Double) = Random.nextDouble() * (max - min) + min
}

class Mat4(m: Array[Double]) {
  def apply(index: Int): Double = m(index)

  def +(mat: Mat4): Mat4 = new Mat4(Array(
    m(0) + mat(0), m(1) + mat(1), m(2) + mat(2), m(3) + mat(3),
    m(4) + mat(4), m(5) + mat(5), m(6) + mat(6), m(7) + mat(7),
    m(8) + mat(8), m(9) + mat(9), m(10) + mat(10), m(11) + mat(11),
    m(12) + mat(12), m(13) + mat(13), m(14) + mat(14), m(15) + mat(15)))

  def -(mat: Mat4): Mat4 = new Mat4(Array(
    m(0) - mat(0), m(1) - mat(1), m(2) - mat(2), m(3) - mat(3),
    m(4) - mat(4), m(5) - mat(5), m(6) - mat(6), m(7) - mat(7),
    m(8) - mat(8), m(9) - mat(9), m(10) - mat(10), m(11) - mat(11),
    m(12) - mat(12), m(13) - mat(13), m(14) - mat(14), m(15) - mat(15)
  ))

  def *(mat: Mat4): Mat4 = new Mat4(Array(
    m(0) * mat(0) + m(4) * mat(1) + m(8) * mat(2) + m(12) * mat(3),
    m(1) * mat(0) + m(5) * mat(1) + m(9) * mat(2) + m(13) * mat(3),
    m(2) * mat(0) + m(6) * mat(1) + m(10) * mat(2) + m(14) * mat(3),
    m(3) * mat(0) + m(7) * mat(1) + m(11) * mat(2) + m(15) * mat(3),
    m(0) * mat(4) + m(4) * mat(5) + m(8) * mat(6) + m(12) * mat(7),
    m(1) * mat(4) + m(5) * mat(5) + m(9) * mat(6) + m(13) * mat(7),
    m(2) * mat(4) + m(6) * mat(5) + m(10) * mat(6) + m(14) * mat(7),
    m(3) * mat(4) + m(7) * mat(5) + m(11) * mat(6) + m(15) * mat(7),
    m(0) * mat(8) + m(4) * mat(9) + m(8) * mat(10) + m(12) * mat(11),
    m(1) * mat(8) + m(5) * mat(9) + m(9) * mat(10) + m(13) * mat(11),
    m(2) * mat(8) + m(6) * mat(9) + m(10) * mat(10) + m(14) * mat(11),
    m(3) * mat(8) + m(7) * mat(9) + m(11) * mat(10) + m(15) * mat(11),
    m(0) * mat(12) + m(4) * mat(13) + m(8) * mat(14) + m(12) * mat(15),
    m(1) * mat(12) + m(5) * mat(13) + m(9) * mat(14) + m(13) * mat(15),
    m(2) * mat(12) + m(6) * mat(13) + m(10) * mat(14) + m(14) * mat(15),
    m(3) * mat(12) + m(7) * mat(13) + m(11) * mat(14) + m(15) * mat(15)
  ))

  def *(vec: Vec4): Vec4 = Vec4(
    m(0) * vec.x + m(4) * vec.y + m(8) * vec.z + m(12) * vec.w,
    m(1) * vec.x + m(5) * vec.y + m(9) * vec.z + m(13) * vec.w,
    m(2) * vec.x + m(6) * vec.y + m(10) * vec.z + m(14) * vec.w,
    m(3) * vec.x + m(7) * vec.y + m(11) * vec.z + m(15) * vec.w
  )

  def inverse: Mat4 = {
    val inv = new Array[Double](16)

    inv(0) = m(5) * m(10) * m(15) - m(5) * m(11) * m(14) - m(9) * m(6) * m(15) + m(9) * m(7) * m(14) + m(13) * m(6) * m(11) - m(13) * m(7) * m(10)
    inv(4) = -m(4) * m(10) * m(15) + m(4) * m(11) * m(14) + m(8) * m(6) * m(15) - m(8) * m(7) * m(14) - m(12) * m(6) * m(11) + m(12) * m(7) * m(10)
    inv(8) = m(4) * m(9) * m(15) - m(4) * m(11) * m(13) - m(8) * m(5) * m(15) + m(8) * m(7) * m(13) + m(12) * m(5) * m(11) - m(12) * m(7) * m(9)
    inv(12) = -m(4) * m(9) * m(14) + m(4) * m(10) * m(13) + m(8) * m(5) * m(14) - m(8) * m(6) * m(13) - m(12) * m(5) * m(10) + m(12) * m(6) * m(9)

    inv(1) = -m(1) * m(10) * m(15) + m(1) * m(11) * m(14) + m(9) * m(2) * m(15) - m(9) * m(3) * m(14) - m(13) * m(2) * m(11) + m(13) * m(3) * m(10)
    inv(5) = m(0) * m(10) * m(15) - m(0) * m(11) * m(14) - m(8) * m(2) * m(15) + m(8) * m(3) * m(14) + m(12) * m(2) * m(11) - m(12) * m(3) * m(10)
    inv(9) = -m(0) * m(9) * m(15) + m(0) * m(11) * m(13) + m(8) * m(1) * m(15) - m(8) * m(3) * m(13) - m(12) * m(1) * m(11) + m(12) * m(3) * m(9)
    inv(13) = m(0) * m(9) * m(14) - m(0) * m(10) * m(13) - m(8) * m(1) * m(14) + m(8) * m(2) * m(13) + m(12) * m(1) * m(10) - m(12) * m(2) * m(9)

    inv(2) = m(1) * m(6) * m(15) - m(1) * m(7) * m(14) - m(5) * m(2) * m(15) + m(5) * m(3) * m(14) + m(13) * m(2) * m(7) - m(13) * m(3) * m(6)
    inv(6) = -m(0) * m(6) * m(15) + m(0) * m(7) * m(14) + m(4) * m(2) * m(15) - m(4) * m(3) * m(14) - m(12) * m(2) * m(7) + m(12) * m(3) * m(6)
    inv(10) = m(0) * m(5) * m(15) - m(0) * m(7) * m(13) - m(4) * m(1) * m(15) + m(4) * m(3) * m(13) + m(12) * m(1) * m(7) - m(12) * m(3) * m(5)
    inv(14) = -m(0) * m(5) * m(14) + m(0) * m(6) * m(13) + m(4) * m(1) * m(14) - m(4) * m(2) * m(13) - m(12) * m(1) * m(6) + m(12) * m(2) * m(5)

    inv(3) = -m(1) * m(6) * m(11) + m(1) * m(7) * m(10) + m(5) * m(2) * m(11) - m(5) * m(3) * m(10) - m(9) * m(2) * m(7) + m(9) * m(3) * m(6)
    inv(7) = m(0) * m(6) * m(11) - m(0) * m(7) * m(10) - m(4) * m(2) * m(11) + m(4) * m(3) * m(10) + m(8) * m(2) * m(7) - m(8) * m(3) * m(6)
    inv(11) = -m(0) * m(5) * m(11) + m(0) * m(7) * m(9) + m(4) * m(1) * m(11) - m(4) * m(3) * m(9) - m(8) * m(1) * m(7) + m(8) * m(3) * m(5)
    inv(15) = m(0) * m(5) * m(10) - m(0) * m(6) * m(9) - m(4) * m(1) * m(10) + m(4) * m(2) * m(9) + m(8) * m(1) * m(6) - m(8) * m(2) * m(5)

    val det = m(0) * inv(0) + m(1) * inv(4) + m(2) * inv(8) + m(3) * inv(12)
    if (det == 0) return Mat4.identity
    val oneOverDet = 1.0 / det
    val invOut = inv.map(_ * oneOverDet)
    new Mat4(invOut)
  }

  def normalMatrix: Mat3 = {
    val ex = extractMat3

    // Work out determinate
    val det = ex(0) * ex(4) * ex(8) + ex(1) * ex(5) * ex(6) + ex(2) * ex(3) * ex(7)
    - ex(2) * ex(4) * ex(6) + ex(0) * ex(5) * ex(7) + ex(1) * ex(3) * ex(8)

    val oneOverDet = 1.0 / det

    new Mat3(Array(
      (ex(4) * ex(8) - ex(5) * ex(7)) * oneOverDet,
      (ex(5) * ex(6) - ex(3) * ex(8)) * oneOverDet,
      (ex(3) * ex(7) - ex(4) * ex(6)) * oneOverDet,
      (ex(2) * ex(7) - ex(1) * ex(8)) * oneOverDet,
      (ex(0) * ex(8) - ex(2) * ex(6)) * oneOverDet,
      (ex(6) * ex(1) - ex(0) * ex(7)) * oneOverDet,
      (ex(1) * ex(5) - ex(2) * ex(4)) * oneOverDet,
      (ex(2) * ex(3) - ex(0) * ex(5)) * oneOverDet,
      (ex(0) * ex(4) - ex(1) * ex(3)) * oneOverDet
    ))
  }

  def extractMat3: Mat3 = new Mat3(Array(m(0), m(1), m(2), m(4), m(5), m(6), m(8), m(9), m(10)))

  //def toJsArray = m.toJSArray

  def *(vec: Vec3): Vec3 = new Vec3(
    m(0) * vec.x + m(4) * vec.y + m(8) * vec.z + m(12),
    m(1) * vec.x + m(5) * vec.y + m(9) * vec.z + m(13),
    m(2) * vec.x + m(6) * vec.y + m(10) * vec.z + m(14)
  )

  override def toString: String =
    "(" + m(0) + ", " + m(4) + ", " + m(8) + ", " + m(12) + "),\n" +
      "(" + m(1) + ", " + m(5) + ", " + m(9) + ", " + m(13) + "),\n" +
      "(" + m(2) + ", " + m(6) + ", " + m(10) + ", " + m(14) + "),\n" +
      "(" + m(3) + ", " + m(7) + ", " + m(11) + ", " + m(15) + "))"
}

object Mat4 {
  def identity: Mat4 = new Mat4(Array(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1))

  def translationMatrix(v: Vec3): Mat4 = translationMatrix(v.x, v.y, v.z)

  def translationMatrix(x: Double, y: Double, z: Double): Mat4 = new Mat4(Array(
    1, 0, 0, 0,
    0, 1, 0, 0,
    0, 0, 1, 0,
    x, y, z, 1
  ))

  def rotationMatrix(angle: Double, axis: Vec3): Mat4 = {
    val sinAngle = Math.sin(angle)
    val cosAngle = Math.cos(angle)
    val oneMinusCosAngle = 1.0 - cosAngle

    new Mat4(Array(
      cosAngle + axis.x * axis.x * oneMinusCosAngle, axis.z * sinAngle + axis.y * axis.x * oneMinusCosAngle, -axis.y * sinAngle + axis.z * axis.z * oneMinusCosAngle, 0,
      -axis.z * sinAngle + axis.x * axis.y * oneMinusCosAngle, cosAngle + axis.y * axis.y * oneMinusCosAngle, axis.x * sinAngle + axis.z * axis.y * oneMinusCosAngle, 0,
      axis.y * sinAngle + axis.x * axis.z * oneMinusCosAngle, -axis.x * sinAngle + axis.y * axis.z * oneMinusCosAngle, cosAngle + axis.z * axis.z * oneMinusCosAngle, 0,
      0, 0, 0, 1
    ))
  }

  def scaleMatrix(v: Vec3): Mat4 = scaleMatrix(v.x, v.y, v.z)

  def scaleMatrix(x: Double, y: Double, z: Double) = new Mat4(Array(
    x, 0, 0, 0,
    0, y, 0, 0,
    0, 0, z, 0,
    0, 0, 0, 1
  ))

  def perspectiveProjection(fov: Double, aspectRatio: Double, near: Double, far: Double): Mat4 = {
    val rad = MathHelp.degToRad(fov / 2)
    val delta = far - near
    val s = Math.sin(rad)

    if (delta == 0 || s == 0 || aspectRatio == 0) return identity

    val cotangent = Math.cos(rad) / s
    new Mat4(Array(
      cotangent / aspectRatio, 0, 0, 0,
      0, cotangent, 0, 0,
      0, 0, -(far - near) / delta, -1,
      0, 0, -2 * near * far / delta, 0
    ))
  }

  def orthographicProjection(left: Double, right: Double, bottom: Double, top: Double, near: Double, far: Double): Mat4 = {
    val rightLeft = right - left
    val topBottom = top - bottom
    val farNear = far - near
    val x = (right + left) / rightLeft
    val y = (top + bottom) / topBottom
    val z = (far + near) / farNear
    new Mat4(Array(
      2 / rightLeft, 0, 0, 0,
      0, 2 / topBottom, 0, 0,
      0, 0, -2 / farNear, 0,
      -x, -y, -z, 1))
  }

  def lookAt(eyePosition: Vec3, center: Vec3, upVector: Vec3): Mat4 = {
    val f = (center - eyePosition).normalize
    val up = upVector.normalize
    val s = f.crossProduct(up).normalize
    val u = s.crossProduct(f).normalize
    val m = new Mat4(Array(s.x, u.x, -f.x, 0, s.y, u.y, -f.y, 0, s.z, u.z, -f.z, 0, 0, 0, 0, 1))
    val t = translationMatrix(-eyePosition.x, -eyePosition.y, -eyePosition.z)
    m * t
  }

  def apply(values: Array[Double]): Mat4 = new Mat4(values)

  def apply(): Mat4 = identity
}
