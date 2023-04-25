package com.example.loginshared

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val usernameInput: TextView = findViewById(R.id.registerUsername)
        val passwordInput: TextView = findViewById(R.id.registerPassword)
        val btnRegister: Button = findViewById(R.id.registerBtnRegister)
        val db: MainDb = MainDb.getDb(this)


        btnRegister.setOnClickListener(){

            var user: User = User(null,
                usernameInput.text.toString(),
                passwordInput.text.toString())

            Thread{
                db.getDao().insertUser(user)
            }.start()
        }
    }
}