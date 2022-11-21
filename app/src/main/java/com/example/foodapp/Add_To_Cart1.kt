package com.example.foodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.foodapp.databinding.ActivityAddToCart1Binding


class Add_To_Cart1 : AppCompatActivity() {

    lateinit var binding:ActivityAddToCart1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddToCart1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.titleText1.text="Pizza"
        binding.plus1.setOnClickListener{
            var presentValStr:String=binding.counter1.text.toString()
            var presentIntVal:Int=Integer.parseInt(presentValStr)
            presentIntVal++
            binding.priceTxt1.text=(300* presentIntVal).toString()
            binding.counter1.text = presentIntVal.toString()


        }
        binding.minus1.setOnClickListener{
            var presentValStr:String=binding.counter1.text.toString()
            var presentIntVal:Int=Integer.parseInt(presentValStr)
            presentIntVal--
            if(presentIntVal!=0){
                binding.priceTxt1.text=((300* presentIntVal+300)-300).toString()
                binding.counter1.text = presentIntVal.toString()}

        }
        binding.Description1.text="Veg pizza which is consists of tomato,cheese with chilli flex and sauce  "
        binding.addToCartBtn1.setOnClickListener{
            Toast.makeText(this,"${binding.counter1.text.toString()} items added to cart",Toast.LENGTH_SHORT).show()

            val bundle = Bundle()

            bundle.putString("price", binding.priceTxt1.text.toString())
            //bundle.putString("Quantity", binding.counter.text.toString())
            val intent = Intent(this, My_Cart::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }



    }
}