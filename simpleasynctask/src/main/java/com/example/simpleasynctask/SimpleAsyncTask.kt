package com.example.simpleasynctask

import android.os.AsyncTask
import android.widget.TextView
import java.lang.ref.WeakReference
import kotlin.random.Random

class SimpleAsyncTask(textView: TextView) : AsyncTask<Unit, Unit, String>() {

    private val view:WeakReference<TextView>

    init {
        view = WeakReference(textView)
    }

    override fun doInBackground(vararg arg: Unit?): String {
        val sleepTime = Random.nextInt(21)*200
        try {
            Thread.sleep(sleepTime.toLong())
        } catch (e:InterruptedException) {
            e.printStackTrace()
        }

        return "Awake at last after sleeping for $sleepTime milliseconds!"
    }

    override fun onPostExecute(result:String) {
        view.get()?.text = result
    }
}


