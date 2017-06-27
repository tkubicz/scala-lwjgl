package eu.lynxware.lwjgl.model

case class KeyFrame(timestamp: Double, pose: Map[String, JointTransform])

case class Animation(lenght: Double, keyFrames: Array[KeyFrame])
