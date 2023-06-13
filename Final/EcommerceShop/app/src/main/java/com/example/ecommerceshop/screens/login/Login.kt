package com.example.ecommerceshop.screens.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.ecommerceshop.R
import com.example.ecommerceshop.databinding.FragmentLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Login : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val vm = LoginViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        login()
        return binding.root
    }

    private fun login(){
        binding.loginBtn.setOnClickListener(){
            with(binding){
                if(username.text.isEmpty() || password.text.isEmpty()){
                    Toast.makeText(requireContext(),"Please fill all fields",Toast.LENGTH_LONG).show()
                }
                else{
                    CoroutineScope(Dispatchers.IO).launch {
                        if (vm.login(username = username.text.toString(), password = password.text.toString())) {
                            withContext(Dispatchers.Main) {
                                findNavController().navigate(R.id.action_login_to_homeActivity)
                            }
                        }
                        else {
                            withContext(Dispatchers.Main){
                                Toast.makeText(requireContext(), "Username/password incorrect", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
            }
        }
    }
}