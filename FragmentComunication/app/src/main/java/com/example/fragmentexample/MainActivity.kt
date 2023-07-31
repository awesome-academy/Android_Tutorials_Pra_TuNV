package com.example.fragmentexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity(),SimpleFragment.OnFragmentInteractionListener{

    private lateinit var mButton:Button
    private var isFragmentDisplayed = false
    private var fragmentState = "state_of_fragment"
    private var stateChoice ="user_choice"

    private var mRadioButtonChoice = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mButton = findViewById(R.id.open_button)

        if(savedInstanceState != null) {
            isFragmentDisplayed =savedInstanceState.getBoolean(fragmentState)
            mRadioButtonChoice =savedInstanceState.getInt(stateChoice)
            if (isFragmentDisplayed){
                mButton.setText(R.string.close)
            }
        }

        mButton.setOnClickListener {
            if (!isFragmentDisplayed)
                displayFragment()
            else
                closeFragment()
        }
    }


    private fun displayFragment(){
        val simpleFragment = SimpleFragment.newInstance(mRadioButtonChoice)
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

    override fun onRadioButtonChoice(choice:Int) {
        mRadioButtonChoice = choice
        val choiceString = choice.toString()
        Toast.makeText(this@MainActivity, "Choice is $choiceString",Toast.LENGTH_SHORT).show()
    }
    override fun onSaveInstanceState (savedInstanceState: Bundle) {
        savedInstanceState.putBoolean(fragmentState, isFragmentDisplayed)
        super.onSaveInstanceState(savedInstanceState)
    }
}




