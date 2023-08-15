package com.example.powerreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.powerreceiver.MainActivity.Companion.ACTION_CUSTOM_BROADCAST

class CustomReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val intentAction = intent.action
        if (intentAction != null) {
            var toastMessage = context.getString(R.string.unknown_intent)

            when (intentAction) {
                Intent.ACTION_POWER_CONNECTED -> toastMessage = context.getString(R.string.power_connected)
                Intent.ACTION_POWER_DISCONNECTED -> toastMessage = context.getString(R.string.power_disconnected)
                ACTION_CUSTOM_BROADCAST -> toastMessage = context.getString(R.string.custom_broadcast_received)
            }

            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        }
    }
}


