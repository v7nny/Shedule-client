package com.android.shedule.api

import com.android.shedule.models.Group
import retrofit2.http.GET

interface GroupApi {
    @GET("groups")
    suspend fun getGroups(): List<Group>
}