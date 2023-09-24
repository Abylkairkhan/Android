package com.example.sneakerstreet.core.di

import com.example.sneakerstreet.domain.parser.ProductParser
import com.example.sneakerstreet.domain.use_case.ValidateEmail
import com.example.sneakerstreet.domain.use_case.ValidatePassword
import com.example.sneakerstreet.domain.use_case.ValidateRepeatedPassword
import org.koin.dsl.module

val validateEmail = module {

    single { ValidateEmail() }

    single { ValidatePassword() }

    single { ValidateRepeatedPassword() }

    single { ProductParser() }

}