package com.example.twoactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.twoactivities.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private val binding: ActivitySecondBinding by lazy{
        ActivitySecondBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        val message = intent.getStringExtra(MainActivity.extraMessage)
        val textView = binding.textMessage
        textView.text = message
    }

    fun returnReply(view: View) {
        val reply = binding.editTextSecond.text.toString()
        val replyIntent = intent
        replyIntent.putExtra(extraReply, reply)
        setResult(RESULT_OK, replyIntent)
        finish()
    }

    companion object {
        const val extraReply = "com.example.twoactivities.extra.REPLY"
    }

}

