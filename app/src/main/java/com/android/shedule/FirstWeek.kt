package com.android.shedule

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Spinner
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FirstWeek : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_week)
        window.statusBarColor = resources.getColor(R.color.sheduleBarColor)
        window.navigationBarColor = resources.getColor(R.color.sheduleBarColor)
        overridePendingTransition(0,0)
    }




    fun toSecondWeek(view: View) {
        val secondWeekIntent = Intent(this, SecondWeek::class.java)
        startActivity(secondWeekIntent)
        finish()
    }



}