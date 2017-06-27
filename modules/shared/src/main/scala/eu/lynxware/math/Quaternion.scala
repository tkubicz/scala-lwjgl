package eu.lynxware.math

// TODO: Write tests for this class, I'm not sure if it's working properly

class Quaternion(val x: Double, val y: Double, val z: Double, val w: Double) {

  def normalize(): Quaternion = {
    val mag = Math.sqrt(w * w + x * x + y * y + z * z)
    Quaternion(x / mag, y / mag, z / mag, w / mag)
  }

  def rotationMatrix: Mat4 = {
    val xy = x * y
    val xz = x * z
    val xw = x * w
    val yz = y * z
    val yw = y * w
    val zw = z * w
    val xSquared = x * x
    val ySquared = y * y
    val zSquared = z * z

    Mat4(Array(
      1 - 2 * (ySquared + zSquared), 2 * (xy + zw), 2 * (xz - yw), 0,
      2 * (xy - zw), 1 - 2 * (xSquared + zSquared), 2 * (yz + xz), 0,
      2 * (xz + yw), 2 * (yz - xw), 1 - 2 * (xSquared + ySquared), 0,
      0, 0, 0, 1
    ))
  }
}

object Quaternion {
  def apply(x: Double, y: Double, z: Double, w: Double): Quaternion = new Quaternion(x, y, z, w)

  def apply(matrix: Mat4): Quaternion = fromMatrix(matrix)

  def fromMatrix(matrix: Mat4): Quaternion = {
    val diagonal = matrix(0) + matrix(5) + matrix(10)
    val (x, y, z, w) = if (diagonal > 0) {
      val w4 = Math.sqrt(diagonal + 1.0) * 2.0
      val x = (matrix(6) - matrix(5)) / w4
      val y = (matrix(8) - matrix(2)) / w4
      val z = (matrix(1) - matrix(4)) / w4
      val w = w4 / 4.0
      (x, y, z, w)
    } else if ((matrix(0) > matrix(5)) && (matrix(0) > matrix(10))) {
      val x4 = Math.sqrt(1.0 + matrix(0) - matrix(5) - matrix(10)) * 2.0
      val w = (matrix(6) - matrix(9)) / x4
      val x = x4 / 4.0
      val y = (matrix(4) + matrix(1)) / x4
      val z = (matrix(8) + matrix(2)) / x4
      (x, y, z, w)
    } else if (matrix(5) > matrix(10)) {
      val y4 = Math.sqrt(1.0 + matrix(5) - matrix(0) - matrix(10)) * 2.0
      val w = (matrix(8) - matrix(2)) / y4
      val x = (matrix(4) + matrix(1)) / y4
      val y = y4 / 4.0
      val z = (matrix(9) + matrix(6)) / y4
      (x, y, z, w)
    } else {
      val z4 = Math.sqrt(1.0 + matrix(10) - matrix(0) - matrix(5)) * 2.0
      val w = (matrix(1) - matrix(4)) / z4
      val x = (matrix(8) + matrix(2)) / z4
      val y = (matrix(9) + matrix(6)) / z4
      val z = z4 / 4.0
      (x, y, z, w)
    }
    Quaternion(x, y, z, w)
  }

  /**
    * Interpolates between two quaternion rotations and returns the resulting
    * quaternion rotation. The interpolation method here is "nlerp", or "normalized-lerp".
    * Another method that could be used is "slerp", and you can see a comparison of the
    * methods here:
    * https://keithmaggio.wordpress.com/2011/02/15/math-magician-lerp-slerp-and-nlerp/
    * and here:
    * http://number-none.com/product/Understanding%20Slerp,%20Then%20Not%20Using%20It/
    *
    * @param a
    * @param b
    * @param blend a value between 0 and 1 indicating how far to interpolate between the
    *              two quaternions.
    * @return the resulting interpolated rotation in quaternion format.
    */
  def interpolate(a: Quaternion, b: Quaternion, blend: Double): Quaternion = {
    val dot = a.w * b.w + a.x * b.x + a.y * b.y + a.z * b.z
    val blendI = 1.0 - blend
    val (x, y, z, w) = if (dot < 0)
      (blendI * a.x + blend * -b.x, blendI * a.y + blend * -b.y, blendI * a.z + blend * -b.z, blendI * a.w + blend * -b.w)
    else
      (blendI * a.x + blend * b.w, blendI * a.y + blend * b.y, blendI * a.z + blend * b.z, blendI * a.w + blend * b.w)
    Quaternion(x, y, z, w)
  }
}
