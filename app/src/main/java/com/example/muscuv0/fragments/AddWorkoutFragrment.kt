package com.example.muscuv0.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
<<<<<<< HEAD
import android.view.View.OnClickListener
=======
>>>>>>> 35e2a39 (Initial commit)
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.muscuv0.MainActivity
import com.example.muscuv0.R
import com.example.muscuv0.WorkoutModel
import com.example.muscuv0.WorkoutRepository
import java.util.UUID

class AddWorkoutFragrment(
    private val context: MainActivity
) : Fragment() {

    private var uploadedImage:ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
<<<<<<< HEAD


        val view = inflater?.inflate(R.layout.fragment_add_workout, container, false)



=======
        val view = inflater?.inflate(R.layout.fragment_add_workout, container, false)

>>>>>>> 35e2a39 (Initial commit)
        // recuper uploadImage pourlui associer son composant
        uploadedImage = view!!.findViewById(R.id.preview_image)

        // recuperer boutton charger une image
        val pickupImageButton = view!!.findViewById<Button>(R.id.upload_button)

        pickupImageButton.setOnClickListener { pickupImage() }

        val confirmButton = view.findViewById<Button>(R.id.save_workout_button)
        confirmButton.setOnClickListener { sendForm(view) }

        return view
    }


<<<<<<< HEAD

    private fun openlist(view: View) {
        val btn = view.findViewById<Button>(R.id.add_exercice_button)
        btn.setOnClickListener {

        }
    }



// a completer quand l'ajoue d'exo marchera!!!!!!
=======
    // a completer quand l'ajoue d'exo marchera!!!!!!
>>>>>>> 35e2a39 (Initial commit)
    private fun sendForm(view: View) {
        val repo = WorkoutRepository()
        val workoutName = view.findViewById<EditText>(R.id.workout_name_input).text.toString()
        val workoutDesciption = view.findViewById<EditText>(R.id.workout_description_input).text.toString()


        val workout = WorkoutModel(
            UUID.randomUUID().toString(),
            workoutName,
            workoutDesciption,
        )
        repo.insertWorkout(workout)
    }

    private fun pickupImage() {
        val intent = Intent()
        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Image"), 47)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 47 && resultCode == Activity.RESULT_OK){

            //verifier si les données sont nulles
            if(data == null || data.data == null) return

            // recuperer l'image selectionnée
            val selectedImage = data.data

            // mettre a jour l'apercu de l'image
            uploadedImage?.setImageURI(selectedImage)


        }
    }

<<<<<<< HEAD
    override fun getView(): View? {
        return super.getView()
    }

=======
>>>>>>> 35e2a39 (Initial commit)
}