package com.example.foodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.foodapp.databinding.ActivityAddToCartBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Add_To_Cart : AppCompatActivity() {

    lateinit var binding:ActivityAddToCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddToCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.titleText.text="Chicken Biriyani"

        binding.plus.setOnClickListener{
            var presentValStr:String=binding.counter.text.toString()
            var presentIntVal:Int=Integer.parseInt(presentValStr)
            presentIntVal++
            binding.priceTxt.text= (200* presentIntVal).toString()
            binding.counter.text = presentIntVal.toString()


        }
        binding.minus.setOnClickListener{
            var presentValStr:String=binding.counter.text.toString()
            var presentIntVal:Int=Integer.parseInt(presentValStr)
            presentIntVal--
            if(presentIntVal!=0){
                binding.priceTxt.text= ((200* presentIntVal+200)-200).toString()
            binding.counter.text = presentIntVal.toString()}

        }

        binding.Description.text="Dum Biriyani consists of crispy piece of chicken and very hygenic and delicious with tasty gravy and raita"
binding.addToCartBtn.setOnClickListener{
    Toast.makeText(this,"${binding.counter.text.toString()} items added to cart",Toast.LENGTH_SHORT).show()
    val bundle = Bundle()

    bundle.putString("price", binding.priceTxt.text.toString())
    //bundle.putString("Quantity", binding.counter.text.toString())
    val intent = Intent(this, My_Cart::class.java)
    intent.putExtras(bundle)
    startActivity(intent)
}
    }
}