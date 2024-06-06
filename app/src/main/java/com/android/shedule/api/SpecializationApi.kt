package com.android.shedule.api

import com.android.shedule.models.Specialization
import retrofit2.http.GET

interface SpecializationApi {
    @GET("specializations")
    suspend fun getSpecializations(): List<Specialization>
}