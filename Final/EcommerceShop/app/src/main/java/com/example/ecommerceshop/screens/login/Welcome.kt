package com.example.ecommerceshop.screens.login

import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ecommerceshop.R
import com.example.ecommerceshop.databinding.FragmentWelcomeBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


const val RC_SIGN_IN = 1000

class Welcome : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        SignButtons()
        return binding.root
    }

    private fun SignButtons(){
        with(binding){
            SignUpButton.setOnClickListener(){
                findNavController().navigate(R.id.action_welcome_to_signUp)
            }
            LoginTextView.setOnClickListener(){
                findNavController().navigate(R.id.action_welcome_to_login)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = Welcome()
    }
}