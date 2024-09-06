package com.android.shedule.models

import com.google.gson.annotations.SerializedName

data class ScheduleForDrawing(
    @SerializedName("subject")
    val subject: Subject,
    @SerializedName("time")
    val time : Time
)
