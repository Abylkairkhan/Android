package com.example.bookstoreapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.bookstoreapp.Fragments.BookAddFragment
import com.example.bookstoreapp.Fragments.BookListFragment

class Home : AppCompatActivity() {

    private lateinit var search: EditText
    private lateinit var searchBtn: Button
    private lateinit var addBtn: Button
    private lateinit  var ascBtn: Button
    private lateinit var descBtn: Button
    private lateinit var role: String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val extras = intent.extras
        if (extras != null) { role = extras.getString("role").toString() }

        init()
        ascOrdesc()
        search()
        clientOrAdmin(role)
        bookAdd()

        supportFragmentManager.beginTransaction()
            .replace(R.id.home_placeholder, BookListFragment.newInstance(0,"", 0, role))
            .commit()
    }

    private fun bookAdd(){
        addBtn.setOnClickListener(){
            supportFragmentManager.beginTransaction()
                .replace(R.id.home_placeholder, BookAddFragment.newInstance())
                .commit()
        }
    }

    private fun search(){
        searchBtn.setOnClickListener(){
            try {
                var id: Int = Integer.parseInt(search.text.toString())
                supportFragmentManager.beginTransaction()
                    .replace(R.id.home_placeholder, BookListFragment.newInstance(3,"", id, role))
                    .commit()
            }
            catch (e: NumberFormatException){
                val pattern: String = "%" + search.text.toString() + "%"
                supportFragmentManager.beginTransaction()
                    .replace(R.id.home_placeholder, BookListFragment.newInstance(-1,pattern, 0, role))
                    .commit()
            }
        }
    }

    private fun ascOrdesc() {
        ascBtn.setOnClickListener(){
            supportFragmentManager.beginTransaction()
                .replace(R.id.home_placeholder, BookListFragment.newInstance(1,"", 0, role))
                .commit()
        }

        descBtn.setOnClickListener(){
            supportFragmentManager.beginTransaction()
                .replace(R.id.home_placeholder, BookListFragment.newInstance(2,"", 0, role))
                .commit()
        }
    }

    private fun init(){
        search = findViewById(R.id.homeSearchTextView)
        searchBtn = findViewById(R.id.homeSearchBtn)
        addBtn = findViewById(R.id.homeAddBtn)
        ascBtn = findViewById(R.id.homeASCBtn)
        descBtn = findViewById(R.id.homeDESCBtn)
    }

    private fun clientOrAdmin(role: String) {
        if (role == "admin"){
            addBtn.visibility = View.VISIBLE
        }
        else addBtn.visibility = View.GONE
    }
}