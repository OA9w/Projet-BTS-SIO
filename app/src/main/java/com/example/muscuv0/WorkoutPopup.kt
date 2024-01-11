package com.example.muscuv0

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.muscuv0.adapter.WorkoutAdapter

class WorkoutPopup(
    private val adapter: WorkoutAdapter,
    private val currentWorkout: WorkoutModel
) : Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.workout_detail_page)
        setupComponents()
        setupCloseButton()
        setupLikeButton()
        setupDetailButton()
    }

    private fun updateLike(button: ImageView) {

        if(currentWorkout.liked) {
            button.setImageResource(R.drawable.ic_saved)
        }
        else {
            button.setImageResource(R.drawable.ic_unsaved)
        }
    }

    private fun setupLikeButton() {
        val LikeButton = findViewById<ImageView>(R.id.not_like_icon)
        updateLike(LikeButton)


        LikeButton.setOnClickListener {
            currentWorkout.liked = !currentWorkout.liked
            val repo = WorkoutRepository()
            repo.updateWorkout(currentWorkout)
            updateLike(LikeButton)
        }

    }
    private fun setupDetailButton() {
        findViewById<ImageView>(R.id.go_to_icon).setOnClickListener{
            setContentView(R.layout.workout_page)
        }
    }
    private fun setupCloseButton() {
        findViewById<ImageView>(R.id.close_button).setOnClickListener {
            dismiss()
        }
    }

    private fun setupComponents() {
        // actualiser image workout
        val WorkoutImage = findViewById<ImageView>(R.id.image_item)
        Glide.with(adapter.context).load(Uri.parse(currentWorkout.imageUrl)).into(WorkoutImage)

        // actualiser le nom du workout
        findViewById<TextView>(R.id.popup_name_workout).text = currentWorkout.name

        // actualiser la description
        findViewById<TextView>(R.id.popup_page_workout_description).text = currentWorkout.description
    }

}