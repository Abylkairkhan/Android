package com.example.sneakerstreet.data.auth

import com.example.sneakerstreet.domain.use_case.AuthErrorCatcher
import com.example.sneakerstreet.util.ui.UIText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.tasks.await

class AuthRepository(
    private val auth: FirebaseAuth,
    private val authErrorCatcher: AuthErrorCatcher
) {

     fun checkUserAlreadyInSystem(): AuthResult {
        val authResult = AuthResult(false)
        val currentUser = auth.currentUser
        if (currentUser != null) {
            authResult.success = true
        }
        return authResult
    }

    fun signOut() {
        auth.signOut()
    }

    suspend fun createUser(email: String, password: String): AuthResult {
        val authResult = AuthResult(false)
        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            authResult.success = true
        } catch (e: FirebaseAuthException) {
            authErrorCatcher.errorCatcher(e, authResult)
        }
        return authResult
    }

    suspend fun signInUser(email: String, password: String): AuthResult {
        val authResult = AuthResult(false)
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            authResult.success = true
        } catch (e: FirebaseAuthException) {
            authErrorCatcher.errorCatcher(e, authResult)
        }
        return authResult
    }
}

data class AuthResult(
    var success: Boolean = false,
    var error: UIText? = null
)
