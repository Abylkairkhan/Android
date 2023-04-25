package com.example.locationapp

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Geocoder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class ContactCardActivity : AppCompatActivity() {

    private lateinit var image: ImageView
    private lateinit var name: TextView
    private lateinit var alter: TextView
    private lateinit var status: TextView
    private lateinit var button: ImageButton
    private lateinit var imageType: Number

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_card)
        init()
        parseData()
        openMap(intent.getStringExtra("wohnort")!!)
    }

    private fun init(){
        image = findViewById(R.id.ContactImage)
        name = findViewById(R.id.TextViewName)
        alter = findViewById(R.id.TextViewAlter)
        status = findViewById(R.id.TextViewStatus)
        button = findViewById(R.id.ImageButtonLocation)
        imageType = intent.getIntExtra("type", 0)
    }

    private fun openMap(location: String){
        button.setOnClickListener() {
            val geocoder = Geocoder(this)
            val address = geocoder.getFromLocationName(location, 1)?.firstOrNull()

            if (address != null) {
                val uri = Uri.parse(
                    "geo:${address.latitude},${address.longitude}?q=${address.latitude},${address.longitude}(${
                        address.getAddressLine(0)
                    })"
                )
                val mapIntent = Intent(Intent.ACTION_VIEW, uri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            } else {
                Toast.makeText(this, "Location not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun parseData(){
        if(imageType == R.id.RadioButtonMannlich){
            image.setImageResource(R.drawable.girl)
        }
        if(imageType == R.id.RadioButtonWeiblich){
            image.setImageResource(R.drawable.man)
        }
        if(imageType == R.id.RadioButtonApache){
            image.setImageResource(R.drawable.helicopter)
        }
        name.text = "Name: " + intent.getStringExtra("name")
        alter.text = "Alter: " + intent.getStringExtra("alter")
        status.text = "Status: " + if(intent.getBooleanExtra("switch", false)) "Student" else "Normalo"
    }
}