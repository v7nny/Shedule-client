package com.android.shedule

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.ComponentActivity
import com.android.shedule.api.GroupApi
import com.android.shedule.api.SpecializationApi
import com.android.shedule.retrofit.RetrofitGetter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlusActivity : ComponentActivity() {

    private val retrofit = RetrofitGetter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plus)
        overridePendingTransition(0,0)
        window.statusBarColor = resources.getColor(R.color.sheduleBarColor)
        window.navigationBarColor = resources.getColor(R.color.sheduleBarColor)

        groupSpinner()
        courseSpinner()
        specializationSpinner()
    }

    //Переход на экран с расписанием
    fun scheduleAct(view: View){
        val scheduleIntent = Intent(this, AnotherActivity::class.java)
        startActivity(scheduleIntent)
        finish()
    }
    
    //Переход на экран настроек
    fun settingsAct(view: View){
        val settingsIntent = Intent(this, SettingsActivity::class.java)
        startActivity(settingsIntent)
        finish()
    }

    private fun courseSpinner() {
        val courseSpinner = findViewById<Spinner>(R.id.courseSpinner)
        val course = arrayOf("--", "1", "2", "3", "4")

        //Создаём адаптер для Spinner'a
        val arrayAdapter = ArrayAdapter(this,
            R.layout.colored_spinner, course)

        //Задаём адаптеру параметры и назначаем его Spinner'у
        arrayAdapter.setDropDownViewResource(R.layout.color_spinner_dropdown_layout)
        courseSpinner.adapter = arrayAdapter
    }

    private fun specializationSpinner() {
        val specializationSpinner = findViewById<Spinner>(R.id.specializationSpinner)
        val specializationApi = retrofit.getRetrofit().create(SpecializationApi::class.java)
        val specializationArrayList: ArrayList<String> = arrayListOf("--")

        //Получаем специализации с сервера и добавляем их в ArrayList
        CoroutineScope(Dispatchers.IO).launch {
            for (i in 0 until specializationApi.getSpecializations().size) {
                specializationArrayList += specializationApi.getSpecializations()[i].name
            }
        }

        //Создаём адаптер для Spinner'a
        val arrayAdapter = ArrayAdapter(this,
            R.layout.colored_spinner, specializationArrayList)

        //Задаём адаптеру параметры и назначаем его Spinner'у
        arrayAdapter.setDropDownViewResource(R.layout.color_spinner_dropdown_layout)
        specializationSpinner.adapter = arrayAdapter
    }

    private fun groupSpinner() {
        val groupSpinner = findViewById<Spinner>(R.id.groupSpinner)
        val groupApi = retrofit.getRetrofit().create(GroupApi::class.java)
        val groupArrayList: ArrayList<String> = arrayListOf("--")

        //Получаем специализации с сервера и добавляем их в ArrayList
        CoroutineScope(Dispatchers.IO).launch {
            for (i in 0 until groupApi.getGroups().size){
                groupArrayList += groupApi.getGroups()[i].name
            }
        }

        //Создаём адаптер для Spinner'a
        val arrayAdapter = ArrayAdapter(this,
            R.layout.colored_spinner, groupArrayList)

        //Задаём адаптеру параметры и назначаем его Spinner'у
        arrayAdapter.setDropDownViewResource(R.layout.color_spinner_dropdown_layout, )
        groupSpinner.adapter = arrayAdapter
    }
}