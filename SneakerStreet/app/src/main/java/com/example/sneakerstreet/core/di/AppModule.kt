package com.example.sneakerstreet.core.di

import com.example.sneakerstreet.data.auth.AuthRepository
import com.example.sneakerstreet.data.firestore.DataBaseRepository
import com.example.sneakerstreet.domain.use_case.AuthErrorCatcher
import com.example.sneakerstreet.presentation.home.HomeViewModel
import com.example.sneakerstreet.presentation.sign_in.SignInViewModel
import com.example.sneakerstreet.presentation.sign_up.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { SignUpViewModel(get(), get(), get(), get()) }

    viewModel { SignInViewModel(get(), get(), get()) }

    viewModel { HomeViewModel(get()) }

    single { AuthRepository(get(), get()) }

    single { DataBaseRepository(get(), get()) }

    single { AuthErrorCatcher() }
}

