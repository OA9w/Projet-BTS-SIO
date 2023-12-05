package com.example.muscuv0.fragments

import com.example.muscuv0.adapter.WorkoutAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.muscuv0.MainActivity
import com.example.muscuv0.R
import com.example.muscuv0.WorkoutModel
import com.example.muscuv0.WorkoutRepository.Singleton.workoutList

class HomeFragment(
    private val context: MainActivity
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_home, container, false)


        val verticalRecyclerView = view!!.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = WorkoutAdapter(context,workoutList, R.layout.item_vertical_workout)

        return view
    }

}