package com.example.fragmentexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var mButton:Button
    private var isFragmentDisplayed = false
    private val fragmentState = "state_of_fragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mButton = findViewById(R.id.open_button)
        mButton.setOnClickListener {
            if (!isFragmentDisplayed)
                displayFragment()
            else
                closeFragment()
        }

        if (savedInstanceState != null) {
            isFragmentDisplayed = savedInstanceState.getBoolean(fragmentState)
        } else if (isFragmentDisplayed) {
            mButton.setText(R.string.close)
        }
    }

    override fun onSaveInstanceState (savedInstanceState: Bundle) {
        savedInstanceState.putBoolean(fragmentState, isFragmentDisplayed)
        super.onSaveInstanceState(savedInstanceState)
    }

    private fun displayFragment(){
        val simpleFragment = SimpleFragment.newInstance()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.apply {
            add(R.id.fragment_container,simpleFragment)
                .addToBackStack(null)
                .commit()
        }
        mButton.setText(R.string.close)
        isFragmentDisplayed = true
    }

    private fun closeFragment() {
        val fragmentManager = supportFragmentManager
        val simpleFragment = fragmentManager.findFragmentById(R.id.fragment_container)
        if (simpleFragment != null) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.apply {
                remove(simpleFragment)
                    .commit()
            }
        }
        mButton.setText(R.string.open)
        isFragmentDisplayed = false
    }
}




