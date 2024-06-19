package com.android.shedule

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.shedule.adapter.FirstWeekAdapter
import com.android.shedule.api.GroupApi
import com.android.shedule.api.ScheduleApi
import com.android.shedule.api.SpecializationApi
import com.android.shedule.models.Group
import com.android.shedule.models.Schedule
import com.android.shedule.retrofit.RetrofitGetter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
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
        val secondWeekIntent = Intent(this, SecondWeek::class.java)
        startActivity(secondWeekIntent)
        finish()
    }

    private fun schedule() {
        val intent: Intent = intent
        val groupName = intent.getStringExtra("groupName").toString()
        val groupCourse = intent.getStringExtra("course").toString()

        val firstSubjectList: ArrayList<String> = arrayListOf()
        val firstTimeList: ArrayList<String> = arrayListOf()
        val firstAuditorium: ArrayList<String> = arrayListOf()


        CoroutineScope(Dispatchers.Main).launch{
            val groupId = groupApi.getGroupByNameAndCourse(groupName, groupCourse.toInt()).id
            for(i in 0 until 6) {
                firstSubjectList += scheduleApi.getScheduleByGroupId(groupId)[i].subjectFirst.name
                firstTimeList += scheduleApi.getScheduleByGroupId(groupId)[i].firstTime.time.substringBeforeLast(':')
                firstAuditorium += scheduleApi.getScheduleByGroupId(groupId)[i].subjectFirst.auditorium
            }
            dayRecyclerView(firstSubjectList, firstTimeList, firstAuditorium)
        }
    }


    fun dayRecyclerView(firstSubjectList: ArrayList<String>, firstTimeList: ArrayList<String>,
                         firstAuditoriumList: ArrayList<String>) {
        val weekRecyclerView = findViewById<RecyclerView>(R.id.weekRecyclerView)
        val imageList = arrayListOf(
            R.drawable.monday,
            R.drawable.tuesday,
            R.drawable.wednesday,
            R.drawable.thursday,
            R.drawable.friday,
            R.drawable.saturday
        )

        weekRecyclerView.layoutManager = LinearLayoutManager(this)

        weekRecyclerView.adapter = FirstWeekAdapter(imageList, firstSubjectList,
            firstTimeList, firstAuditoriumList)
    }
}