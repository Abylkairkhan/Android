package com.example.ecommerceshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.ecommerceshop.databinding.ActivityMainBinding
import com.example.ecommerceshop.screens.login.Login
import com.example.ecommerceshop.screens.login.SignUp
import com.example.ecommerceshop.screens.login.Welcome
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}