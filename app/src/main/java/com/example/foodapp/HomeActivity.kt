package com.example.foodapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {
    lateinit var authe: FirebaseAuth
    lateinit var mGoogleSignInClient: GoogleSignInClient
    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

       authe=FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient= GoogleSignIn.getClient(this,gso)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val button: Button = findViewById(R.id.button1)
        button.setOnClickListener {
            val intent = Intent(this,Add_To_Cart ::class.java)
            startActivity(intent)
        }

        /*val button_cart: FloatingActionButton = findViewById(R.id.cartBtn)

        button_cart.setOnClickListener {
           val intent = Intent(this,My_Cart ::class.java)
            startActivity(intent)
        }*/
        val button2: Button = findViewById(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(this,Add_To_Cart1 ::class.java)
            startActivity(intent)
        }
        val button3: Button = findViewById(R.id.button3)
        button3.setOnClickListener {
            val intent = Intent(this,Add_To_Cart2 ::class.java)
            startActivity(intent)
        }
        val button4: Button = findViewById(R.id.button4)
        button4.setOnClickListener {
            val intent = Intent(this,Add_To_Cart3 ::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item1 -> {
                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "apk/file"
                startActivity(Intent.createChooser(sharingIntent, "Share using"))
            }
            R.id.item2 -> {
                this.startActivity(Intent(this, Give_Rating::class.java))
                return true
            }
            R.id.item3 -> {
                this.startActivity(Intent(this, Help::class.java))
                return true
            }
            R.id.item4->{
               authe.signOut()
                mGoogleSignInClient.signOut()
                startActivity(Intent(this,LogIn::class.java))
                finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }
}
//hhh//









