package com.android.shedule

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.shedule.adapter.SecondWeekAdapter
import com.android.shedule.api.GroupApi
import com.android.shedule.api.ScheduleApi
import com.android.shedule.models.ScheduleForDrawing
import com.android.shedule.retrofit.RetrofitGetter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SecondWeek : ComponentActivity() {
    private val retrofit = RetrofitGetter()
    private val scheduleApi = retrofit.getRetrofit().create(ScheduleApi::class.java)
    private val groupApi = retrofit.getRetrofit().create(GroupApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_week)
        overridePendingTransition(0,0)
        window.statusBarColor = resources.getColor(R.color.sheduleBarColor)
        window.navigationBarColor = resources.getColor(R.color.sheduleBarColor)
        schedule()
    }

    fun toFirstWeek(view: View) = finish()

    private fun schedule() {
        val intent: Intent = intent
        val groupName = intent.getStringExtra("groupName").toString()
        val groupCourse = intent.getStringExtra("course").toString()

        val scheduleForDrawingArray : Array<Array<ScheduleForDrawing>> = Array(6){
            Array(3){
                ScheduleForDrawing("", "", "", "")
            }
        }

        CoroutineScope(Dispatchers.Main).launch{
            val groupId = groupApi.getGroupByNameAndCourse(groupName, groupCourse.toInt()).id
            for(i in 1 until 7) {
                val scheduleList = scheduleApi.getScheduleByGroupIdAndWeekIdAndWeekType(groupId, i, 1)

                for(j in scheduleList.indices){
                    scheduleForDrawingArray[i - 1][j].subject = scheduleList[j].subject.name
                    scheduleForDrawingArray[i - 1][j].teacher = scheduleList[j].subject.teacher.fullName
                    scheduleForDrawingArray[i - 1][j].time = scheduleList[j].time.time.substringBeforeLast(':')
                    scheduleForDrawingArray[i - 1][j].auditorium = "Аудитория: " + scheduleList[j].subject.auditorium
                }
            }
            dayRecyclerView(scheduleForDrawingArray)
        }
    }

    private fun dayRecyclerView(scheduleForDrawingArray: Array<Array<ScheduleForDrawing>>) {
        val weekRecyclerView = findViewById<RecyclerView>(R.id.recyclerViewSecondWeek)
        val imageList = arrayListOf(
            R.drawable.monday_second,
            R.drawable.tuesday_second,
            R.drawable.wednesday_second,
            R.drawable.thursday_second,
            R.drawable.friday_second,
            R.drawable.saturday_second
        )

        weekRecyclerView.layoutManager = LinearLayoutManager(this)

        weekRecyclerView.adapter = SecondWeekAdapter(imageList, scheduleForDrawingArray)
    }
}