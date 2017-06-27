package eu.lynxware

import org.scalajs.dom

object ErrorHandling {
  def logMessage(msg: String): Unit = {
    dom.window.alert(msg)
    println (msg)
  }
}
