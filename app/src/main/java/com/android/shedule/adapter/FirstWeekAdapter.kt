package com.android.shedule.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.android.shedule.FirstWeek
import com.android.shedule.R

class FirstWeekAdapter constructor(val imageList: ArrayList<Int>, val subjectNameFirst: ArrayList<String>,
    val firstTimeList: ArrayList<String>, val firstAuditorium: ArrayList<String>) : RecyclerView.Adapter<FirstWeekAdapter.FirstWeekHolder>() {




    class FirstWeekHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val dayList = itemView.findViewById<ImageView>(R.id.dayImage)
        val subjectNameFirst = itemView.findViewById<TextView>(R.id.subjectNameFirst)
        val teacherNameFirst = itemView.findViewById<TextView>(R.id.teacherNameFirst)
        val auditoriumNameFirst = itemView.findViewById<TextView>(R.id.auditoriumNameFirst)
        val timeTextViewFirst = itemView.findViewById<TextView>(R.id.timeTextViewFirst)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstWeekHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_for_week, parent, false)
        return FirstWeekHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: FirstWeekHolder, position: Int) {
        holder.dayList.setImageResource(imageList[position])
        holder.subjectNameFirst.text = subjectNameFirst[position]
        holder.teacherNameFirst.text = "Турченко Виктория Владимировна"
        holder.auditoriumNameFirst.text = "Аудитория: " + firstAuditorium[position]
        holder.timeTextViewFirst.text = firstTimeList[position]
    }
}