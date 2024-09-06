package com.android.shedule.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.shedule.R
import com.android.shedule.models.ScheduleDbEntity
import com.android.shedule.models.ScheduleForDrawing

class SecondWeekAdapter (private val imageList: ArrayList<Int>,
                         private val scheduleForDrawingArray: Array<List<ScheduleDbEntity>>
): RecyclerView.Adapter<SecondWeekAdapter.SecondWeekHolder>() {

    class SecondWeekHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val dayImageList: ImageView = itemView.findViewById(R.id.dayImage)

        val recyclerViewForDays: RecyclerView = itemView.findViewById(R.id.recyclerViewForSecondWeekDays)
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

        holder.recyclerViewForDays.layoutManager = LinearLayoutManager(holder.recyclerViewForDays.context)
        holder.recyclerViewForDays.adapter = SecondDaysAdapter(scheduleForDrawingArray[position])
    }
}