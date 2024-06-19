package com.android.shedule.models

import com.google.gson.annotations.SerializedName

data class Schedule(
    val id: Int,
    @SerializedName("subjectFirst")
    val subjectFirst: Subjects,
    @SerializedName("firstTime")
    val firstTime : Time,
    @SerializedName("subjectSecond")
    val subjectSecond: Subjects,
    @SerializedName("secondTime")
    val secondTime: Time
)
