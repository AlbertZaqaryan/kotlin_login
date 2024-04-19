package com.example.loginregister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mystart: TextView = findViewById(R.id.start)
        mystart.setOnClickListener{
            val intent = Intent(this, SignInPage::class.java)
            startActivity(intent)
        }


    }


}