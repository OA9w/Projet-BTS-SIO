package com.example.muscuv0.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.muscuv0.*

class ExerciceAdapter(
    val context: MainActivity,
    private val exerciceList: List<ExerciceModel>,
    private val layoutId: Int) : RecyclerView.Adapter<ExerciceAdapter.ViewHolder>() {

    //boite pour ranger tt les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val exerciceImage: ImageView = view.findViewById(R.id.exercice_image)
        val exerciceName: TextView? = view.findViewById(R.id.name_exercice)
        val likeIcon: ImageView = view.findViewById(R.id.add_exercice_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciceAdapter.ViewHolder, position: Int) {
        // recuperer info du workout
        val currentExercice = exerciceList[position]

        // recuperer le repository
        val repo = ExerciceRepository()

        // utiliser glide
        Glide.with(context).load(Uri.parse(currentExercice.imageUrl)).into(holder.exerciceImage)

        // mettre a jour nom du workout
        holder.exerciceName?.text = currentExercice.name

        // verifier si workout like ou pas
        if (currentExercice.added) {
            holder.likeIcon.setImageResource(R.drawable.ic_saved)
        } else {
            holder.likeIcon.setImageResource(R.drawable.ic_unsaved)
        }

        //rajouter une interaction sur le like
        holder.likeIcon.setOnClickListener {
            // inverser si like ou pas like
            currentExercice.added = !currentExercice.added
            // mettre a jour l'objet workout
            repo.updateExercice(currentExercice)
        }

    }

    override fun getItemCount(): Int = exerciceList.size
}
