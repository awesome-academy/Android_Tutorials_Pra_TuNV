package com.example.fragmentexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView



class SimpleFragment : Fragment() {

    private var yes = 1
    private var no = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.simple_fragment, container, false)
        val radioGroup = rootView.findViewById<RadioGroup>(R.id.radio_group)

        radioGroup.setOnCheckedChangeListener { _, checkId ->
            val radioButton = radioGroup.findViewById<View>(checkId)
            val index = radioGroup.indexOfChild(radioButton)
            val textView = rootView.findViewById<TextView>(R.id.fragment_header)
            when (index) {
                yes -> textView.setText(R.string.yes_message)
                no -> textView.setText(R.string.no_message)
            }
        }
        return rootView
    }


    companion object{
        @JvmStatic
        fun newInstance() = SimpleFragment().apply {
        }
    }
}