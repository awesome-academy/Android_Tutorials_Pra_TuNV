package com.example.twoactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.twoactivities.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var mReply:EditText
    private lateinit var binding: ActivitySecondBinding

    companion object {
        const val extraReply = "com.example.twoactivities.extra.REPLY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mReply = binding.editTextSecond

        val message = intent.getStringExtra(MainActivity.extraMessage)
        val textView = binding.textMessage
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