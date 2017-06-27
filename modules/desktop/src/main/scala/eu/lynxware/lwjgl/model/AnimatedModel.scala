package eu.lynxware.lwjgl.model

import eu.lynxware.math.Mat4
import eu.lynxware.misc.Disposable

class AnimatedModel(model: Vao, texture: Texture, rootJoint: Joint, jointCount: Int) extends Disposable {

  private val animator = new Animator(this)
  rootJoint.calculateInverseBindTransform(Mat4())

  override def dispose(): Unit = {
    model.delete()
    texture.delete()
  }

  def doAnimation(animation: Animation): Unit = {
    animator.doAnimation(animation)
  }

  def update(): Unit = {
    animator.update()
  }

  def getJointTransforms(): Mat4 = ???

  def addJointsToArray(headJoint: Joint, jointMatrices: Array[Mat4]): Unit = ???
}
