package com.android.shedule.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.shedule.R
import com.android.shedule.models.ScheduleForDrawing

class SecondDaysAdapter(private val scheduleForDrawing: Array<ScheduleForDrawing>): RecyclerView.Adapter<SecondDaysAdapter.SecondDaysHolder>() {

    class SecondDaysHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val subjectName: TextView = itemView.findViewById(R.id.subjectName)
        val teacherName: TextView = itemView.findViewById(R.id.teacherName)
        val auditoriumName: TextView = itemView.findViewById(R.id.auditoriumName)
        val timeTextViewFirst: TextView = itemView.findViewById(R.id.timeTextViewFirst)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondDaysHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_for_days, parent, false)
        return SecondDaysHolder(view)
    }

    override fun getItemCount(): Int {
        return scheduleForDrawing.size
    }

    override fun onBindViewHolder(holder: SecondDaysHolder, position: Int) {
        holder.subjectName.text = scheduleForDrawing[position].subject
        holder.teacherName.text = scheduleForDrawing[position].teacher
        holder.auditoriumName.text = scheduleForDrawing[position].auditorium
        holder.timeTextViewFirst.text = scheduleForDrawing[position].time
    }
}