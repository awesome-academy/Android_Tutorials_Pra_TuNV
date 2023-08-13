package com.example.whowroteit

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import com.example.whowroteit.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<String> {

    private val binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (supportLoaderManager.getLoader<String>(0) != null) {
            supportLoaderManager.initLoader(0,null,this)
        }

        binding.searchButton.setOnClickListener {

            val queryString = binding.bookInput.text.toString()

            val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(it.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo

            if (networkInfo != null) {
                if (networkInfo.isConnected && queryString.isNotEmpty()) {
                    val queryBundle = Bundle()
                    queryBundle.putString(queryMessage, queryString)
                    supportLoaderManager.restartLoader(0, queryBundle, this)
                    binding.authorText.text = " "
                    binding.titleText.setText(R.string.loading)

                } else {
                    checkStatus(queryString)
                }
            }
        }
    }

    private fun checkStatus (queryString:String) {
        if (queryString.isEmpty()) {
            binding.authorText.text = " "
            binding.titleText.setText(R.string.no_search_term)

        } else {
            binding.authorText.text = " "
            binding.titleText.setText(R.string.no_network)
        }
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<String> {
        var queryString = " "
        if (args != null) {
            queryString = args.getString(queryMessage).toString()
        }

        return BookLoader(this, queryString)
    }

    override fun onLoadFinished(loader: Loader<String>, data: String) {
        try {
            val jsonObject = JSONObject(data)
            val itemsArray = jsonObject.getJSONArray("items")

            var i = 2
            var title:String? = null
            var authors:String? = null

            while (i < itemsArray.length() && authors == null && title == null) {
                val book = itemsArray.getJSONObject(i)
                val volumeInfo = book.getJSONObject(infoString)

                title = volumeInfo.getString(titleString)
                authors = volumeInfo.getString(authorString)

                i++
            }

            if(title != null && authors != null) {
                binding.titleText.text = title
                binding.authorText.text = authors
            } else {
                binding.titleText.setText(R.string.no_results)
                binding.authorText.text = " "
            }

        }catch (e:Exception) {
            binding.titleText.setText(R.string.no_results)
            binding.authorText.text = " "
            e.printStackTrace()
        }
    }

    override fun onLoaderReset(loader: Loader<String>) {
    }

    companion object {
        const val queryMessage = "queryString"
        const val infoString = "volumeInfo"
        const val titleString = "title"
        const val authorString = "authors"
    }
}


