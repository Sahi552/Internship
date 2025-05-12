package com.example.retrofitapi

import UniversityModelItem
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitapi.databinding.ActivityApiScreenBinding
import com.example.retrofitapi.recycler.UniversityAdopter
import com.example.retrofitapi.recycler.UniversityAdopter.OnItemClickListener
import com.example.retrofitapi.retrofit.ApiHelper
import com.example.retrofitapi.retrofit.ApiRequest
import com.example.retrofitapi.utility.Util
import kotlinx.coroutines.launch

class ApiScreen : AppCompatActivity(), OnItemClickListener {

    private lateinit var binding: ActivityApiScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityApiScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val networkChecker = Util()
        val loadingMessage = "Please wait while we are fetching data for you"
        val failedMessage = "Please check Internet Connectivity"

        var isLoading = false

        val apiData = intent.extras?.getString("countryName")

        isLoading = apiData != null

        if (networkChecker.isInternetAvailable(this)) {
            if (isLoading) {
                binding.loadingTextView.text = loadingMessage
                binding.progressBarCircular.visibility = View.VISIBLE
                binding.linearlayout.visibility = View.GONE
                binding.recyclerView.visibility = View.GONE

                lifecycleScope.launch {
                    try {
                        //apicall
                        val apiCall = ApiHelper.instance().create(ApiRequest::class.java)

                        //get response body
                        val result = apiCall.getCollage(apiData.toString())

                        //successfully retrieved
                        if (result.isSuccessful) {
                            binding.progressBarCircular.visibility = View.GONE
                            binding.loadingTextView.visibility = View.GONE
                            binding.linearlayout.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.VISIBLE

                            val dataset = result.body()
                            val recyclerAdapter = UniversityAdopter(
                                dataset,
                                this@ApiScreen
                            ) 
                            binding.recyclerView.layoutManager = LinearLayoutManager(this@ApiScreen)
                            binding.recyclerView.adapter = recyclerAdapter
                        } else {
                            binding.recyclerView.visibility = View.GONE
                            binding.progressBarCircular.visibility = View.GONE
                            binding.linearlayout.visibility = View.GONE
                            binding.loadingTextView.text = failedMessage
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
            }
        } else {
            binding.recyclerView.visibility = View.GONE
            binding.progressBarCircular.visibility = View.GONE
            binding.loadingTextView.text = failedMessage
        }
    }

    override fun itemClick(position: Int, context: Context, currentItem: UniversityModelItem?) {
        val intent = Intent(context, ExpandedScreen::class.java)
        intent.putExtra("name", currentItem?.name)
        intent.putExtra("domains", currentItem?.domains[0])
        intent.putExtra("country", currentItem?.country)
        intent.putExtra("alpha", currentItem?.alpha_two_code)
        intent.putExtra("webPages", currentItem?.web_pages[0])
        context.startActivity(intent)
    }
}

