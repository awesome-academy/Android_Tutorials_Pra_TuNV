package com.example.twoactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.twoactivities.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mMessageEditText:EditText
    private lateinit var mReplyHeadTextView:TextView
    private lateinit var mReplyTextView:TextView
    private lateinit var binding:ActivityMainBinding

    companion object {
        const val extraMessage = "com.example.twoactivities.extra.MESSAGE"
        const val textRequest = 1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mMessageEditText = binding.editTextMain
        mReplyHeadTextView = binding.textHeaderReply
        mReplyTextView = binding.textMessageReply

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
        val intent =Intent(this,SecondActivity::class.java)
        val message = mMessageEditText.text.toString()
        intent.putExtra(extraMessage,message)
        startActivityForResult(intent, textRequest)
    }
}



