package com.example.twoactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    private lateinit var mReply:EditText

    companion object {
        const val extraReply = "com.example.twoactivities.extra.REPLY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        mReply = findViewById(R.id.editText_second)

        val intent = intent
        val message = intent.getStringExtra(MainActivity.extraMessage)
        val textView = findViewById<TextView>(R.id.text_message)
        textView.text = message
    }

    fun returnReply(view: View) {
        val reply = mReply.text.toString()
        val replyIntent = intent
        replyIntent.putExtra(extraReply, reply)
        setResult(RESULT_OK, replyIntent)
        finish()
    }
}