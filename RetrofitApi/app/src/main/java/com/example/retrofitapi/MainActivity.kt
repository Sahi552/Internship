package com.example.retrofitapi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apibutton.setOnClickListener {
            //check for empty input
            if (binding.countryEditText.text.isNotEmpty()) {
                val intent = Intent(this@MainActivity, ApiScreen::class.java)
                //passing country name to next activity
                intent.putExtra("countryName", binding.countryEditText.text.toString())
                startActivity(intent)
            } else {
                //leave message for user
                Toast.makeText(this, "please enter country", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

