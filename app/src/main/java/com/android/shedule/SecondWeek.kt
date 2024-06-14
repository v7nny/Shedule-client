package com.android.shedule

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondWeek : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_week)
        overridePendingTransition(0,0)
        window.statusBarColor = resources.getColor(R.color.sheduleBarColor)
        window.navigationBarColor = resources.getColor(R.color.sheduleBarColor)
    }

    fun toFirstWeek(view: View) {
        val firstWeekIntent = Intent(this, FirstWeek::class.java)
        startActivity(firstWeekIntent)
        finish()
    }
}