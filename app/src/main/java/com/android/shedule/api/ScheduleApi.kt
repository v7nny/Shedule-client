package com.android.shedule.api

import com.android.shedule.models.Schedule
import retrofit2.http.GET
import retrofit2.http.Path

interface ScheduleApi {

    @GET("schedule/{id}")
    suspend fun getOne(@Path(value = "id") id: Int): Schedule

    @GET("schedule/groups/{id}")
    suspend fun getScheduleByGroupId(@Path(value = "id") id: Int): List<Schedule>
}