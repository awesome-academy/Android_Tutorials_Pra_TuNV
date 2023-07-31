package com.example.intents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText


class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
    }

    fun openWebsite(view: View) {
        val url = editText.text.toString()
        val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://$url"))
        if (urlIntent.resolveActivity(packageManager) != null){
            startActivity(urlIntent)
        }
    }
}