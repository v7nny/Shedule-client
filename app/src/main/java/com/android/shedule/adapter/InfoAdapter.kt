package com.android.shedule.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.shedule.R

class InfoAdapter : RecyclerView.Adapter<InfoAdapter.InfoHolder>() {

    class InfoHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val subjectNameTextView = itemView.findViewById<TextView>(R.id.subjectName)
        val teacherNameTextView = itemView.findViewById<TextView>(R.id.teacherName)
        val auditoriumNameTextView = itemView.findViewById<TextView>(R.id.auditoriumName)
        val timeTextView = itemView.findViewById<TextView>(R.id.timeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: InfoHolder, position: Int) {
        TODO("Not yet implemented")
    }
}