package eu.lynxware.math

case class AABox(minBounds: Vec3, maxBounds: Vec3) {
  val center: Vec3 = {
    val x = (maxBounds.x + minBounds.x) / 2.0
    val y = (maxBounds.y + minBounds.y) / 2.0
    val z = (maxBounds.z + minBounds.z) / 2.0
    Vec3(x, y, z)
  }

  val halfSize: Vec3 = {
    val x = maxBounds.x - center.x
    val y = maxBounds.y - center.y
    val z = maxBounds.z - center.z
    Vec3(x, y, z)
  }

  def positiveVertex(normal: Vec3): Vec3 = {
    val result = Vec3(center.x - halfSize.x, center.y - halfSize.y, center.z - halfSize.z)
    val x = if (normal.x > 0) result.x + halfSize.x * 2.0 else result.x
    val y = if (normal.y > 0) result.y + halfSize.y * 2.0 else result.y
    val z = if (normal.z > 0) result.z + halfSize.z * 2.0 else result.z
    Vec3(x, y, z)
  }

  def negativeVertex(normal: Vec3): Vec3 = {
    val result = Vec3(center.x - halfSize.x, center.y - halfSize.y, center.z - halfSize.z)
    val x = if (normal.x < 0) result.x + halfSize.x * 2.0 else result.x
    val y = if (normal.y < 0) result.y + halfSize.y * 2.0 else result.y
    val z = if (normal.z < 0) result.z + halfSize.z * 2.0 else result.z
    Vec3(x, y, z)
  }
}
