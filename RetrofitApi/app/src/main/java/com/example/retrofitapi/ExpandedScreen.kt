package com.example.retrofitapi

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ExpandedScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_expanded_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = findViewById<TextView>(R.id.nameTextView)
        val domains = findViewById<TextView>(R.id.domainsTextView)
        val country = findViewById<TextView>(R.id.countryTextView)
        val alpha = findViewById<TextView>(R.id.alphaTextView)
        val web = findViewById<TextView>(R.id.webTextView)

        val nameData = intent.extras?.getString("name")
        val domainsData = intent.extras?.getString("domains")
        val countryData = intent.extras?.getString("country")
        val alphaData = intent.extras?.getString("alpha")
        val webData = intent.extras?.getString("webPages")


        if (nameData != null && domainsData != null && countryData != null && alphaData != null &&webData != null){
            name.text = nameData.toString()
            domains.text = domainsData.toString()
            country.text = countryData.toString()
            alpha.text = alphaData.toString()
            web.text = webData.toString()
        }
    }
}