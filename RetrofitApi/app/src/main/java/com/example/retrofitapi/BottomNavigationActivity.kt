package com.example.retrofitapi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.retrofitapi.databinding.ActivityBottomNavigationBinding

class BottomNavigationActivity : AppCompatActivity() {

    val homeFragment = HomeFragment()
    val collageFragment = CollageFragment()
    private val viewModel: ItemViewModel by viewModels()
    private lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.frameLayout, HomeFragment())
        transaction.commit()

        binding.bottomNavigationBar.setOnItemSelectedListener {
            try {
                when (it.itemId) {
                    R.id.homeView -> {
                        loadFragment(homeFragment)
                        viewModel.message = "Home Button CLicked"
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
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }

}

