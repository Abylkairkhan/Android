package com.example.ecommerceshop.screens.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.ecommerceshop.R
import com.example.ecommerceshop.databinding.FragmentSignUpBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUp : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private var vm = LoginViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        checkbox()
        signup()
        return binding.root
    }

    private fun checkbox(){
        with(binding){
            seller.setOnClickListener {
                if(client.isChecked) client.isChecked = false
            }
            client.setOnClickListener {
                if(seller.isChecked) seller.isChecked = false
            }
        }
    }

    private fun signup(){
        binding.signup.setOnClickListener(){
            with(binding){
                if(username.text.isEmpty() || email.text.isEmpty() || password.text.isEmpty() || client.isChecked && seller.isChecked){
                    Toast.makeText(requireContext(),"Please fill all fields", Toast.LENGTH_LONG).show()
                }
                else{
                    var type = if(seller.isChecked) 1 else 2
                    CoroutineScope(Dispatchers.IO).launch {
                        if (vm.signup(username.text.toString(),email.text.toString(),password.text.toString(),type)){
                            withContext(Dispatchers.Main) {
                                findNavController().navigate(R.id.action_signUp_to_login)
                            }
                        }
                        else{
                            withContext(Dispatchers.Main){
                                Toast.makeText(requireContext(), "Something wrong", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
            }
        }
    }
}