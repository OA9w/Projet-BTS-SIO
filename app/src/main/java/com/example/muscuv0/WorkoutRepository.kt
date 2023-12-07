package com.example.muscuv0

import com.example.muscuv0.WorkoutRepository.Singleton.databaseRef
import com.example.muscuv0.WorkoutRepository.Singleton.workoutList
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class WorkoutRepository {

    object Singleton {

        private val BUCKET_URL: String = "gs://muscuv-0.appspot.com"

        // se connecter a l'espace de stockage
        val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(BUCKET_URL)

        // se connecter à la reference "workout"
        val databaseRef = FirebaseDatabase.getInstance("https://muscuv-0-default-rtdb.europe-west1.firebasedatabase.app").getReference("workout")

        // creer une liste qui va contenir nos workout
        val workoutList = arrayListOf<WorkoutModel>()


    }

    fun updateData(callback: () -> Unit) {
        // absorber les donnees depuis la databaseRef
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //retirer les anciennes
                workoutList.clear()
                // recolter la liste
                for (ds in snapshot.children) {
                    // construire un objet workout
                    val workout = ds.getValue(WorkoutModel::class.java)

                    // verifier que le workout n'est pas nul
                    if(workout != null) {
                        // ajouter workout à la liste
                        workoutList.add(workout)
                    }
                }
                // actionner le callback
                callback()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    // mettre a jour un objet en bdd
    fun updateWorkout(workout: WorkoutModel) = databaseRef.child(workout.id).setValue(workout)

    fun insertWorkout(workout: WorkoutModel) = databaseRef.child(workout.id).setValue(workout)

    fun deleteWorkout(workout: WorkoutModel) = databaseRef.child(workout.id).removeValue()
}