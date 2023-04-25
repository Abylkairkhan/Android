package com.example.animation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private lateinit var img: ImageView
    private lateinit var btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        img = findViewById(R.id.imageView)
        btn = findViewById(R.id.button)
        var x: Animation = AnimationUtils.loadAnimation(applicationContext,R.anim.anim_1)
        btn.setOnClickListener(){
            img.startAnimation(x)
        }
    }
}