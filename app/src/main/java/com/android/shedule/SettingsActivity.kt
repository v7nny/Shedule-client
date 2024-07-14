package com.android.shedule

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity


class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        overridePendingTransition(0,0)
        window.statusBarColor = resources.getColor(R.color.sheduleBarColor)
        window.navigationBarColor = resources.getColor(R.color.sheduleBarColor)
    }

    fun scheduleAct(view: View){
        val scheduleIntent = Intent(this, ScheduleActivity::class.java)
        startActivity(scheduleIntent)
        finish()
    }

    fun plusAct(view: View){
        val plusIntent = Intent(this, PlusActivity::class.java)
        startActivity(plusIntent)
        finish()
    }
}