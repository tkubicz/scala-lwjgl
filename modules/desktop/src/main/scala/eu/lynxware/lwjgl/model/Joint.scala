package eu.lynxware.lwjgl.model

import eu.lynxware.math.{Mat4, Quaternion, Vec3}

case class Joint(index: Int, name: String, children: Seq[Joint]) {
  val animatedTransform = Mat4()
  val localBindTransform = Mat4()
  val inverseBindTransform = Mat4()

  def addChild(child: Joint): Joint = this.copy(children = children :+ child)

  def calculateInverseBindTransform(parentBindTransform: Mat4): Unit = {
    val bindTransform = parentBindTransform * localBindTransform


  }
}

case class JointTransform(position: Vec3, rotation: Quaternion) {
  lazy val localTransform: Mat4 = Mat4.translationMatrix(position) * rotation.rotationMatrix
}

object JointTransform {
  def apply(position: Vec3, rotation: Quaternion): JointTransform = new JointTransform(position, rotation)

  def interpolate(frameA: JointTransform, frameB: JointTransform, progression: Double): Unit = {
    val pos = interpolate(frameA.position, frameB.position, progression)
    val rot = Quaternion.interpolate(frameA.rotation, frameB.rotation, progression)
    JointTransform(pos, rot)
  }

  def interpolate(start: Vec3, end: Vec3, progression: Double): Vec3 = {
    val x = start.x + (end.x - start.x) * progression
    val y = start.y + (end.y - start.y) * progression
    val z = start.z + (end.z - start.z) * progression
    Vec3(x, y, z)
  }
}