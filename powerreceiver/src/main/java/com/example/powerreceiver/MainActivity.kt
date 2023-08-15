package com.example.powerreceiver

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.powerreceiver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val receiver = CustomReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        this.registerReceiver(receiver, filter)
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,
            IntentFilter(ACTION_CUSTOM_BROADCAST)
        )

        binding.sendBroadcast.setOnClickListener {
            val customBroadcastIntent = Intent(ACTION_CUSTOM_BROADCAST)
            LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent)
        }
    }

    override fun onDestroy() {

        this.unregisterReceiver(receiver)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
        super.onDestroy()
    }

    companion object {
        const val ACTION_CUSTOM_BROADCAST = "com.example.powerreceiver.ACTION_CUSTOM_BROADCAST"
    }
}


