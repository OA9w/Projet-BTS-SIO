package com.example.muscuv0.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.muscuv0.MainActivity
import com.example.muscuv0.R
import com.example.muscuv0.WorkoutRepository.Singleton.workoutList
import com.example.muscuv0.adapter.WorkoutAdapter

class AddExerciceFragment(
    private val context: MainActivity
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_select_exercice, container, false)

        // recuperer recycler view
        val collectionRecyclerView = view!!.findViewById<RecyclerView>(R.id.exercice_recycler_list)
        collectionRecyclerView.adapter = WorkoutAdapter(context, workoutList.filter { it.liked }, R.layout.item_vertical_exercice)
        collectionRecyclerView.layoutManager = LinearLayoutManager(context)


        return view
    }

}