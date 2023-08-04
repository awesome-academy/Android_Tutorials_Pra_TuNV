package com.example.fragmentcomunication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmentcomunication.databinding.FragmentSimpleBinding


class SimpleFragment : Fragment() {

    private var radioButtonChoice = 2
    private var choiceString ="choice"


    private val binding:FragmentSimpleBinding by lazy {
        FragmentSimpleBinding.inflate(layoutInflater)
    }

    interface OnFragmentInteractionListener {
        fun onRadioButtonChoice(choice:Int)
    }

    private var listener:OnFragmentInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw ClassCastException(context.toString() + getString(R.string.exception_message))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

    if (arguments?.containsKey(choiceString) == true) {
        radioButtonChoice = arguments?.getInt(choiceString)!!
        if(radioButtonChoice != 2) {
            binding.radioGroup.check(binding.radioGroup.getChildAt(radioButtonChoice).id)
        }
    }


        binding.radioGroup.setOnCheckedChangeListener { _, _ ->
            val index = binding.radioGroup.checkedRadioButtonId
            when(index) {
                R.id.radio_button_yes -> {
                    binding.fragmentHeader.setText(R.string.yes_message)
                    radioButtonChoice = yes
                    listener?.onRadioButtonChoice(yes)
                }
                R.id.radio_button_no -> {
                    binding.fragmentHeader.setText(R.string.no_message)
                    radioButtonChoice = no
                    listener?.onRadioButtonChoice(no)
                }
                else ->{
                    radioButtonChoice = 2
                    listener?.onRadioButtonChoice(2)
                }
            }
        }

        return binding.root

    }


    companion object{
        const val yes = 0
        const val no = 1

        @JvmStatic
        fun newInstance(choice: Int) = SimpleFragment().apply {
            arguments = Bundle().apply {
                putInt(choiceString, choice)
            }
            return@apply
        }
    }
}


