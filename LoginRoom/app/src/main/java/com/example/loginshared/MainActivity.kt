package com.example.loginshared

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnRegister:Button = findViewById(R.id.mainBtnRegister)
        val btnLogin:Button = findViewById(R.id.mainBtnLogin)

        btnRegister.setOnClickListener(){
            val intent: Intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener(){
            val intent: Intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}