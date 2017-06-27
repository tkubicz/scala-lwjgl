package eu.lynxware.lwjgl.model

case class AnimationState(currentAnimation: Animation, animationTime: Double = 0)

class Animator(entity: AnimatedModel) {

  def doAnimation(animation: Animation): AnimationState = AnimationState(animation)

  def update(state: AnimationState, frameTime: Double): Unit = {
    val updatedState = increaseAnimationTime(state, frameTime)
    val currentPose = cal
  }

  def increaseAnimationTime(state: AnimationState, frameTime: Double): Unit = {
    val tempTime = state.animationTime + frameTime
    val newTime = if (tempTime > state.currentAnimation.lenght) tempTime % state.currentAnimation.lenght else tempTime
    state.copy(animationTime = newTime)
  }

  def calculateCurrentAnimationPose(): Unit = {
    val frames = getPreviousAndNextFrames()
  }

}
