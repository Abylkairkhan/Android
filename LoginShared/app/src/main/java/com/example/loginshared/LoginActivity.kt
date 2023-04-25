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
        val pref: SharedPreferences = getSharedPreferences("user_db", Context.MODE_PRIVATE)

        btnLogin.setOnClickListener(){
            val password = pref.getString(usernameInput.text.toString(),"").toString()
            txt.text = password

            if(passwordInput.text.toString() == password){
                val toastText = Toast.makeText(this, "Go HomePage", Toast.LENGTH_SHORT)
                toastText.show()

                val int: Intent = Intent(this, HomeActivity::class.java)
                startActivity(int)
            }
            else{
                val toastText = Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT)
                toastText.show()
            }
        }
    }
}