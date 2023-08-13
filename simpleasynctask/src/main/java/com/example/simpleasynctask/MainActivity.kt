package com.example.simpleasynctask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simpleasynctask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            binding.textView1.setText(R.string.napping)
            SimpleAsyncTask(binding.textView1).execute()}

        if (savedInstanceState != null) {
            binding.textView1.text = savedInstanceState.getString(textState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(textState, binding.textView1.text.toString())
    }

    companion object {
        const val textState = "currentText"
    }
}



