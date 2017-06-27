package eu.lynxware.math

class Vec4(val x: Double = 0.0, val y: Double = 0.0, val z: Double = 0.0, val w: Double = 0.0) {
  def *(mat: Mat4): Vec4 = Vec4(
    mat(0) * x + mat(4) * y + mat(8) * z + mat(12) * w,
    mat(1) * x + mat(5) * y + mat(9) * z + mat(13) * w,
    mat(2) * x + mat(6) * y + mat(10) * z + mat(14) * w,
    mat(3) * x + mat(7) * y + mat(11) * z + mat(15) * w
  )

  override def toString: String = "(" + x + "," + y + "," + z + "," + w + ")"
}

object Vec4 {
  def apply(x: Double, y: Double, z: Double, w: Double): Vec4 = new Vec4(x, y, z, w)

  def apply(): Vec4 = new Vec4()
}
