package com.android.shedule

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.constraintlayout.widget.ConstraintLayout



class ScheduleActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_schedule)
            overridePendingTransition(0,0)
            window.statusBarColor = resources.getColor(R.color.sheduleBarColor)
            window.navigationBarColor = resources.getColor(R.color.sheduleBarColor)
    }


    fun plusAct(view: View){
        val plusIntent = Intent(this, PlusActivity::class.java)
        startActivity(plusIntent)
    }

    fun settingsAct(view: View){
        val settingsIntent = Intent(this, SettingsActivity::class.java)
        startActivity(settingsIntent)
    }

    fun firstWeek(view: View) {
        val firstWeekIntent = Intent(this, FirstWeek::class.java)
        startActivity(firstWeekIntent)
    }
}


