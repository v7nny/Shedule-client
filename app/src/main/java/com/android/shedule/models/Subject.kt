package com.android.shedule.models

data class Subject(
    val id: Int,
    val name: String,
    val auditorium: String,
    val teacher: Teacher
)