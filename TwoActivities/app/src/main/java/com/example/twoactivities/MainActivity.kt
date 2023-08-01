package com.example.twoactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private val logTag =MainActivity::class.simpleName
    private lateinit var mMessageEditText:EditText
    private lateinit var mReplyHeadTextView:TextView
    private lateinit var mReplyTextView:TextView

    companion object {
        const val extraMessage = "com.example.twoactivities.extra.MESSAGE"
        const val textRequest = 1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mMessageEditText = findViewById(R.id.editText_main)
        mReplyHeadTextView = findViewById(R.id.text_header_reply)
        mReplyTextView = findViewById(R.id.text_message_reply)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == textRequest){
            if (resultCode == RESULT_OK) {
                val reply = data?.getStringExtra(SecondActivity.extraReply)
                mReplyHeadTextView.isVisible = true
                mReplyTextView.text = reply
                mReplyTextView.isVisible = true
            }
        }
    }

    fun setOnClickListener(view: View) {
        Log.wtf(logTag, "Button is clicked")
        val intent =Intent(this,SecondActivity::class.java)
        val message = mMessageEditText.text.toString()
        intent.putExtra(extraMessage,message)
        startActivityForResult(intent, textRequest)
    }
}