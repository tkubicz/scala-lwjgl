package eu.lynxware.math

class Vec3(val x: Double = 0.0, val y: Double = 0.0, val z: Double = 0.0) {
  def +(v: Vec3): Vec3 = new Vec3(x + v.x, y + v.y, z + v.z)
  def -(v: Vec3): Vec3 = new Vec3(x - v.x , y - v.y, z - v.z)
  def *(scale: Double): Vec3 = new Vec3(x * scale, y * scale, z * scale)
  def /(scale: Double): Vec3 = new Vec3(x / scale, y / scale, z / scale)
  def ==(v: Vec3): Boolean = x == v.x && y == v.y && z == v.z

  def componentProduct(v: Vec3): Vec3 = new Vec3(x * v.x, y * v.y, z * v.z)
  def crossProduct(v: Vec3): Vec3 = new Vec3(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x)
  def dotProduct(v: Vec3): Double = x * v.x + y * v.y + z * v.z

  def angle(v: Vec3): Double = Math.acos(dotProduct(v) / (length * v.length))
  def distance(v: Vec3): Double = Math.sqrt((v.x - x) * (v.x - x) + (v.y - y) * (v.y - y) + (v.z - z) * (v.z - z))
  def length: Double = Math.sqrt(x * x + y * y + z * z)
  def normalize: Vec3 = {
    val l = length
    if (l > 0) {
      val factor = 1.0 / l
      new Vec3(x * factor, y * factor, z * factor)
    }
    else new Vec3(0.0, 0.0, 0.0)
  }
  override def toString: String = "(" + x + "," + y + "," + z + ")"
}

object Vec3 {
  def apply(x: Double, y: Double, z: Double): Vec3 = new Vec3(x, y, z)
  def apply(): Vec3 = new Vec3()

  def random(xMin: Double, xMax: Double, yMin: Double, yMax: Double, zMin: Double, zMax: Double): Vec3 =
    Vec3(MathHelp.random(xMin, xMax), MathHelp.random(yMin, yMax), MathHelp.random(zMin, zMax))
}