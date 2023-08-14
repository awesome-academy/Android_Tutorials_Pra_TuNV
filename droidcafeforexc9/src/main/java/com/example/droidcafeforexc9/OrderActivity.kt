package com.example.droidcafeforexc9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.droidcafeforexc9.databinding.ActivityOrderBinding

class OrderActivity : AppCompatActivity() {

    private val binding:ActivityOrderBinding by lazy {
        ActivityOrderBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val message = intent.getStringExtra(MainActivity.extraMessage)
        binding.orderTextview.text = message

        binding.radioGroup.setOnCheckedChangeListener{_,_ ->
            val index = binding.radioGroup.checkedRadioButtonId
            when (index) {
                R.id.sameday -> displayToast(getString(R.string.same_day_messenger_service))
                R.id.nextday -> displayToast(getString(R.string.next_day_ground_delivery))
                R.id.pickup -> displayToast(getString(R.string.pick_up))
            }
        }


        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.labels_array,
            android.R.layout.simple_spinner_item )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.labelSpinner.adapter = adapter

        binding.labelSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, long: Long) {
                val spinnerLabel:String = parent?.getItemAtPosition(position).toString()
                displayToast(spinnerLabel)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }


    private fun displayToast(message:String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

}


