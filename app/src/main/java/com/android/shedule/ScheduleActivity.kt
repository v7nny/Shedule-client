package com.android.shedule

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.shedule.adapter.ScheduleBoxAdapter
import com.android.shedule.api.GroupApi
import com.android.shedule.api.ScheduleApi
import com.android.shedule.api.SpecializationApi
import com.android.shedule.retrofit.RetrofitGetter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ScheduleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
        overridePendingTransition(0,0)
        window.statusBarColor = resources.getColor(R.color.sheduleBarColor)
        window.navigationBarColor = resources.getColor(R.color.sheduleBarColor)
        getText()

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

    private fun getText() {
        val recyclerView = findViewById<RecyclerView>(R.id.scheduleBoxRecyclerView)
        val intent: Intent  = intent

        val groupNameList: ArrayList<String> = arrayListOf()
        groupNameList.add(intent.getStringExtra("groupName").toString())

        val courseList: ArrayList<String> = arrayListOf()
        courseList.add(intent.getStringExtra("course").toString())
        val specializationNameList: ArrayList<String> = arrayListOf()
        specializationNameList.add(intent.getStringExtra("specializationName").toString())

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = ScheduleBoxAdapter(specializationNameList, courseList, groupNameList, R.drawable.schedule_box)
    }



    fun onFirstWeek(view: View) {

        val intent: Intent = intent

        val groupName = intent.getStringExtra("groupName").toString()
        val course = intent.getStringExtra("course").toString()

        val firstWeek = Intent(this, FirstWeek::class.java)

        firstWeek.putExtra("groupName", groupName)
        firstWeek.putExtra("course", course)

        startActivity(firstWeek)
    }
}


