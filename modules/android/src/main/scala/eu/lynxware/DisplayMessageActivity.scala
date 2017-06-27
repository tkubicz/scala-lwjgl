package eu.lynxware

import android.app.Activity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView

class DisplayMessageActivity extends Activity {
  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_display_message)

    val intent = getIntent()
    val message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE)
    val textView = new TextView(this)
    textView.setTextSize(40)
    textView.setText(message)

    val layout = findViewById(R.id.activity_display_message).asInstanceOf[ViewGroup]
    layout.addView(textView)
  }
}

