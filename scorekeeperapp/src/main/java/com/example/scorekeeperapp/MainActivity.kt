package com.example.scorekeeperapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.example.scorekeeperapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.decreaseTeam1.setOnClickListener {decreaseScore(binding.decreaseTeam1)}
        binding.decreaseTeam2.setOnClickListener {decreaseScore(binding.decreaseTeam2)}
        binding.increaseTeam1.setOnClickListener {increaseScore(binding.increaseTeam1)}
        binding.increaseTeam2.setOnClickListener {increaseScore(binding.increaseTeam2)}

        if (savedInstanceState != null) {
            score1 = savedInstanceState.getInt(stateScore1)
            score2 = savedInstanceState.getInt(stateScore2)
            binding.score1.text = score1.toString()
            binding.score2.text = score2.toString()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val nightMode = AppCompatDelegate.getDefaultNightMode()
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode)
        }else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.night_mode){
            val nightMode = AppCompatDelegate.getDefaultNightMode()
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            recreate()
        }
        return true
    }

    private fun decreaseScore(view: View) {
        when(view.id) {
            R.id.decreaseTeam1 -> {
                score1--
                binding.score1.text = score1.toString()
            }
            R.id.decreaseTeam2 -> {
                score2--
                binding.score2.text = score2.toString()
            }
        }
    }

    private fun increaseScore(view: View) {
        when(view.id) {
            R.id.increaseTeam1 -> {
                score1++
                binding.score1.text = score1.toString()
            }
            R.id.increaseTeam2 -> {
                score2++
                binding.score2.text = score2.toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(stateScore1, score1)
        outState.putInt(stateScore2, score2)
        super.onSaveInstanceState(outState)
    }

    companion object {
        var score1 = 0
        var score2 = 0
        const val stateScore1 = "Team 1 Score"
        const val stateScore2 = "Team 2 Score"
    }
}


