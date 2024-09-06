package com.android.shedule

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.activity.ComponentActivity
import androidx.lifecycle.asLiveData
import com.android.shedule.api.GroupApi
import com.android.shedule.api.SpecializationApi
import com.android.shedule.config.DbConfig
import com.android.shedule.models.ScheduleBoxDbEntity
import com.android.shedule.retrofit.RetrofitGetter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlusActivity : ComponentActivity() {

    private val retrofit = RetrofitGetter()
    private val groupApi = retrofit.getRetrofit().create(GroupApi::class.java)
    private val specializationApi = retrofit.getRetrofit().create(SpecializationApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plus)
        overridePendingTransition(0,0)
        window.statusBarColor = resources.getColor(R.color.sheduleBarColor)
        window.navigationBarColor = resources.getColor(R.color.sheduleBarColor)

        getCourseSpinner().adapter = getAdapter(getCourseList())
        getSpecializationSpinner().adapter = getAdapter(getSpecializationNameList())
        getGroupSpinner().adapter = getAdapter(groupSpinner())

        sendGroup()
    }

    //Получаем Spinner'ы
    private fun getCourseSpinner(): Spinner = findViewById(R.id.courseSpinner)
    private fun getSpecializationSpinner(): Spinner = findViewById(R.id.specializationSpinner)
    private fun getGroupSpinner(): Spinner = findViewById(R.id.groupSpinner)

    //Получаем ArrayList'ы для Spinner'ов
    private fun getCourseList(): ArrayList<String> = arrayListOf("--", "1", "2", "3", "4")

    private fun getSpecializationNameList(): ArrayList<String> {
        val specializationNameArrayList: ArrayList<String> = arrayListOf("--")

        //Получаем специализации с сервера и добавляем их в ArrayList
        CoroutineScope(Dispatchers.IO).launch{
            for (i in 0 until specializationApi.getSpecializations().size) {
                specializationNameArrayList += specializationApi.getSpecializations()[i].name
            }
        }
        return specializationNameArrayList
    }

    //Получаем Adapter
    private fun getAdapter(list: List<String>): SpinnerAdapter {
        //Создаём адаптер для Spinner'a
        val  arrayAdapter = ArrayAdapter(this, R.layout.colored_spinner, list)

        //Задаём адаптеру параметры и назначаем его Spinner'у
        arrayAdapter.setDropDownViewResource(R.layout.color_spinner_dropdown_layout)

        return arrayAdapter
    }


    //Переход на экран с расписанием
    fun scheduleAct(view: View){
        val scheduleIntent = Intent(this, ScheduleActivity::class.java)
        startActivity(scheduleIntent)
        finish()
    }
    
    //Переход на экран настроек
    fun settingsAct(view: View){
        val settingsIntent = Intent(this, SettingsActivity::class.java)
        startActivity(settingsIntent)
        finish()
    }


    private fun groupSpinner(): ArrayList<String> {
        val groupArrayList: ArrayList<String> = arrayListOf("--")

        getCourseSpinner().onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val course = getCourseSpinner().selectedItem.toString()
                val specializationName = getSpecializationSpinner().selectedItem.toString()

                CoroutineScope(Dispatchers.IO).launch {
                    for (i in 0 until groupApi.getGroupsBySpecializationAndCourse(specializationName, course).size){
                        groupArrayList.add(groupApi.getGroupsBySpecializationAndCourse(specializationName, course)[i].name)
                    }
                }
                groupArrayList.clear()
                groupArrayList.add("--")
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        return groupArrayList
    }

    private fun sendGroup() {
        val saveButton = findViewById<Button>(R.id.sendButton)
        val database = DbConfig.getDb(this)

        saveButton.setOnClickListener {
            val scheduleBox = ScheduleBoxDbEntity(
                getGroupSpinner().selectedItem.toString(),
                getCourseSpinner().selectedItem.toString(),
                getSpecializationSpinner().selectedItem.toString()
            )

            database.getScheduleBoxDao().getAllScheduleBox().asLiveData().observe(this){
                if(!it.contains(scheduleBox))
                    Thread {database.getScheduleBoxDao().insertScheduleBox(scheduleBox)}.start()
        }

            val scheduleActivityIntent = Intent(this, ScheduleActivity::class.java)
            startActivity(scheduleActivityIntent)
            finish()
        }
    }

}