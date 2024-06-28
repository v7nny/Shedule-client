package com.android.shedule.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.shedule.R
import com.android.shedule.adapter.FirstWeekAdapter.FirstWeekHolder

class SecondWeekAdapter(private val imageList: ArrayList<Int>, private val subjectNameThird: ArrayList<String>,
                        private val thirdTimeList: ArrayList<String>, private val thirdAuditorium: ArrayList<String>,
                        private val thirdTeachersList: ArrayList<String>, private val scheduleMap: MutableMap<String, ArrayList<String>>
): RecyclerView.Adapter<SecondWeekAdapter.SecondWeekHolder>() {

    class SecondWeekHolder(itemView: View): RecyclerView.ViewHolder(itemView){
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondWeekHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_for_second_week, parent, false)
        return SecondWeekHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: SecondWeekHolder, position: Int) {
        holder.dayImageList.setImageResource(imageList[position])

        holder.subjectNameFirst.text = scheduleMap["Subjects 0"]?.get(position)
        holder.teacherNameFirst.text = scheduleMap["Teachers 0"]?.get(position)
        holder.auditoriumNameFirst.text = scheduleMap["Auditorium 0"]?.get(position)
        holder.timeTextViewFirst.text = scheduleMap["Time 0"]?.get(position)

        holder.subjectNameSecond.text = scheduleMap["Subjects 1"]?.get(position)
        holder.teacherNameSecond.text = scheduleMap["Teachers 1"]?.get(position)
        holder.auditoriumNameSecond.text = scheduleMap["Auditorium 1"]?.get(position)
        holder.timeTextViewSecond.text = scheduleMap["Time 1"]?.get(position)

        holder.subjectNameThird.text = subjectNameThird[position]
        holder.teacherNameThird.text = thirdTeachersList[position]
        holder.auditoriumNameThird.text = thirdAuditorium[position]
        holder.timeTextViewThird.text = thirdTimeList[position]
    }
}