package com.example.locationapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit  var alter: EditText
    private lateinit var wohnort: EditText
    private lateinit var radioButton: RadioGroup
    private lateinit var switch: Switch
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        switchActivity()
    }

    private fun init(){
        name = findViewById(R.id.editTextName)
        alter = findViewById(R.id.editTextAlter)
        wohnort = findViewById(R.id.editTextWohnort)
        radioButton= findViewById(R.id.RadioGroupType)
        switch = findViewById(R.id.SwitchStudent)
        button = findViewById(R.id.ButtonSave)
    }

    private fun switchActivity(){
        button.setOnClickListener() {
            val intent = Intent(this, ContactCardActivity::class.java)
            intent.putExtra("name", name.text.toString())
            intent.putExtra("alter", alter.text.toString())
            intent.putExtra("wohnort", wohnort.text.toString())
            intent.putExtra("type", radioButton.checkedRadioButtonId)
            intent.putExtra("switch", switch.isChecked)
            startActivity(intent)
        }
    }
}