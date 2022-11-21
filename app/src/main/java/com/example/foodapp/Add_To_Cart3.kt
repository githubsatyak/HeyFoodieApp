package com.example.foodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.foodapp.databinding.ActivityAddToCart3Binding


class Add_To_Cart3 : AppCompatActivity() {

    lateinit var binding:ActivityAddToCart3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddToCart3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.titleText3.text="Tandoori Rotis"
        binding.plus3.setOnClickListener{
            var presentValStr:String=binding.counter3.text.toString()
            var presentIntVal:Int=Integer.parseInt(presentValStr)
            presentIntVal++
            binding.priceTxt3.text= (70* presentIntVal).toString()
            binding.counter3.text = presentIntVal.toString()


        }
        binding.minus3.setOnClickListener{
            var presentValStr:String=binding.counter3.text.toString()
            var presentIntVal:Int=Integer.parseInt(presentValStr)
            presentIntVal--
            if(presentIntVal!=0){
                binding.priceTxt3.text=((70* presentIntVal+70)-70).toString()
                binding.counter3.text = presentIntVal.toString()}

        }

        binding.Description3.text="Two Tandoori Rotis along with paneer curry and salad which is onion and lemon"
        binding.addToCartBtn3.setOnClickListener{
            Toast.makeText(this,"${binding.counter3.text.toString()} items added to cart",Toast.LENGTH_SHORT).show()

            val bundle = Bundle()

            bundle.putString("price", binding.priceTxt3.text.toString())
            //bundle.putString("Quantity", binding.counter.text.toString())
            val intent = Intent(this, My_Cart::class.java)
            intent.putExtras(bundle)
            startActivity(intent)

        }
    }
}