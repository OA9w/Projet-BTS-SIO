package com.example.muscuv0

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class ConnexionActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.connexion_start)

        FirebaseApp.initializeApp(this)

        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        val gotoRegistrationButton = findViewById<Button>(R.id.goto_registration_btn)
        gotoRegistrationButton.setOnClickListener {
            setContentView(R.layout.registration_layout)
        }
        val loginButton = findViewById<Button>(R.id.login_btn)
        val userNameLogin = findViewById<EditText>(R.id.login_username_email)
        val userPassword = findViewById<EditText>(R.id.login_password)

        loginButton.setOnClickListener {
            val email = userNameLogin.text.toString()
            val password = userPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                // Show a message to the user indicating that fields are required
                return@setOnClickListener
            }

            // Sign In
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // User login successful
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Log.e("LoginActivity", "Login failed", task.exception)
                    }
                }
        }
    }
}