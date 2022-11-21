package com.example.foodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.my_cart.*

class My_Cart : AppCompatActivity() {
   lateinit var price:TextView
    lateinit var count:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_cart)

        totalFeeTxt2.text ="₹ 15"
        totalFeeTxt3.text ="₹ 3"
        price=findViewById(R.id.totalFeeTxt1)
        count=findViewById(R.id.textView5)
        val bundle = intent.extras
        if (bundle != null){
            price.text = "${bundle.getString("price")}"
            //price.text = "${bundle.getString("Quantity")}"

        }
        var valStr:String=price.text.toString()
        var value:Int=Integer.parseInt(valStr)

        total.text ="₹ "+(value+15+3).toString()

        val onClick: TextView = findViewById(R.id.textView20)
        onClick.setOnClickListener {
            val intent = Intent(this, OrderConfirmed::class.java)
            startActivity(intent)
        }
    }
}