package com.example.fragmentcomunication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragmentcomunication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SimpleFragment.OnFragmentInteractionListener {

    private var isFragmentDisplayed = false
    private var radioButtonChoice = 2
    private val fragmentState = "state_of_fragment"
    private val choiceState ="user_choice"


    private val binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)


        binding.openButton.setOnClickListener {
            if (!isFragmentDisplayed)
                displayFragment()
            else
                closeFragment()
        }

        if (savedInstanceState != null) {
            isFragmentDisplayed = savedInstanceState.getBoolean(fragmentState)
            radioButtonChoice = savedInstanceState.getInt(choiceState)
        } else if (isFragmentDisplayed) {
            binding.openButton.setText(R.string.close)
        }

    }

    override fun onSaveInstanceState (savedInstanceState: Bundle) {
        savedInstanceState.putBoolean(fragmentState, isFragmentDisplayed)
        super.onSaveInstanceState(savedInstanceState)
    }

    private fun displayFragment(){
        val simpleFragment = SimpleFragment.newInstance(radioButtonChoice)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.apply {
            add(R.id.fragment_container,simpleFragment)
                .addToBackStack(null)
                .commit()
        }
        binding.openButton.setText(R.string.close)
        isFragmentDisplayed = true
    }

    private fun closeFragment() {
        val simpleFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (simpleFragment != null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.apply {
                remove(simpleFragment)
                    .commit()
            }
        }
        binding.openButton.setText(R.string.open)
        isFragmentDisplayed = false
    }

    override fun onRadioButtonChoice(choice:Int) {
        radioButtonChoice = choice
    }
}


