package com.example.netflix_compose.screens.authentication

import androidx.lifecycle.ViewModel
import com.example.netflix_compose.EventHandler

class LoginViewModel: ViewModel(), EventHandler<LoginEvent> {

    override fun obtainEvent(event: LoginEvent) {

    }

}

sealed class LoginEvent