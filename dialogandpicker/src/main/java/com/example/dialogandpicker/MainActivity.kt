package com.example.dialogandpicker

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.dialogandpicker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.alertButton.setOnClickListener {
            val alertBuilder = AlertDialog.Builder(this)
            alertBuilder.apply {
                setTitle(getString(R.string.alert_title))
                setMessage(getString(R.string.alert_message))
                setPositiveButton(getString(R.string.pos_nutton_text)) { _, _ ->
                    displayToast(getString(R.string.pos_button_toast))
                }
                setNegativeButton(getString(R.string.neg_button_text)) { _, _ ->
                    displayToast(getString(R.string.neg_button_toast))
                }
                show()
            }
        }

        binding.datePickerButton.setOnClickListener {
            DatePickerFragment().show(supportFragmentManager,"datePicker")
        }
    }

    fun processDatePickerResult(year:Int ,month:Int ,day:Int) {
        val dateMessage = String.format("%d/%d/%d", day, month+1, year)
        displayToast("Date is: $dateMessage")
    }

    private fun displayToast(message:String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}


