package com.example.implicitintents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.implicitintents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        binding.websiteButton.setOnClickListener { websiteListener() }
        binding.mapButton.setOnClickListener { mapListener() }
        binding.snippetButton.setOnClickListener { snippetListener() }

    }


    private fun websiteListener() {
        val url = binding.websiteText.text.toString()
        val intent =Intent(Intent.ACTION_VIEW, Uri.parse("https://$url"))

        if(intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }


    private fun mapListener() {
        val geo = binding.mapText.text.toString()
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$geo"))

        if(intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }


    private fun snippetListener() {
        val snippet = binding.snippetText.text.toString()
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, snippet)
            type = "text/plain"
        }

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

}


