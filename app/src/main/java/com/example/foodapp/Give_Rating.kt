package com.example.foodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast

class Give_Rating : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_give_rating)
        val rBar = findViewById<RatingBar>(R.id.rBar)
        if (rBar != null) {
            val button = findViewById<Button>(R.id.submit_button)
            button?.setOnClickListener {
                val msg = rBar.rating.toString()
                Toast.makeText(this@Give_Rating,
                    "Thanks for giving "+msg +" Rating", Toast.LENGTH_SHORT).show()
            }
        }
    }

}