package com.example.loginshared

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val txt = findViewById<TextView>(R.id.loginText)
        val usernameInput: TextView = findViewById(R.id.loginUsername)
        val passwordInput: TextView = findViewById(R.id.loginPassword)
        val btnLogin: Button = findViewById(R.id.loginBtnLogin)
        val db = MainDb.getDb(this)

        btnLogin.setOnClickListener(){
            Thread{
                val temp = db.getDao().checkPassword(usernameInput.text.toString())
                if(temp == passwordInput.text.toString()){
                    var int = Intent(this, HomeActivity::class.java)
                    startActivity(int)
                }
            }.start()
        }
    }
}