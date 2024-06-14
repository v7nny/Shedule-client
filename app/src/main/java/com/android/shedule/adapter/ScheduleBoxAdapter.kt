package com.android.shedule.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.shedule.R
import com.android.shedule.models.Group
import com.android.shedule.models.Specialization

class ScheduleBoxAdapter(grouList:ArrayList<Group>, specializationName: ArrayList<Specialization>): RecyclerView.Adapter<ScheduleBoxAdapter.ScheduleBoxHolder>() {

    class ScheduleBoxHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val specializationName = itemView.findViewById<TextView>(R.id.specializationName)
        val course = itemView.findViewById<TextView>(R.id.course)
        val groupName = itemView.findViewById<TextView>(R.id.groupName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleBoxHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_for_schedule, parent, false)
        return ScheduleBoxHolder(view)

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ScheduleBoxHolder, position: Int) {
        TODO("Not yet implemented")
    }


}