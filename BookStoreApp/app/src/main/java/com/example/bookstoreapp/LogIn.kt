package com.example.bookstoreapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.bookstoreapp.Database.MainDb

class LogIn : AppCompatActivity() {
    private lateinit var username: TextView;
    private lateinit var password: TextView;
    private lateinit var loginBtn: Button;
    private lateinit var registerText: TextView;
    private lateinit var db: MainDb;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        login()
        regiser()
    }

    fun init(){
        username = findViewById<TextView>(R.id.usernameInputLogin)
        password = findViewById<TextView>(R.id.passwordInputLogin)
        loginBtn = findViewById<Button>(R.id.loginBtn)
        registerText = findViewById<TextView>(R.id.registerText)
        db = MainDb.getDb(this)
    }

    fun login(){

        lateinit var actionTxt: Toast

        loginBtn.setOnClickListener(){

            var usernameTxt = username.text.toString()
            var passwordTxt = password.text.toString()

            if(usernameTxt.isEmpty() || passwordTxt.isEmpty()){
                actionTxt = Toast.makeText(this, "Please fill the fields", Toast.LENGTH_SHORT)
                actionTxt.show()
            }
            else{
                Thread {
                    val db_password = db.getDao().getUserPassword(usernameTxt)
                    val role = db.getDao().getUserRole(usernameTxt)

                    if(db_password.isNullOrEmpty()){
                        runOnUiThread{
                            actionTxt = Toast.makeText(this, "That user doesn't exist", Toast.LENGTH_SHORT)
                            actionTxt.show()
                        }
                    }
                    else if(db_password == passwordTxt){
                        var int = Intent(this, Home::class.java)
                        int.putExtra("role", role)
                        startActivity(int)
                    }
                    else runOnUiThread {
                        actionTxt = Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT)
                        actionTxt.show()
                    }
                }.start()
            }
        }
    }

    fun regiser(){
        registerText.setOnClickListener(){
            var int = Intent(this, Register::class.java)
            startActivity(int)
        }
    }
}