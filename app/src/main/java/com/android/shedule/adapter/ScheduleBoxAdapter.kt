package com.android.shedule.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.shedule.FirstWeek
import com.android.shedule.R
import com.android.shedule.ScheduleActivity
import com.android.shedule.config.DbConfig
import com.android.shedule.models.ScheduleBox
import com.android.shedule.models.ScheduleBoxDbEntity



class ScheduleBoxAdapter constructor(private val array: List<ScheduleBoxDbEntity>,
                                     private val imageId: Int,
                                     val context: Context,): RecyclerView.Adapter<ScheduleBoxAdapter.ScheduleBoxHolder>() {

    class ScheduleBoxHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageId: ImageView = itemView.findViewById(R.id.scheduleBox)

        val weekButton: Button = itemView.findViewById(R.id.scheduleButton)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)

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

        holder.weekButton.setOnClickListener {
            toFirstWeek(position)
        }

        holder.deleteButton.setOnClickListener {
            deleteScheduleBox(position)
        }
    }


    private  fun toFirstWeek(position: Int) {
        val firstWeek = Intent(context, FirstWeek::class.java)

        firstWeek.putExtra("groupName", array[position].groupName)
        firstWeek.putExtra("course", array[position].course)

        context.startActivity(firstWeek)
    }

    private fun deleteScheduleBox(position: Int) {
        val scheduleActivity = Intent(context, ScheduleActivity::class.java)
        val database = DbConfig.getDb(context)

        val scheduleBox = ScheduleBoxDbEntity(
            array[position].groupName,
            array[position].course,
            array[position].specializationName
        )

        Thread {
            database.getDao().deleteScheduleBox(scheduleBox)
        }.start()

        context.startActivity(scheduleActivity)
    }
}