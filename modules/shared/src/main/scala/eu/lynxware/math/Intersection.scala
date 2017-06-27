package eu.lynxware.math

object Intersection {

  // TODO: This method is not optimized and can be optimized. Do it in a spare time.
  def intersect(r: Ray, aaBox: AABox, t0: Double = 0.01, t1: Double = 1000.0): Boolean = {
    var tmin = (aaBox.minBounds.x - r.position.x) / r.direction.x
    var tmax = (aaBox.maxBounds.x - r.position.x) / r.direction.x

    if (tmin > tmax) {
      val tmp = tmin
      tmin = tmax
      tmax = tmp
    }

    var tymin = (aaBox.minBounds.y - r.position.y) / r.direction.y
    var tymax = (aaBox.maxBounds.y - r.position.y) / r.direction.y

    if (tymin > tymax){
      val tmp = tymin
      tymin = tymax
      tymax = tmp
    }

    if ((tmin > tymax) || (tymin > tmax)) return false

    if (tymin > tmin) tmin = tymin
    if (tymax < tmax) tmax = tymax

    var tzmin = (aaBox.minBounds.z - r.position.z) / r.direction.z
    var tzmax = (aaBox.maxBounds.z - r.position.z) / r.direction.z

    if (tzmin > tzmax) {
      val tmp = tzmin
      tzmin = tzmax
      tzmax = tmp
    }

    if ((tmin > tzmax) || (tzmin > tmax)) return false

    if (tzmin > tmin) tmin = tzmin
    if (tzmax < tmax) tmax = tzmax

    true
  }
}
