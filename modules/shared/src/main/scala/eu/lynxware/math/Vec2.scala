package eu.lynxware.math

class Vec2(val x: Double = 0.0, val y: Double = 0.0) {
  override def toString: String = "(" + x + "," + y + ")"
}

object Vec2 {
  def apply(x: Double, y: Double): Vec2 = new Vec2(x, y)
  def apply(): Vec2 = new Vec2()
}
