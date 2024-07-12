package com.android.shedule

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.shedule.adapter.ScheduleBoxAdapter
import com.android.shedule.config.DbConfig
import com.android.shedule.models.ScheduleBox
import com.android.shedule.models.ScheduleBoxDbEntity

class ScheduleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
        overridePendingTransition(0,0)
        window.statusBarColor = resources.getColor(R.color.sheduleBarColor)
        window.navigationBarColor = resources.getColor(R.color.sheduleBarColor)

        getScheduleBox()
    }

    private val scheduleBoxArray: ArrayList<ScheduleBox> = arrayListOf()

    fun plusAct(view: View){
        val plusIntent = Intent(this, PlusActivity::class.java)
        startActivity(plusIntent)
    }

    fun settingsAct(view: View){
        val settingsIntent = Intent(this, SettingsActivity::class.java)
        startActivity(settingsIntent)
    }


//    @SuppressLint("NotifyDataSetChanged")
    private fun getScheduleBox() {
        val recyclerView = findViewById<RecyclerView>(R.id.scheduleBoxRecyclerView)
        val intent: Intent  = intent

        scheduleBoxArray.add(
            ScheduleBox(intent.getStringExtra("groupName").toString(),
            intent.getStringExtra("course").toString(),
            intent.getStringExtra("specializationName").toString())
        )

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = ScheduleBoxAdapter(scheduleBoxArray, R.drawable.schedule_box)
    }

    fun toFirstWeek(view: View) {
        val intent: Intent = intent

        val groupName = intent.getStringExtra("groupName").toString()
        val course = intent.getStringExtra("course").toString()

        val firstWeek = Intent(this, FirstWeek::class.java)

        firstWeek.putExtra("groupName", groupName)
        firstWeek.putExtra("course", course)

        startActivity(firstWeek)
    }
}


