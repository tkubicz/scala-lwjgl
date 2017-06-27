package eu.lynxware

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText

class MainActivity extends AppCompatActivity {

  override def onCreate(savedInstanceState: android.os.Bundle): Unit = {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  def sendMessage(view: View): Unit = {
    val intent = new Intent(this, classOf[DisplayMessageActivity])
    val editText= findViewById(R.id.edit_message).asInstanceOf[EditText]
    val message = editText.getText.toString
    intent.putExtra(MainActivity.EXTRA_MESSAGE, message)
    startActivity(intent)
  }
}

object MainActivity {
  val EXTRA_MESSAGE = "eu.lynxware.myfirstapp.MESSAGE"
}
