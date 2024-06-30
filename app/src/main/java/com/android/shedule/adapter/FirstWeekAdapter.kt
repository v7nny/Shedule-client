package com.android.shedule.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.shedule.R
import com.android.shedule.ScheduleForDrawing
import com.android.shedule.models.Schedule

class FirstWeekAdapter (private val imageList: ArrayList<Int>,
                        private val scheduleForDrawingArray: ArrayList<ScheduleForDrawing>
) : RecyclerView.Adapter<FirstWeekAdapter.FirstWeekHolder>() {

    class FirstWeekHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val dayImageList: ImageView = itemView.findViewById(R.id.dayImage)

        val subjectNameFirst: TextView = itemView.findViewById(R.id.subjectNameFirst)
        val teacherNameFirst: TextView = itemView.findViewById(R.id.teacherNameFirst)
        val auditoriumNameFirst: TextView = itemView.findViewById(R.id.auditoriumNameFirst)
        val timeTextViewFirst: TextView = itemView.findViewById(R.id.timeTextViewFirst)

        val subjectNameSecond: TextView = itemView.findViewById(R.id.subjectNameSecond)
        val teacherNameSecond: TextView = itemView.findViewById(R.id.teacherNameSecond)
        val auditoriumNameSecond: TextView = itemView.findViewById(R.id.auditoriumNameSecond)
        val timeTextViewSecond: TextView = itemView.findViewById(R.id.timeTextViewSecond)

        val subjectNameThird: TextView = itemView.findViewById(R.id.subjectNameThird)
        val teacherNameThird: TextView = itemView.findViewById(R.id.teacherNameThird)
        val auditoriumNameThird: TextView = itemView.findViewById(R.id.auditoriumNameThird)
        val timeTextViewThird: TextView = itemView.findViewById(R.id.timeTextViewThird)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstWeekHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_for_first_week, parent, false)
        return FirstWeekHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: FirstWeekHolder, position: Int) {
        holder.dayImageList.setImageResource(imageList[position])

        holder.subjectNameFirst.text = scheduleForDrawingArray[position].subject[0]
        holder.teacherNameFirst.text = scheduleForDrawingArray[position].teacher[0]
        holder.auditoriumNameFirst.text = scheduleForDrawingArray[position].auditorium[0]
        holder.timeTextViewFirst.text = scheduleForDrawingArray[position].time[0]

        holder.subjectNameSecond.text = scheduleForDrawingArray[position].subject[1]
        holder.teacherNameSecond.text = scheduleForDrawingArray[position].teacher[1]
        holder.auditoriumNameSecond.text = scheduleForDrawingArray[position].auditorium[1]
        holder.timeTextViewSecond.text = scheduleForDrawingArray[position].time[1]

        holder.subjectNameThird.text = scheduleForDrawingArray[position].subject[2]
        holder.teacherNameThird.text = scheduleForDrawingArray[position].teacher[2]
        holder.auditoriumNameThird.text = scheduleForDrawingArray[position].auditorium[2]
        holder.timeTextViewThird.text = scheduleForDrawingArray[position].time[2]
    }
}