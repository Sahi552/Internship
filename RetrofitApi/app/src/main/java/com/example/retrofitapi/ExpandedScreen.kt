package com.example.retrofitapi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitapi.databinding.ActivityExpandedScreenBinding

class ExpandedScreen : AppCompatActivity() {

    private lateinit var binding: ActivityExpandedScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityExpandedScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nameData = intent.extras?.getString("name")
        val domainsData = intent.extras?.getString("domains")
        val countryData = intent.extras?.getString("country")
        val alphaData = intent.extras?.getString("alpha")
        val webData = intent.extras?.getString("webPages")

        if (nameData != null && domainsData != null && countryData != null && alphaData != null && webData != null) {
            binding.nameTextView.text = nameData.toString()
            binding.domainsTextView.text = domainsData.toString()
            binding.countryTextView.text = countryData.toString()
            binding.alphaTextView.text = alphaData.toString()
            binding.webTextView.text = webData.toString()
        }
    }
}