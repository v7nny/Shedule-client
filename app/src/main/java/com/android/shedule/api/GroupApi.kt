package com.android.shedule.api

import com.android.shedule.models.Group
import retrofit2.http.GET
import retrofit2.http.Path

interface GroupApi {
    @GET("groups")
    suspend fun getGroups(): List<Group>

    @GET("groups/{name}-{course}")
    suspend fun getGroupsBySpecializationAndCourse(@Path(value = "name") name: String,
                                                   @Path(value = "course") course: Int): List<Group>
}