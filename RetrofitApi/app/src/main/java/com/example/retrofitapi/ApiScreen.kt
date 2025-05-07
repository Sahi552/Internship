package com.example.retrofitapi

import UniversityModelItem
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapi.recycler.RecyclerAdopter
import com.example.retrofitapi.retrofit.ApiHelper
import com.example.retrofitapi.retrofit.ApiRequest
import kotlinx.coroutines.launch

class ApiScreen : AppCompatActivity(), OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_api_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val progressBar = findViewById<ProgressBar>(R.id.progressBarCircular)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val messageView = findViewById<TextView>(R.id.loadingTextView)
        val linearLayout = findViewById<LinearLayout>(R.id.linearlayout)

        val loadingMessage = "Please wait while we are fetching data for you"
        val failedMessage = "Please check Internet Connectivity"

        var isLoading = false

        val apiData = intent.extras?.getString("countryName")

        if (apiData == null) {
            isLoading = false
        } else {
            isLoading = true
        }

        if (isLoading) {
            messageView.text = loadingMessage
            progressBar.visibility = View.VISIBLE
            linearLayout.visibility = View.GONE
            recyclerView.visibility = View.GONE

            lifecycleScope.launch {
                try {
                    //apicall
                    val apiCall = ApiHelper.instance().create(ApiRequest::class.java)

                    //get response body
                    val result = apiCall.getCollage(apiData.toString())

                    //successfully retrieved
                    if (result.isSuccessful) {
                        progressBar.visibility = View.GONE
                        messageView.visibility = View.GONE
                        linearLayout.visibility = View.VISIBLE
                        recyclerView.visibility = View.VISIBLE

                        val dataset = result.body()
                        val recyclerAdapter = RecyclerAdopter(dataset, this@ApiScreen)
                        recyclerView.layoutManager = LinearLayoutManager(this@ApiScreen)
                        recyclerView.adapter = recyclerAdapter
                    } else {
                        recyclerView.visibility = View.GONE
                        progressBar.visibility = View.GONE
                        linearLayout.visibility = View.GONE
                        messageView.text = failedMessage
                        Toast.makeText(
                            this@ApiScreen,
                            "Fetching data was failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: Exception) {
                    Log.e("YourAPIResponse", "Exception: ${e.message}")
                }
            }
        } else {
            recyclerView.visibility = View.GONE
            progressBar.visibility = View.GONE
            messageView.text = failedMessage
        }
    }

    override fun itemClick(position: Int, context: Context, currentItem: UniversityModelItem?) {
        Toast.makeText(this@ApiScreen, "$position is clicked", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, ExpandedScreen::class.java)
        intent.putExtra("name", currentItem?.name)
        intent.putExtra("domains", currentItem?.domains[0])
        intent.putExtra("country", currentItem?.country)
        intent.putExtra("alpha", currentItem?.alpha_two_code)
        intent.putExtra("webPages", currentItem?.web_pages[0])
        context.startActivity(intent)
    }
}

interface OnItemClickListener {
    fun itemClick(position: Int, context: Context, currentItem: UniversityModelItem?)
}