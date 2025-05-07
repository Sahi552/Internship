package com.example.retrofitapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.retrofitapi.retrofit.ApiHelper
import com.example.retrofitapi.retrofit.ApiRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val apiButton = findViewById<Button>(R.id.apibutton)
        val country = findViewById<EditText>(R.id.countryEditText)

        apiButton.setOnClickListener {

            //check for empty input
            if (country.text.isNotEmpty()){
                val intent = Intent(this@MainActivity, ApiScreen::class.java)
                //passing country name to next activity
                intent.putExtra("countryName", country.text.toString())
                startActivity(intent)
            }else {
                //leave message for user
                Toast.makeText(this,"please enter country", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

