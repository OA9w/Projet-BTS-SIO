package com.example.muscuv0
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class ConnexionActivity : AppCompatActivity() {

    // Function to retrieve the token locally
    private fun retrieveTokenLocally(): String? {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("userToken", null)
    }

    private fun showError(message: String) {
        val errorMessageTextView = findViewById<TextView>(R.id.errorMessageTextView)
        errorMessageTextView.text = message
        errorMessageTextView.visibility = View.VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if a token is already saved
        val savedToken = retrieveTokenLocally()

        if (savedToken != null) {
            // If a token is found, go directly to the main activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

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
        val errorMessageTextView = findViewById<TextView>(R.id.errorMessageTextView)

        loginButton.setOnClickListener {
            val email = userNameLogin.text.toString()
            val password = userPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                // Show a message to the user indicating that fields are required
                return@setOnClickListener
            }

            // Function to save the token locally
            fun saveTokenLocally(token: String?) {
                val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("userToken", token)
                editor.apply()
            }

            // Sign In
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // User login successful
                        val user = auth.currentUser

                        user?.getIdToken(true)
                            ?.addOnCompleteListener { idTokenTask ->
                                if (idTokenTask.isSuccessful) {
                                    // Token retrieved successfully
                                    val token = idTokenTask.result?.token

                                    // Store the token locally (in SharedPreferences for simplicity)
                                    saveTokenLocally(token)

                                    // Now you can use the token as needed
                                    // For example, you might want to send it to your server

                                    val intent = Intent(this, MainActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    // Handle error getting token
                                    Log.e("LoginActivity", "Failed to get token", idTokenTask.exception)
                                    showError("Failed to get authentication token.")
                                }
                            }
                    } else {
                        Log.e("LoginActivity", "Login failed", task.exception)
                        showError("Authentication failed. Please check your credentials.")
                    }
                }
        }
    }
}