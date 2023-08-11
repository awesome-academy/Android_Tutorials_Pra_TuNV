package com.example.tabexperiment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PagerAdapter(fm: FragmentManager, NumOfTabs:Int) : FragmentStatePagerAdapter(fm, NumOfTabs) {

    private val numOfTabs = NumOfTabs

    override fun getCount(): Int {
         return numOfTabs
     }

     override fun getItem(position: Int): Fragment {
         return when (position) {
             0 -> TabFragment1()
             1 -> TabFragment2()
             2 -> TabFragment3()
             else -> TabFragment1()
         }
     }

}


