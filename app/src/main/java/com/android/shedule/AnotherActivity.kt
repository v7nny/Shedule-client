package com.android.shedule

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity


class AnotherActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_another)
            overridePendingTransition(0,0)
            window.statusBarColor = resources.getColor(R.color.sheduleBarColor)
            window.navigationBarColor = resources.getColor(R.color.sheduleBarColor)
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
}


