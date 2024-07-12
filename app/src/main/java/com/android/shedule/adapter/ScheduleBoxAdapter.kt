package com.android.shedule.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.shedule.R
import com.android.shedule.models.ScheduleBox

class ScheduleBoxAdapter constructor(private val array: ArrayList<ScheduleBox>,
                                     private val imageId: Int): RecyclerView.Adapter<ScheduleBoxAdapter.ScheduleBoxHolder>() {

    class ScheduleBoxHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageId: ImageView = itemView.findViewById(R.id.scheduleBox)

        val specializationName: TextView = itemView.findViewById(R.id.specializationName)
        val course: TextView = itemView.findViewById(R.id.course)
        val groupName: TextView = itemView.findViewById(R.id.groupName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleBoxHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_for_schedule, parent, false)
        return ScheduleBoxHolder(view)

    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: ScheduleBoxHolder, position: Int) {
        holder.imageId.setImageResource(imageId)

        holder.specializationName.text = array[position].specializationName
        holder.course.text = "Курс: " + array[position].course
        holder.groupName.text = array[position].groupName
    }

}