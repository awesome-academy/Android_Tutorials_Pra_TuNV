package com.example.tabexperiment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tabexperiment.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private val binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.tabLayout.apply {
            addTab(this.newTab().setText(R.string.tab_label1))
            addTab(this.newTab().setText(R.string.tab_label2))
            addTab(this.newTab().setText(R.string.tab_label3))
            tabGravity = TabLayout.GRAVITY_FILL
        }

        val adapter = PagerAdapter(supportFragmentManager,binding.tabLayout.tabCount)
        binding.pager.adapter = adapter

        binding.pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))
        binding.tabLayout.addOnTabSelectedListener (object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.pager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}


