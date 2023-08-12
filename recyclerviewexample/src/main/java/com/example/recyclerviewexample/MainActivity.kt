package com.example.recyclerviewexample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerviewexample.databinding.ActivityMainBinding
import java.util.LinkedList

class MainActivity : AppCompatActivity() {

    private val wordList = LinkedList<String>()

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        for (i in 0..20) {
            wordList.addLast("Word$i")
        }

        val wordListAdapter = WordListAdapter(wordList)
        binding.contentLayout.recyclerView.apply {
            adapter = wordListAdapter
        }

        binding.fab.setOnClickListener {
            val wordListSize = wordList.size
            wordList.addLast("+ Word $wordListSize")
            binding.contentLayout.recyclerView.apply {
                adapter?.notifyItemInserted(wordListSize)
                smoothScrollToPosition(wordListSize)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}


