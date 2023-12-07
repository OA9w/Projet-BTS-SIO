package com.example.muscuv0

import android.os.Bundle
import android.widget.Button
<<<<<<< HEAD
=======
import android.widget.TextView
>>>>>>> 35e2a39 (Initial commit)
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.muscuv0.fragments.AddWorkoutFragrment
import com.example.muscuv0.fragments.CollectionFragment
import com.example.muscuv0.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

<<<<<<< HEAD

=======
        // bouton home
>>>>>>> 35e2a39 (Initial commit)
        val btnclickme = findViewById<FloatingActionButton>(R.id.home_button)
        btnclickme.setOnClickListener {
            loadFragment(HomeFragment(this))
        }

        //importer nav view
        val navigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId)
            {
<<<<<<< HEAD

=======
                // bouton creer workout
>>>>>>> 35e2a39 (Initial commit)
                R.id.create_workout -> {
                    loadFragment(AddWorkoutFragrment(this))
                    return@setOnNavigationItemSelectedListener true
                }
<<<<<<< HEAD
=======
                // bouton workout liked
>>>>>>> 35e2a39 (Initial commit)
                R.id.saved_workout -> {
                    loadFragment(CollectionFragment(this))
                    return@setOnNavigationItemSelectedListener true
                }
<<<<<<< HEAD

=======
>>>>>>> 35e2a39 (Initial commit)
                else -> false
            }
        }




    }




    private fun loadFragment(fragment: Fragment) {

        val repo = WorkoutRepository()

        // actualiser titre de la page

        // mettre à jour la liste de workout
        repo.updateData {
            // injecter le fragment dans notre boite (fragment_container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit() }

    }

}

