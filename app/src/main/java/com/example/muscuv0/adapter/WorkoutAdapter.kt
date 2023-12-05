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

class WorkoutAdapter(
    val context: MainActivity,
    private val workoutList: List<WorkoutModel>,
    private val layoutId: Int) : RecyclerView.Adapter<WorkoutAdapter.ViewHolder>(){

    //boite pour ranger tt les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val workoutImage: ImageView = view.findViewById(R.id.workout_image)
        val workoutName:TextView? = view.findViewById(R.id.name_workout)
        val likeIcon: ImageView = view.findViewById(R.id.not_like_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // recuperer info du workout
        val currentWorkout = workoutList[position]

        // recuperer le repository
        val repo = WorkoutRepository()

        // utiliser glide
        Glide.with(context).load(Uri.parse(currentWorkout.imageUrl)).into(holder.workoutImage)

        // mettre a jour nom du workout
        holder.workoutName?.text = currentWorkout.name

        // verifier si workout like ou pas
        if(currentWorkout.liked) {
            holder.likeIcon.setImageResource(R.drawable.ic_saved)
        }
        else {
            holder.likeIcon.setImageResource(R.drawable.ic_unsaved)
        }

        //rajouter une interaction sur le like
        holder.likeIcon.setOnClickListener {
            // inverser si like ou pas like
            currentWorkout.liked = !currentWorkout.liked
            // mettre a jour l'objet workout
            repo.updateWorkout(currentWorkout)
        }

        // interaction lors du clic sur un workout
        holder.itemView.setOnClickListener {
            // afficher la pop up
            WorkoutPopup(this, currentWorkout).show()
        }
    }

    override fun getItemCount(): Int = workoutList.size


}