package com.example.retrofitapi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationActivity : AppCompatActivity() {

    val homeFragment = HomeFragment()
    val collageFragment = CollageFragment()
    private lateinit var bottomNavigationActivity: BottomNavigationView
    private val viewModel: ItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bottom_navigation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.frameLayout, HomeFragment())
        transaction.commit()

        bottomNavigationActivity = findViewById<BottomNavigationView>(R.id.bottomNavigationBar)

        bottomNavigationActivity.setOnItemSelectedListener {
            try {
                when (it.itemId) {
                    R.id.homeView -> {
                        loadFragment(homeFragment)
                        viewModel.message = "Home Button Clicked"
                        true
                    }

                    R.id.collageView -> {
                        loadFragment(collageFragment)
                        viewModel.message = "Collage Button Clicked"
                        true
                    }

                    else -> false
                }
            } catch (e: Exception) {
                throw e
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {

        if (true) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout, fragment)
            transaction.commit()
        }
    }
}
