package com.example.foodapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.foodapp.Model.Users
import com.example.foodapp.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class SignupActivity : AppCompatActivity(){
    lateinit var binding: ActivitySignupBinding
    lateinit var auth:FirebaseAuth
    lateinit var database:FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding= ActivitySignupBinding.inflate(layoutInflater)

        setContentView(binding.root)
        auth= FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance()

        binding.signup.setOnClickListener{
            try {
                auth.createUserWithEmailAndPassword(
                    binding.email.text.toString(),
                    binding.password.text.toString()
                )
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val username = binding.username.text.toString()
                            val email = binding.email.text.toString()
                            val password = binding.password.text.toString()
                            var user = Users(username, email, password)
                            val id = task.result.user?.uid
                            if (id != null) {
                                database.reference.child("Users").child(id).setValue(user)
                                    .addOnSuccessListener {
                                        binding.username.text.clear()
                                        binding.email.text.clear()
                                        binding.password.text.clear()

                                        startActivity(Intent(this, HomeActivity::class.java))

                                    }
                            }
                            Toast.makeText(
                                this@SignupActivity,
                                "Account created successfully",
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {
                            Toast.makeText(
                                this@SignupActivity,
                                task.exception?.message,
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }
            }
            catch (e:Exception){
                Toast.makeText(this,e.message.toString(),Toast.LENGTH_SHORT).show()
            }

        }

        binding.accountView.setOnClickListener{
            startActivity(Intent(this,LogIn::class.java))
        }

    }







}
