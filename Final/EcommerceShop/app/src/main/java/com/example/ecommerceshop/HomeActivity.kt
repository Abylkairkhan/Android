package com.example.ecommerceshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ecommerceshop.databinding.ActivityHomeBinding
import com.example.ecommerceshop.screens.home.Basket
import com.example.ecommerceshop.screens.home.Create
import com.example.ecommerceshop.screens.home.Home
import com.example.ecommerceshop.screens.home.TrueHome
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(TrueHome())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.main -> replaceFragment(TrueHome())
                R.id.basket -> replaceFragment(Basket())
                else ->{

                }
            }
            false
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}