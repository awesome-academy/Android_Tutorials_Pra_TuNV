package com.example.dialogandpicker

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar


class DatePickerFragment : DialogFragment(),DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return activity?.let { DatePickerDialog(it, this, year, month, day) }!!
    }
    override fun onDateSet(datePicker: DatePicker?, year: Int, month: Int, day: Int) {
        val mainActivity = activity as? MainActivity
        mainActivity?.processDatePickerResult(year, month, day)
    }
}


