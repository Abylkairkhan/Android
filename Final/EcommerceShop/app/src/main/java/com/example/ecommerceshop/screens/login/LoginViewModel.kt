package com.example.ecommerceshop.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ecommerceshop.models.UserLogin
import com.example.ecommerceshop.requests.Service
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.ecommerceshop.Credentials
import com.example.ecommerceshop.models.UserRegister
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LoginViewModel: ViewModel() {
    private val service = Service()


    suspend fun login(username: String, password: String): Boolean = suspendCoroutine{
        val user = UserLogin(username,password)
        CoroutineScope(Dispatchers.IO).launch {
            service.getUserInfo(username)
            try{
                val token = service.login(user)
                if(token.access.isNotBlank()) {
                    Credentials.TOKEN = token.access
                    Credentials.REFRESH_TOKEN = token.refresh
                    it.resume(true)
                }
                else it.resume(false)
            }
            catch (e: Exception) {
                it.resume(false)
            }
        }
    }

    suspend fun signup(username: String, email: String, password: String, groups: Int):Boolean = suspendCoroutine{
        val userRegister = UserRegister(username,email,password, arrayOf(groups))
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRegister2 = service.signup(userRegister)
                if (userRegister2.username.isNotBlank()) it.resume(true)
                else it.resume(false)
            }
            catch (e: Exception) {
                it.resume(false)
            }
        }
    }
}