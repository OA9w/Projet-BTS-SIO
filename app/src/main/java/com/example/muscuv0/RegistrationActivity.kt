package com.example.muscuv0

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_layout)

        auth = FirebaseAuth.getInstance()

        val registerButton = findViewById<Button>(R.id.register_btn)

        registerButton.setOnClickListener {
            val username = findViewById<EditText>(R.id.registration_username).text.toString()
            val email = findViewById<EditText>(R.id.registration_email).text.toString()
            val password = findViewById<EditText>(R.id.registration_password).text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                // Show a message to the user indicating that fields are required
                return@setOnClickListener
            }

            // Create Account
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // User registration successful
                        // Optionally, you can update the UI or navigate to another screen
                        Log.d("RegistrationActivity", "Registration successful")

                        // For simplicity, let's finish the activity after registration
                        finish()
                    } else {
                        Log.e("RegistrationActivity", "Registration failed", task.exception)
                        // Handle specific registration failures and show relevant messages to the user
                    }
                }
        }
    }
}
