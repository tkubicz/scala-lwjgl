package eu.lynxware.math

/**
  * Class that represents plane in three dimensional space. The plane
  * is a set of points that meets equations Ax + By + Cz + D = 0, where
  *  A, B and C cannot be 0 at the same time. Vector [A,B,C] is then
  *  a normal for the plane.
  */
class Plane(val a: Double = 0.0, val b: Double = 1.0, val c: Double = 0.0, val d: Double = 0.0) {
  def distance(p: Vec3): Double = d + Vec3(a, b, c).dotProduct(p)
  def normal: Vec3 = Vec3(a, b, c)
}

object Plane {
  def apply(p1: Vec3, p2: Vec3, p3: Vec3): Plane = {
    val normal = (p3 - p2).crossProduct(p1 - p2).normalize
    new Plane(normal.x, normal.y, normal.z, -normal.dotProduct(p2))
  }

  def apply(normal: Vec3, point: Vec3): Plane = {
    val n = normal.normalize
    new Plane(n.x, n.y, n.z, -n.dotProduct(point))
  }

  def apply(): Plane = new Plane()
  def apply(a: Double, b: Double, c: Double, d: Double) = new Plane(a, b, c, d)
}
