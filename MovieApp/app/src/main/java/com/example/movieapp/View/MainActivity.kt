package com.example.movieapp.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.movieapp.R
import com.example.movieapp.View.Fragments.DetailsFragment
import com.example.movieapp.View.Fragments.HomeFragment
import com.example.movieapp.View.Fragments.MovieFragment
import com.example.movieapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var homeFragment: HomeFragment = HomeFragment.newInstance()
    private var detailsFragment: DetailsFragment = DetailsFragment.newInstance()
    private var movieFragment: MovieFragment = MovieFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(movieFragment)
        bottomNavigation()
    }

    private fun bottomNavigation(){
        binding.bottomAppBar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.ic_movie -> replaceFragment(movieFragment)
                R.id.ic_home -> replaceFragment(homeFragment)
                R.id.ic_details -> replaceFragment(detailsFragment)
                else -> {}
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeHolder, fragment)
            .commit()
    }
}