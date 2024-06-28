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
import com.android.shedule.models.Schedule
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

        val thirdSubjectsList: ArrayList<String> = arrayListOf()
        val thirdTeachersList: ArrayList<String> = arrayListOf()
        val thirdAuditoriumList: ArrayList<String> = arrayListOf()
        val thirdTimeList: ArrayList<String> = arrayListOf()

        val scheduleForDrawingArray : ArrayList<ScheduleForDrawing> = arrayListOf()
        val subjectArray: Array<String> = arrayOf("")
        val teacherArray: Array<String> = arrayOf("")
        val auditoriumArray: Array<String> = arrayOf("")
        val timeArray: Array<String> = arrayOf("")


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
                val scheduleList = scheduleApi.getScheduleByGroupIdAndWeekIdAndWeekType(groupId, i, 0)


                for(j in scheduleList.indices){
                    scheduleMap["Subjects $j"]!! += scheduleList[j].subject.name
                    subjectArray[j] = scheduleList[j].subject.name

                    scheduleMap["Teachers $j"]!! += scheduleList[j].subject.teacher.fullName
                     teacherArray[j] = scheduleList[j].subject.teacher.fullName

                    scheduleMap["Time $j"]!! += scheduleList[j].time.time.substringBeforeLast(':')
                    timeArray[j] = scheduleList[j].time.time.substringBeforeLast(':')

                    scheduleMap["Auditorium $j"]!! += "Аудитория: " + scheduleList[j].subject.auditorium
                    auditoriumArray[j] = "Аудитория: " + scheduleList[j].subject.auditorium



                }
                scheduleForDrawingArray[i - 1] = ScheduleForDrawing(subjectArray, timeArray,
                    teacherArray, auditoriumArray)

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
            dayRecyclerView(thirdSubjectsList, thirdTimeList, thirdAuditoriumList, thirdTeachersList, scheduleMap, scheduleForDrawingArray)
        }
    }

    private fun dayRecyclerView(thirdSubjectList: ArrayList<String>, thirdTimeList: ArrayList<String>,
                        thirdAuditoriumList: ArrayList<String>, thirdTeachersList: ArrayList<String>,
                        scheduleMap: MutableMap<String, ArrayList<String>>, scheduleForDrawingArray: ArrayList<ScheduleForDrawing>
                        ) {
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

        weekRecyclerView.adapter = FirstWeekAdapter(imageList, thirdSubjectList, thirdTimeList,
            thirdAuditoriumList, thirdTeachersList, scheduleMap, scheduleForDrawingArray)
    }
}