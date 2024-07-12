package com.android.shedule.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.shedule.R
import com.android.shedule.models.ScheduleForDrawing

class FirstWeekAdapter (private val imageList: ArrayList<Int>,
                        private val scheduleForDrawingArray: Array<Array<ScheduleForDrawing>>
) : RecyclerView.Adapter<FirstWeekAdapter.FirstWeekHolder>() {

    class FirstWeekHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val dayImageList: ImageView = itemView.findViewById(R.id.dayImage)

        val recyclerViewForDays: RecyclerView = itemView.findViewById(R.id.recyclerViewForFirstWeekDays)
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

        holder.recyclerViewForDays.layoutManager = LinearLayoutManager(holder.recyclerViewForDays.context)
        holder.recyclerViewForDays.adapter = FirstDaysAdapter(scheduleForDrawingArray[position])
    }
}