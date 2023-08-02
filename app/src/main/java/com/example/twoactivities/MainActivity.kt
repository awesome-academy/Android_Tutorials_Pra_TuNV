package com.example.twoactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import androidx.core.view.isVisible
import com.example.twoactivities.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == textRequest){
            if (resultCode == RESULT_OK) {
                val reply = data?.getStringExtra(SecondActivity.extraReply)
                binding.textHeaderReply.isVisible = true
                binding.textMessageReply.text = reply
                binding.textMessageReply.isVisible = true
            }
        }
    }

    fun setOnClickListener(view: View) {
        val intent =Intent(this,SecondActivity::class.java)
        val message = binding.editTextMain.text.toString()
        intent.putExtra(extraMessage,message)
        startActivityForResult(intent, textRequest)
    }

    companion object {
        const val extraMessage = "com.example.twoactivities.extra.MESSAGE"
        const val textRequest = 1
    }
}


