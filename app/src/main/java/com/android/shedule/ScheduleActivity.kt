package com.android.shedule

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.asLiveData
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


    fun plusAct(view: View){
        val plusIntent = Intent(this, PlusActivity::class.java)
        startActivity(plusIntent)
        finish()
    }

    fun settingsAct(view: View){
        val settingsIntent = Intent(this, SettingsActivity::class.java)
        startActivity(settingsIntent)
        finish()
    }

    private fun getScheduleBox() {
        val recyclerView = findViewById<RecyclerView>(R.id.scheduleBoxRecyclerView)
        val database = DbConfig.getDb(this)

        recyclerView.layoutManager = LinearLayoutManager(this)

        database.getDao().getAllScheduleBox().asLiveData().observe(this){
            recyclerView.adapter = ScheduleBoxAdapter(it, R.drawable.schedule_box, this)
        }
    }
}


