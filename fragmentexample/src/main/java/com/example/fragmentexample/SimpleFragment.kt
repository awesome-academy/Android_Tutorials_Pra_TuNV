package com.example.fragmentexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmentexample.databinding.FragmentSimpleBinding


class SimpleFragment : Fragment() {

    private val binding:FragmentSimpleBinding by lazy {
        FragmentSimpleBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding.radioGroup.setOnCheckedChangeListener { _, _ ->
            val index = binding.radioGroup.checkedRadioButtonId
            when(index) {
                R.id.radio_button_yes -> binding.fragmentHeader.setText(R.string.yes_message)
                R.id.radio_button_no -> binding.fragmentHeader.setText(R.string.no_message)
            }
        }

        return binding.root

    }


    companion object{
        @JvmStatic
        fun newInstance() = SimpleFragment()
    }
}


