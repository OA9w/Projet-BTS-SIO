package com.example.muscuv0

import com.example.muscuv0.ExerciceRepository.Singleton.databaseRef
import com.example.muscuv0.ExerciceRepository.Singleton.exerciceList
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class ExerciceRepository {

    object Singleton {

        private val BUCKET_URL: String = "gs://muscuv-0.appspot.com"

        // se connecter a l'espace de stockage
        val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(BUCKET_URL)

        // se connecter à la reference "workout"
        val databaseRef =
            FirebaseDatabase.getInstance("https://muscuv-0-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("Exercice")

        // creer une liste qui va contenir nos workout
        val exerciceList = arrayListOf<ExerciceModel>()
    }

    fun updateData(callback: () -> Unit) {
        // absorber les donnees depuis la databaseRef
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //retirer les anciennes
                exerciceList.clear()
                // recolter la liste
                for (ds in snapshot.children) {
                    // construire un objet workout
                    val exercice = ds.getValue(ExerciceModel::class.java)

                    // verifier que le workout n'est pas nul
                    if (exercice != null) {
                        // ajouter workout à la liste
                        exerciceList.add(exercice)
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
    fun updateExercice(exercice: ExerciceModel) = databaseRef.child(exercice.id).setValue(exercice)

}
