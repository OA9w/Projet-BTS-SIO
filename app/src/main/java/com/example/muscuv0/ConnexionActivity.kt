package com.example.muscuv0

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.muscuv0.MainActivity
import com.example.muscuv0.R
import com.example.muscuv0.WorkoutModel
import com.example.muscuv0.WorkoutRepository
import java.util.UUID

class ConnexionActivity : AppCompatActivity() {

    fun onStart(view: View) {
        setContentView(R.layout.connexion_start)

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