package com.example.whowroteit

import android.net.Uri
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class NetworkUtils {

    companion object{
        private const val bookBaseURL = "https://www.googleapis.com/books/v1/volumes?"
        private const val queryParam = "q"
        private const val maxResult = "maxResults"
        private const val printType = "printType"

        fun getBookInfo(queryString:String?): String? {

            var urlConnection:HttpURLConnection? = null
            var reader:BufferedReader? = null
            var bookJSONString:String? = null

            try {
                val buildURI = Uri.parse(bookBaseURL).buildUpon()
                buildURI.apply {
                    appendQueryParameter(queryParam, queryString)
                    appendQueryParameter(maxResult, "10")
                    appendQueryParameter(printType, "books")
                }

                val requestURL = URL(buildURI.toString())

                urlConnection = requestURL.openConnection() as HttpURLConnection
                urlConnection.apply {
                    requestMethod = "GET"
                    connect()
                }

                val inputStream = urlConnection.inputStream
                reader = BufferedReader(InputStreamReader(inputStream))

                val builder = StringBuilder()
                var line:String?
                while (reader.readLine().also { line = it } != null) {
                    builder.append(line)
                }
                if (builder.isEmpty()) {
                    return null
                }

                bookJSONString = builder.toString()

            } catch (e:IOException) {
                e.printStackTrace()

            } finally {
                urlConnection?.disconnect()
                reader?.close()
            }

            return bookJSONString
        }
    }
}


