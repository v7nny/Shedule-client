package com.android.shedule

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.activity.ComponentActivity
import androidx.core.view.get
import com.android.shedule.api.GroupApi
import com.android.shedule.api.SpecializationApi
import com.android.shedule.models.Specialization
import com.android.shedule.retrofit.RetrofitGetter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.util.stream.Collectors.toList

class PlusActivity : ComponentActivity() {

    private val retrofit = RetrofitGetter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plus)
        overridePendingTransition(0,0)
        window.statusBarColor = resources.getColor(R.color.sheduleBarColor)
        window.navigationBarColor = resources.getColor(R.color.sheduleBarColor)

        getCourseSpinner().adapter = getAdapter(getCourseList())
        getSpecializationSpinner().adapter = getAdapter(getSpecializationList())
        groupSpinner()
    }

    //Получаем Spinner'ы
    private fun getCourseSpinner(): Spinner = findViewById(R.id.courseSpinner)
    private fun getSpecializationSpinner(): Spinner = findViewById(R.id.specializationSpinner)
    private fun getGroupSpinner(): Spinner = findViewById(R.id.groupSpinner)

    //Получаем ArrayList'ы для Spinner'ов
    private fun getCourseList(): ArrayList<String> = arrayListOf("1", "2", "3", "4")

    private fun getSpecializationList(): ArrayList<String> {
        val specializationApi = retrofit.getRetrofit().create(SpecializationApi::class.java)
        val specializationArrayList: ArrayList<String> = arrayListOf("")

        //Получаем специализации с сервера и добавляем их в ArrayList
        CoroutineScope(Dispatchers.Main).launch{
            for (i in 0 until specializationApi.getSpecializations().size) {
                specializationArrayList += specializationApi.getSpecializations()[i].name
            }
        }

        return specializationArrayList
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

    private fun groupSpinner() {
        val groupApi = retrofit.getRetrofit().create(GroupApi::class.java)
        val groupArrayList: ArrayList<String> = arrayListOf("")
        val specializationApi = retrofit.getRetrofit().create(SpecializationApi::class.java)
        var course = 0
        var positions = 0


        getSpecializationSpinner().onItemSelectedListener = object: AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position1: Int, id: Long) {

                CoroutineScope(Dispatchers.IO).launch {
                    for (i in 0 until specializationApi.getSpecializations().size) {
                        getSpecializationList() += specializationApi.getSpecializations()[i].name
                    }

                }
                positions = position1

                groupArrayList.clear()
                groupArrayList.add("")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        getCourseSpinner().onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                course = getCourseList()[position].toInt()

                CoroutineScope(Dispatchers.IO).launch {
                for (i in 0 until groupApi.getGroupsBySpecializationAndCourse(getSpecializationList()[positions], course).size){
                        groupArrayList.add(groupApi.getGroupsBySpecializationAndCourse(getSpecializationList()[positions], course)[i].name)
                    }
                }
                groupArrayList.clear()
                groupArrayList.add("")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        getGroupSpinner().adapter = getAdapter(groupArrayList)
    }
}