package com.android.shedule

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.shedule.adapter.FirstWeekAdapter

class SecondWeek : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_week)
        overridePendingTransition(0,0)
        window.statusBarColor = resources.getColor(R.color.sheduleBarColor)
        window.navigationBarColor = resources.getColor(R.color.sheduleBarColor)

//        dayRecyclerView()
    }

    fun toFirstWeek(view: View) {
        val firstWeekIntent = Intent(this, FirstWeek::class.java)
        startActivity(firstWeekIntent)
        finish()
    }

//    fun dayRecyclerView() {
//        val weekRecyclerView = findViewById<RecyclerView>(R.id.weekRecyclerView)
//
//        val imageList = arrayListOf(
//            R.drawable.monday,
//            R.drawable.tuesday,
//            R.drawable.wednesday,
//            R.drawable.thursday,
//            R.drawable.friday,
//            R.drawable.saturday
//        )
//
//        weekRecyclerView.layoutManager = LinearLayoutManager(this)
//
//        weekRecyclerView.adapter = FirstWeekAdapter(imageList)
//    }
}