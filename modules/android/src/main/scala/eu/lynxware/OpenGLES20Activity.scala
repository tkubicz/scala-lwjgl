package eu.lynxware

import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

import android.app.Activity
import android.content.Context
import android.opengl.{GLES20, GLSurfaceView}
import android.os.Bundle

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
    GLES20.glClearColor(1.0f, 0.0f, 0.0f, 1.0f)
  }

  override def onDrawFrame(gl10: GL10): Unit = {
    GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
  }

  override def onSurfaceChanged(unused: GL10, width: Int, height: Int): Unit = {
    GLES20.glViewport(0, 0, width, height)
  }
}