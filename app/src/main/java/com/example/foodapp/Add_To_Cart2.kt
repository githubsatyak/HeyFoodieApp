package com.example.foodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.foodapp.databinding.ActivityAddToCart2Binding


class Add_To_Cart2 : AppCompatActivity() {

    lateinit var binding:ActivityAddToCart2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddToCart2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.titleText2.text="Ice-Creams"
        binding.plus2.setOnClickListener{
            var presentValStr:String=binding.counter2.text.toString()
            var presentIntVal:Int=Integer.parseInt(presentValStr)
            presentIntVal++
            binding.priceTxt2.text=(100* presentIntVal).toString()
            binding.counter2.text = presentIntVal.toString()


        }
        binding.minus2.setOnClickListener{
            var presentValStr:String=binding.counter2.text.toString()
            var presentIntVal:Int=Integer.parseInt(presentValStr)
            presentIntVal--
            if(presentIntVal!=0){
                binding.priceTxt2.text=((100* presentIntVal+100)-100).toString()
                binding.counter2.text = presentIntVal.toString()}

        }
        binding.Description2.text="Ice Cream Vanilla with Chocolate and kaju badam with additional chocofills"
        binding.addToCartBtn2.setOnClickListener{
            Toast.makeText(this,"${binding.counter2.text.toString()} items added to cart",Toast.LENGTH_SHORT).show()

            val bundle = Bundle()

            bundle.putString("price", binding.priceTxt2.text.toString())
            //bundle.putString("Quantity", binding.counter.text.toString())
            val intent = Intent(this, My_Cart::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        }
}
