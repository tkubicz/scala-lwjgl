package eu.lynxware

import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

import android.app.Activity
import android.content.Context
import android.opengl.GLSurfaceView
import android.os.Bundle
import eu.lynxware.opengl.GL20Binding._

class OpenGLES20Activity extends Activity {
  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)

    // Create a GLSurfaceView instance and set it as the ContentView for this Activity
    val glView = new MyGLSurfaceView(this)
    setContentView(glView)
  }
}

class MyGLSurfaceView(context: Context) extends GLSurfaceView(context) {
  setEGLContextClientVersion(2)
  val renderer = new MyGLRenderer()
  setRenderer(renderer)
}

class MyGLRenderer extends GLSurfaceView.Renderer {

  override def onSurfaceCreated(gl10: GL10, eglConfig: EGLConfig): Unit = {
    clearColor(1.0f, 0.0f, 0.0f, 1.0f)
  }

  override def onDrawFrame(gl10: GL10): Unit = {
    clear(ColorBufferBit)
  }

  override def onSurfaceChanged(unused: GL10, width: Int, height: Int): Unit = {
    viewport(0, 0, width, height)
  }
}