package com.example.loginshared

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val txt: TextView = findViewById(R.id.registerText)
        val usernameInput: TextView = findViewById(R.id.registerUsername)
        val passwordInput: TextView = findViewById(R.id.registerPassword)
        val btnRegister: Button = findViewById(R.id.registerBtnRegister)
        val pref: SharedPreferences = getSharedPreferences("user_db", MODE_PRIVATE)
        val edit: Editor = pref.edit()

        btnRegister.setOnClickListener(){
            edit.putString(usernameInput.text.toString(), passwordInput.text.toString())
            edit.apply()
            val toastText = Toast.makeText(this, "You Registered", Toast.LENGTH_SHORT)
            toastText.show()
        }
    }
}