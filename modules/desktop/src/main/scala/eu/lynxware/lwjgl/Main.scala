package eu.lynxware.lwjgl

import eu.lynxware.math.Mat4
import org.lwjgl.glfw.GLFW._
import org.lwjgl.glfw._
import org.lwjgl.opengl.GL11._
import org.lwjgl.opengl.GL._
import org.lwjgl.system.MemoryUtil._

object Main extends App {

  private var window: java.lang.Long = null

  run()

  def run(): Unit = {
    println("Hello LWJGL ")

    val mat = Mat4()
    println(mat)

    try {
      init()
      loop()

      glfwDestroyWindow(window)
    } finally {
      glfwTerminate()
      glfwSetErrorCallback(null).free()
    }
  }

  def init(): Unit = {
    GLFWErrorCallback.createPrint(System.err).set()

    if (!GLFW.glfwInit()) {
      throw new IllegalStateException("Unable to initialize GLFW")
    }

    // Configure window
    GLFW.glfwDefaultWindowHints()
    GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE)
    GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE)

    val width, height = 300

    // Create the window
    window = glfwCreateWindow(width, height, "Hello World", NULL, NULL)
    if (window == null) {
      throw new RuntimeException("Failed to create GLFW window")
    }

    //glfwSetKeyCallback(window,

    // Get the resolution of the primary monitor
    val vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor())

    // Center the window
    glfwSetWindowPos(window, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2)

    // Make the OpenGL current context
    glfwMakeContextCurrent(window)

    // Enable v-sync
    glfwSwapInterval(1)

    // Make the window visible
    glfwShowWindow(window)
  }

  def loop(): Unit = {
    createCapabilities()

    glClearColor(1.0f, 0.0f, 0.0f, 0.0f)

    while (!glfwWindowShouldClose(window)) {
      glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)
      glfwSwapBuffers(window)
      glfwPollEvents()
    }
  }
}