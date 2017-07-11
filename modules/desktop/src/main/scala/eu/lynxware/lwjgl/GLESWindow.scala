package eu.lynxware.lwjgl

import eu.lynxware.lwjgl.opengl.GLES20Binding
import eu.lynxware.playground.CreateShader
import org.lwjgl.egl._
import org.lwjgl.glfw.GLFW._
import org.lwjgl.glfw.GLFWNativeEGL._
import org.lwjgl.glfw.{Callbacks, GLFWErrorCallback, GLFWKeyCallback}
import org.lwjgl.egl.EGL10._
import org.lwjgl.opengles.GLES20._
import org.lwjgl.opengles.{GLES, GLESCapabilities}
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil._

object GLESWindow extends App {

  GLFWErrorCallback.createPrint.set()
  if (!glfwInit()) {
    throw new IllegalStateException("Unable to initialize glfw")
  }

  glfwDefaultWindowHints()
  glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
  glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)

  // GLFW setup for EGL & OpenGL ES
  glfwWindowHint(GLFW_CONTEXT_CREATION_API, GLFW_EGL_CONTEXT_API)
  glfwWindowHint(GLFW_CLIENT_API, GLFW_OPENGL_ES_API)
  glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 2)
  glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 0)

  val width = 300
  val height = 300

  val window: java.lang.Long = glfwCreateWindow(width, height, "GLFW ES/OpenGL ES Demo", NULL, NULL)
  if (window == null) throw new RuntimeException("Failed to create the GLFW window")

  glfwSetKeyCallback(window, new GLFWKeyCallback() {
    override def invoke(windowHnd: Long, key: Int, scanCode: Int, action: Int, mods: Int): Unit = {
      if (action == GLFW_RELEASE && key == GLFW_KEY_ESCAPE) glfwSetWindowShouldClose(windowHnd, true)
    }
  })

  // EGL capabilities
  val dpy = glfwGetEGLDisplay()

  val stack = MemoryStack.stackPush()
  val major = stack.mallocInt(1)
  val minor = stack.mallocInt(1)

  if (!eglInitialize(dpy, major, minor)) throw new IllegalStateException("Failed to intiialize EGL")

  val egl = EGL.createDisplayCapabilities(dpy, major.get(0), minor.get(0))

  println("EGL Capabilities:")
  classOf[EGLCapabilities].getFields.filter(f => f.getType == classOf[Boolean] && f.get(egl) == true.asInstanceOf[AnyRef]).foreach(println)

  // OpenGL ES capabilities
  glfwMakeContextCurrent(window)
  val gles = GLES.createCapabilities()

  println("OpenGL ES Capabilities:")
  classOf[GLESCapabilities].getFields.filter(f => f.getType == classOf[Boolean] && f.get(gles) == true.asInstanceOf[AnyRef]).foreach(println)

  println("GL_VENDOR: " + glGetString(GL_VENDOR))
  println("GL_COLOR_BUFFER: " + glGetString(GL_VERSION))
  println("GL_RENDERER: " + glGetString(GL_RENDERER))

  // Render with OpenGL ES
  glfwShowWindow(window)

  new CreateShader(GLES20Binding)

  glClearColor(0.0f, 0.5f, 1.0f, 0.0f)
  while (!glfwWindowShouldClose(window)) {
    glfwPollEvents()

    glClear(GL_COLOR_BUFFER_BIT)
    glfwSwapBuffers(window)
  }

  Callbacks.glfwFreeCallbacks(window)
  glfwTerminate()
}