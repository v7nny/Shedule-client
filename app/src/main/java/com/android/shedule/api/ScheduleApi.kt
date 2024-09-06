package com.android.shedule.api

import com.android.shedule.models.ScheduleDbEntity
import com.android.shedule.models.ScheduleForDrawing
import retrofit2.http.GET
import retrofit2.http.Path

interface ScheduleApi {

    @GET("schedule/week/{groupId}-{weekType}")
    suspend fun getScheduleByGroupIdAndWeekType(@Path(value = "groupId") groupId: Int,
                                                         @Path(value = "weekType") weekType: Int): Array<Array<ScheduleForDrawing>>
}