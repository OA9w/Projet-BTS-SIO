package com.example.muscuv0

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ConnexionActivity : AppCompatActivity() {
    fun onCreate(savedInstanceState: Bundle? , view: View) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.connexion_start)

        val loginbtn = findViewById<Button>(R.id.login_btn)
        loginbtn.setOnClickListener {
            val userNameLogin = view.findViewById<EditText>(R.id.login_username_email).text.toString()
            val userPassword = view.findViewById<EditText>(R.id.login_password).text.toString()

            val userLoginPassword = userNameLogin + userPassword

            if (userNameLogin === "Admin" && userPassword === "12345") {
                setContentView(R.layout.activity_main)
            } else if (userLoginPassword == "admin@mail.com12345") {
                setContentView(R.layout.activity_main)
            } else {
                println("Mot de passe incorrecte")
            }
        }
    }
}
