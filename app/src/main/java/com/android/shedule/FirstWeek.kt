package com.android.shedule

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.shedule.adapter.FirstWeekAdapter
import com.android.shedule.api.GroupApi
import com.android.shedule.api.ScheduleApi
import com.android.shedule.models.ScheduleForDrawing
import com.android.shedule.retrofit.RetrofitGetter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FirstWeek : ComponentActivity() {
    private val retrofit = RetrofitGetter()
    private val scheduleApi = retrofit.getRetrofit().create(ScheduleApi::class.java)
    private val groupApi = retrofit.getRetrofit().create(GroupApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_week)
        window.statusBarColor = resources.getColor(R.color.sheduleBarColor)
        window.navigationBarColor = resources.getColor(R.color.sheduleBarColor)
        overridePendingTransition(0,0)
        schedule()
    }

    fun toSecondWeek(view: View) {
        val intent: Intent = intent
        val groupName = intent.getStringExtra("groupName").toString()
        val groupCourse = intent.getStringExtra("course").toString()

        val secondWeekIntent = Intent(this, SecondWeek::class.java)

        secondWeekIntent.putExtra("groupName", groupName)
        secondWeekIntent.putExtra("course", groupCourse)
        startActivity(secondWeekIntent)
    }

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
                val scheduleList = scheduleApi.getScheduleByGroupIdAndWeekIdAndWeekType(groupId, i, 0)

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
        val weekRecyclerView = findViewById<RecyclerView>(R.id.recyclerViewFirstWeek)
        val imageList = arrayListOf(
            R.drawable.monday_first,
            R.drawable.tuesday_first,
            R.drawable.wednesday_first,
            R.drawable.thursday_first,
            R.drawable.friday_first,
            R.drawable.saturday_first
        )

        weekRecyclerView.layoutManager = LinearLayoutManager(this)

        weekRecyclerView.adapter = FirstWeekAdapter(imageList, scheduleForDrawingArray)
    }
}