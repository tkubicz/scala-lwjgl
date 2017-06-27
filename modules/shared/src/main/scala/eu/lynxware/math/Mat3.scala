package eu.lynxware.math

class Mat3(m: Array[Double]) {
  def apply(index: Int): Double = m(index)

  def +(mat: Mat3): Mat3 = new Mat3(Array(
    m(0) + mat(0), m(1) + mat(1), m(2) + mat(2),
    m(3) + mat(3), m(4) + mat(4), m(5) + mat(5),
    m(6) + mat(6), m(7) + mat(7), m(8) + mat(8)
  ))

  def -(mat: Mat3): Mat3 = new Mat3(Array(
    m(0) - mat(0), m(1) - mat(1), m(2) - mat(2),
    m(3) - mat(3), m(4) - mat(4), m(5) - mat(5),
    m(6) - mat(6), m(7) - mat(7), m(8) - mat(8)
  ))

  def *(mat: Mat3): Mat3 = new Mat3(Array(
    m(0) * mat(0) + m(3) * mat(1) + m(6) * mat(2),
    m(1) * mat(0) + m(4) * mat(1) + m(7) * mat(2),
    m(2) * mat(0) + m(5) * mat(1) + m(8) * mat(2),
    m(0) * mat(3) + m(3) * mat(4) + m(6) * mat(5),
    m(1) * mat(3) + m(4) * mat(4) + m(7) * mat(5),
    m(2) * mat(3) + m(5) * mat(4) + m(8) * mat(5),
    m(0) * mat(6) + m(3) * mat(7) + m(6) * mat(8),
    m(1) * mat(6) + m(4) * mat(7) + m(7) * mat(8),
    m(2) * mat(6) + m(5) * mat(7) + m(8) * mat(8)
  ))

  //def toJsArray = m.toJSArray
}

object Mat3 {
  def identity: Mat3 = new Mat3(Array(1, 0, 0, 0, 1, 0, 0, 0, 1))
  def apply(values: Array[Double]): Mat3 = new Mat3(values)
  def apply(): Mat3 = identity
}
