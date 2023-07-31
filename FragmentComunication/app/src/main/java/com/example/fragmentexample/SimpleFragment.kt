package com.example.fragmentexample

import android.content.Context
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
    private var none = 2
    private var mRadioButtonChoice = none
    private var choiceString ="choice"

    interface OnFragmentInteractionListener {
        fun onRadioButtonChoice(choice:Int)
    }
    private var mListener:OnFragmentInteractionListener? = null


    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.apply {
        }
    }*/
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener){
            mListener = context
        } else {
            throw ClassCastException(context.toString() + getString(R.string.exception_message))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.simple_fragment, container, false)
        val radioGroup = rootView.findViewById<RadioGroup>(R.id.radio_group)

        if (requireArguments().containsKey(choiceString)) {
            mRadioButtonChoice = requireArguments().getInt(choiceString)
            if (mRadioButtonChoice != none) {
                radioGroup.check(radioGroup.getChildAt(mRadioButtonChoice).id)
            }
        }

        radioGroup.setOnCheckedChangeListener { _, checkId ->
            val radioButton = radioGroup.findViewById<View>(checkId)
            val index = radioGroup.indexOfChild(radioButton)
            val textView = rootView.findViewById<TextView>(R.id.fragment_header)
            when (index) {
                yes -> { textView.setText(R.string.yes_message)
                        mRadioButtonChoice = yes
                        mListener?.onRadioButtonChoice(yes)}

                no -> {textView.setText(R.string.no_message)
                        mRadioButtonChoice = no
                        mListener?.onRadioButtonChoice(no)}

                else -> {mRadioButtonChoice = none
                        mListener?.onRadioButtonChoice(none)}
            }
        }
        return rootView
    }


    companion object{
        @JvmStatic
        fun newInstance(choice:Int) =
            SimpleFragment().apply {
                arguments = Bundle().apply {
                   putInt(choiceString,choice)
                }
                return@apply
            }
    }
}

