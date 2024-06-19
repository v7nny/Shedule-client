package com.android.shedule.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.shedule.R
import com.android.shedule.models.Group
import com.android.shedule.models.Specialization

class ScheduleBoxAdapter constructor(private val specializationNameList: ArrayList<String>,
                                     private val courseList: ArrayList<String>,
                                     private val groupNameList: ArrayList<String>,
                                     val imageId: Int): RecyclerView.Adapter<ScheduleBoxAdapter.ScheduleBoxHolder>() {




    class ScheduleBoxHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val specializationName = itemView.findViewById<TextView>(R.id.specializationName)
        val course = itemView.findViewById<TextView>(R.id.course)
        val groupName = itemView.findViewById<TextView>(R.id.groupName)
        val imageId = itemView.findViewById<ImageView>(R.id.scheduleBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleBoxHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_for_schedule, parent, false)
        return ScheduleBoxHolder(view)

    }

    override fun getItemCount(): Int {
        return specializationNameList.size
    }

    override fun onBindViewHolder(holder: ScheduleBoxHolder, position: Int) {
        holder.specializationName.text = specializationNameList[position]
        holder.course.text = "Курс: " + courseList[position]
        holder.groupName.text = groupNameList[position]
        holder.imageId.setImageResource(imageId)
    }

}