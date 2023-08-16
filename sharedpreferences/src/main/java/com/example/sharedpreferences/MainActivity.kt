package com.example.sharedpreferences

import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var count = 0
    private var color = 0
    private lateinit var showCountTextView:TextView
    private lateinit var preferences: SharedPreferences

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            blackBackgroundButton.setOnClickListener { changeBackground(it) }
            redBackgroundButton.setOnClickListener { changeBackground(it) }
            blueBackgroundButton.setOnClickListener { changeBackground(it) }
            greenBackgroundButton.setOnClickListener { changeBackground(it) }
        }
        binding.countButton.setOnClickListener { countUp() }
        binding.resetButton.setOnClickListener { reset() }

        showCountTextView = binding.countTextview
        color =ContextCompat.getColor(this, R.color.default_background)

        preferences =getSharedPreferences(sharedPrefFile, MODE_PRIVATE)

        count = preferences.getInt(COUNT_KEY, 0)
        showCountTextView.text = String.format("%s", count)
        color = preferences.getInt(COLOR_KEY, color)
        showCountTextView.setBackgroundColor(color)

    }

    override fun onPause() {
        super.onPause()
        val preferencesEditor =preferences.edit()
        preferencesEditor.apply {
            putInt(COUNT_KEY, count)
            putInt(COLOR_KEY, color)
            apply()
        }
    }

    private fun changeBackground(view: View) {
        val backgroundColor = (view.background as ColorDrawable).color
        showCountTextView.setBackgroundColor(backgroundColor)
        color = backgroundColor
    }

    private fun countUp() {
        count++
        showCountTextView.text = String.format("%s", count)
    }

    private fun reset() {
        count = 0
        showCountTextView.text = String.format("%s", count)

        color =ContextCompat.getColor(this, R.color.default_background)
        showCountTextView.setBackgroundColor(color)

        val preferencesEditor = preferences.edit()
        preferencesEditor.apply {
            clear()
            apply()
        }
    }

    companion object {
        private const val COUNT_KEY ="count"
        private const val COLOR_KEY = "color"
        private const val sharedPrefFile = "com.example.android.hellosharedprefs"
    }
}


