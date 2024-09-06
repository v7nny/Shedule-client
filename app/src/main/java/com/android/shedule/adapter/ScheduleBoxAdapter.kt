package com.android.shedule.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.RecyclerView
import com.android.shedule.FirstWeek
import com.android.shedule.R
import com.android.shedule.ScheduleActivity
import com.android.shedule.api.GroupApi
import com.android.shedule.api.ScheduleApi
import com.android.shedule.config.DbConfig
import com.android.shedule.models.ScheduleBoxDbEntity
import com.android.shedule.models.ScheduleDbEntity
import com.android.shedule.models.ScheduleForDrawing
import com.android.shedule.retrofit.RetrofitGetter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ScheduleBoxAdapter(private val scheduleBoxList: List<ScheduleBoxDbEntity>,
                         private val imageId: Int,
                         val context: Context,): RecyclerView.Adapter<ScheduleBoxAdapter.ScheduleBoxHolder>() {

    private val retrofit = RetrofitGetter()
    private val scheduleApi = retrofit.getRetrofit().create(ScheduleApi::class.java)
    private val groupApi = retrofit.getRetrofit().create(GroupApi::class.java)

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
        return scheduleBoxList.size
    }

    override fun onBindViewHolder(holder: ScheduleBoxHolder, position: Int) {
        holder.imageId.setImageResource(imageId)

        holder.specializationName.text = scheduleBoxList[position].specializationName
        holder.course.text = "Курс: " + scheduleBoxList[position].course
        holder.groupName.text = scheduleBoxList[position].groupName


        holder.weekButton.setOnClickListener {
            toFirstWeek(position)
        }

        holder.deleteButton.setOnClickListener {
            deleteScheduleBox(position)
        }
    }

    private  fun toFirstWeek(position: Int) {
        val firstWeek = Intent(context, FirstWeek::class.java)

        firstWeek.putExtra("groupName", scheduleBoxList[position].groupName)
        firstWeek.putExtra("course", scheduleBoxList[position].course)

        context.startActivity(firstWeek)
    }


    private fun deleteScheduleBox(position: Int) {
        val scheduleActivity = Intent(context, ScheduleActivity::class.java)
        val database = DbConfig.getDb(context)

        val scheduleBox = ScheduleBoxDbEntity(
            scheduleBoxList[position].groupName,
            scheduleBoxList[position].course,
            scheduleBoxList[position].specializationName
        )

        Thread {
            database.getScheduleBoxDao().deleteScheduleBox(scheduleBox)
        }.start()
        context.startActivity(scheduleActivity)
    }
}