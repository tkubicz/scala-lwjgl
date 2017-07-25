package eu.lynxware

import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

import android.app.Activity
import android.content.Context
import android.opengl.GLSurfaceView
import android.os.Bundle
import eu.lynxware.opengl.GL20Binding
import eu.lynxware.opengl.GL20Binding._
import eu.lynxware.playground.CreateShader
import org.slf4j.LoggerFactory

class OpenGLES20Activity extends Activity {

  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)

    // Create a GLSurfaceView instance and set it as the ContentView for this Activity
    val glView = new MyGLSurfaceView(this)
    setContentView(glView)
  }
}

class MyGLSurfaceView(context: Context) extends GLSurfaceView(context) {

  val logger = LoggerFactory.getLogger(this.getClass)
  logger.info("Creating GL Surface View")

  setEGLContextClientVersion(2)
  val renderer = new MyGLRenderer()
  setRenderer(renderer)
}

class MyGLRenderer extends GLSurfaceView.Renderer {

  val logger = LoggerFactory.getLogger(this.getClass)

  override def onSurfaceCreated(gl10: GL10, eglConfig: EGLConfig): Unit = {
    logger.info("Surface created")

    clearColor(0.5f, 0.5f, 1.0f, 0.0f)
    new CreateShader(GL20Binding)
  }

  override def onDrawFrame(gl10: GL10): Unit = {
    clear(ColorBufferBit)
  }

  override def onSurfaceChanged(unused: GL10, width: Int, height: Int): Unit = {
    viewport(0, 0, width, height)
  }
}