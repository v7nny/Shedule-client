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
import com.android.shedule.config.DbConfig
import com.android.shedule.models.ScheduleDbEntity
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

        val database = DbConfig.getDb(this)

        CoroutineScope(Dispatchers.IO).launch{
            val scheduleArray: Array<List<ScheduleDbEntity>> = Array(6){listOf()}
            val groupId = groupApi.getGroupByNameAndCourse(groupName, groupCourse.toInt()).id

            if (database.getScheduleDao().getScheduleByGroupIdAndWeekType(groupId, 1).isEmpty()){
                val scheduleFromServer = scheduleApi.getScheduleByGroupIdAndWeekType(groupId, 1)
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
                            scheduleForDrawingArray[i][j].subject.auditorium, 1)
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
                getScheduleByGroupIdAndWeekTypeAndWeek(groupId, 1, i)
            }
        }.start()

        runOnUiThread {
            dayRecyclerView(scheduleArray)
        }
    }


    private fun dayRecyclerView(scheduleForDrawingArray: Array<List<ScheduleDbEntity>>) {
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