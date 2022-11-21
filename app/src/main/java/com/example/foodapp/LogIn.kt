package com.example.foodapp

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.foodapp.Model.Users
import com.example.foodapp.databinding.ActivityLogInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase

class LogIn : AppCompatActivity(){
    lateinit var binding: ActivityLogInBinding
    lateinit var progressDialog: ProgressDialog
    lateinit var auth: FirebaseAuth
    lateinit var mGoogleSignInClient:GoogleSignInClient
    private lateinit var database: FirebaseDatabase
    val Req_Code: Int = 97
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding= ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog= ProgressDialog(this)
        progressDialog.setTitle("Account is signing in..")
        progressDialog.setMessage("Please wait..")
        supportActionBar?.hide()
        database= FirebaseDatabase.getInstance()
        //configure Google signIn
        FirebaseApp.initializeApp(this)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        auth=FirebaseAuth.getInstance()


        binding.signupIn.setOnClickListener{
            try{
                if (binding.emailIn.text.toString().isEmpty()){
                    binding.emailIn.error = "Enter valid email"
                }
                if (binding.passwordIn.text.toString().isEmpty()){
                    binding.passwordIn.error = "Enter valid password"
                }

                progressDialog.show()

                auth.signInWithEmailAndPassword(binding.emailIn.text.toString(),binding.passwordIn.text.toString())
                    .addOnCompleteListener{
                            task->
                        if (task.isSuccessful){
                            progressDialog.dismiss()
                            startActivity(Intent(this,HomeActivity::class.java))
                        }
                        else{
                            progressDialog.dismiss()
                            Toast.makeText(this,task.exception?.message,Toast.LENGTH_SHORT).show()
                        }
                    }}
            catch (e:Exception){
                progressDialog.dismiss()
                Toast.makeText(this,e.message.toString(),Toast.LENGTH_SHORT).show()

            }
        }
        binding.googleIn.setOnClickListener{
            signInGoogle()
        }
        binding.accountSignUp.setOnClickListener{
            startActivity(Intent(this,SignupActivity::class.java))
        }
        if(auth.currentUser!=null){
            startActivity(Intent(this,HomeActivity::class.java))
        }
    }
    private fun signInGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, Req_Code)
    }

    // onActivityResult() function : this is where
    // we provide the task and data for the Google Account
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Req_Code) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }}
    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                UpdateUI(account)
            }
        } catch (e: ApiException) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    private fun UpdateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                var user: FirebaseUser? =auth.currentUser
                var users= Users()
                if (user != null) {
                    users?.setUserId(user.uid)
                }
                if (user != null) {
                    users?.setUsername(user.displayName.toString())
                }
                if (user != null) {
                    users?.setMail(user.email.toString())
                }
                if (user != null) {
                    database.reference.child("Users").child(user.uid).setValue(users)
                }

                // SavedPreference.setEmail(this,account.email.toString())
                //SavedPreference.setUsername(this,account.displayName.toString())

                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()


            }
        }
    }
    override fun onStart() {
        super.onStart()
        if (GoogleSignIn.getLastSignedInAccount(this) != null) {
            startActivity(
                Intent(
                    this, HomeActivity
                    ::class.java
                )
            )
            finish()
        }



    }
}