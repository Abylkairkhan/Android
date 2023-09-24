package com.example.sneakerstreet.core.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val firebaseModule = module {

    single { Firebase.auth }

    single { Firebase.firestore }
}