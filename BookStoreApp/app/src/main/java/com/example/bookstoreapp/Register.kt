package com.example.bookstoreapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.bookstoreapp.Database.MainDb
import com.example.bookstoreapp.Database.User

class Register : AppCompatActivity() {
    private lateinit var username: TextView;
    private lateinit var password: TextView;
    private lateinit var adminPassword: TextView;
    private lateinit var registerBtn: Button;
    private lateinit var db: MainDb;
    val admPas = "qwerty123qwerty"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        init()
        register()
    }

    fun init(){
        username = findViewById(R.id.usernameInputRegister)
        password = findViewById(R.id.passwordInputRegister)
        adminPassword = findViewById(R.id.passwordInputAdmin)
        registerBtn = findViewById(R.id.registerBtn)
        db = MainDb.getDb(this)
    }

    fun register(){

        lateinit var actionTxt: Toast

        registerBtn.setOnClickListener() {

            var usernameTxt = username.text.toString()
            var passwordTxt = password.text.toString()
            var adminPasswordTxt = adminPassword.text.toString()


            if (usernameTxt.isEmpty() || passwordTxt.isEmpty()) {
                actionTxt = Toast.makeText(this, "Please fill the fields", Toast.LENGTH_SHORT)
                actionTxt.show()
            }
            else{
                Thread{
                    val db_username = db.getDao().getUsername(usernameTxt)
                    if(db_username.isNullOrEmpty()){
                        lateinit var user: User

                        if(adminPasswordTxt.equals(admPas))
                            user = User(null, usernameTxt, passwordTxt, "admin")
                        else
                            user = User(null, usernameTxt, passwordTxt, "client")

                        db.getDao().insertUser(user)
                    }
                    else if(!db_username.isNullOrEmpty() && db_username == usernameTxt){
                        runOnUiThread{
                            actionTxt = Toast.makeText(this, "Choose another Username", Toast.LENGTH_SHORT)
                            actionTxt.show()
                        }
                    }
                }.start()
            }
        }
    }
}
