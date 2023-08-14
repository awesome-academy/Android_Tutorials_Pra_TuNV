package com.example.droidcafeforexc9

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.droidcafeforexc9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.Toolbar)

        binding.fab.setOnClickListener {
            if (orderMessage == " ") {
                displayToast(getString(R.string.no_order))
            }else {
                val intent = Intent(this, OrderActivity::class.java)
                intent.putExtra(extraMessage, orderMessage)
                startActivity(intent)
            }
        }

        binding.contentLayout.donut.setOnClickListener {
            orderMessage = getString(R.string.donut_order_message)
            displayToast(orderMessage)
        }

        binding.contentLayout.iceCream.setOnClickListener {
            orderMessage = getString(R.string.ice_cream_order_message)
            displayToast(orderMessage)
        }

        binding.contentLayout.froyo.setOnClickListener {
            orderMessage = getString(R.string.froyo_order_message)
            displayToast(orderMessage)
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_order -> displayToast(getString(R.string.action_order_message))
            R.id.action_status -> displayToast(getString(R.string.action_status_message))
            R.id.action_contact -> displayToast(getString(R.string.action_contact_message))
            R.id.action_favorites -> displayToast(getString(R.string.action_favorites_message))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun displayToast(message:String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }


    companion object {
        var orderMessage = " "
        const val extraMessage ="droidcafe.extra.MESSAGE"
    }
}



