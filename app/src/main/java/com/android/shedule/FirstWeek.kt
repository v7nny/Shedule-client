package com.android.shedule

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.shedule.adapter.FirstWeekAdapter
import com.android.shedule.api.GroupApi
import com.android.shedule.api.ScheduleApi
import com.android.shedule.config.DbConfig
import com.android.shedule.models.ScheduleDbEntity
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

        val database = DbConfig.getDb(this)

        CoroutineScope(Dispatchers.IO).launch{
            val scheduleArray: Array<List<ScheduleDbEntity>> = Array(6){listOf()}
            val groupId = groupApi.getGroupByNameAndCourse(groupName, groupCourse.toInt()).id

                if (database.getScheduleDao().getScheduleByGroupIdAndWeekType(groupId, 0).isEmpty()){
                    val scheduleFromServer = scheduleApi.getScheduleByGroupIdAndWeekType(groupId, 0)
                    saveToDb(scheduleFromServer, scheduleArray, groupId, database)
                }
            loadScheduleFromDb(scheduleArray, groupId, database)
        }
    }

    private fun saveToDb(scheduleForDrawingArray: Array<Array<ScheduleForDrawing>>,
                  scheduleArray: Array<List<ScheduleDbEntity>>, groupId: Int, database: DbConfig) {
        Thread{
            for (i in 0 until 6) {
                for (j in 0 until scheduleForDrawingArray[i].size) {
                    database.getScheduleDao().insertSchedule(
                        ScheduleDbEntity(groupId, i, scheduleForDrawingArray[i][j].subject.name,
                            scheduleForDrawingArray[i][j].subject.teacher.fullName, scheduleForDrawingArray[i][j].time.time,
                            scheduleForDrawingArray[i][j].subject.auditorium, 0)
                    )
                }
            }
            loadScheduleFromDb(scheduleArray, groupId, database)
        }.start()
    }

    private fun loadScheduleFromDb(scheduleArray: Array<List<ScheduleDbEntity>>,
                                                                groupId: Int, database: DbConfig) {
        Thread{
            for(i in 0 until 6){
                scheduleArray[i] = database.getScheduleDao().
                getScheduleByGroupIdAndWeekTypeAndWeek(groupId, 0, i)
            }
        }.start()

        runOnUiThread {
            dayRecyclerView(scheduleArray)
        }
    }

    private fun dayRecyclerView(scheduleForDrawingArray: Array<List<ScheduleDbEntity>>) {
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