package com.android.shedule

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.shedule.adapter.FirstWeekAdapter
import com.android.shedule.adapter.SecondWeekAdapter
import com.android.shedule.api.GroupApi
import com.android.shedule.api.ScheduleApi
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

        val thirdSubjectsList: ArrayList<String> = arrayListOf()
        val thirdTeachersList: ArrayList<String> = arrayListOf()
        val thirdAuditoriumList: ArrayList<String> = arrayListOf()
        val thirdTimeList: ArrayList<String> = arrayListOf()

        val scheduleMap: MutableMap<String, ArrayList<String>> = mutableMapOf()
        for(i in 0 until 3) {
            scheduleMap += mutableMapOf("Subjects $i" to arrayListOf())
            scheduleMap += mutableMapOf("Teachers $i" to arrayListOf())
            scheduleMap += mutableMapOf("Time $i" to arrayListOf())
            scheduleMap += mutableMapOf("Auditorium $i" to arrayListOf())
        }

        CoroutineScope(Dispatchers.Main).launch{
            val groupId = groupApi.getGroupByNameAndCourse(groupName, groupCourse.toInt()).id
            for(i in 1 until 7) {
                val scheduleList = scheduleApi.getScheduleByGroupIdAndWeekIdAndWeekType(groupId, i, 1)

                for(i in scheduleList.indices){
                    scheduleMap["Subjects $i"]!! += scheduleList[i].subject.name
                    scheduleMap["Teachers $i"]!! += scheduleList[i].subject.teacher.fullName
                    scheduleMap["Time $i"]!! += scheduleList[i].time.time.substringBeforeLast(':')
                    scheduleMap["Auditorium $i"]!! += "Аудитория: " + scheduleList[i].subject.auditorium
                }

                if(scheduleList.size > 2) {
                    thirdSubjectsList += scheduleList[2].subject.name
                    thirdTeachersList += scheduleList[2].subject.teacher.fullName
                    thirdTimeList += scheduleList[2].time.time.substringBeforeLast(':')
                    thirdAuditoriumList += "Аудитория: " + scheduleList[2].subject.auditorium
                }else {
                    thirdSubjectsList += " "
                    thirdTeachersList += " "
                    thirdTimeList += " "
                    thirdAuditoriumList += " "
                }
            }
            dayRecyclerView(thirdSubjectsList, thirdTimeList, thirdAuditoriumList, thirdTeachersList, scheduleMap)
        }
    }

    private fun dayRecyclerView(thirdSubjectList: ArrayList<String>, thirdTimeList: ArrayList<String>,
                        thirdAuditoriumList: ArrayList<String>, thirdTeachersList: ArrayList<String>,
                        scheduleMap: MutableMap<String, ArrayList<String>>) {
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

        weekRecyclerView.adapter = SecondWeekAdapter(imageList, thirdSubjectList, thirdTimeList, thirdAuditoriumList,
            thirdTeachersList, scheduleMap)
    }
}