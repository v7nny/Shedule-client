package com.android.shedule.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ScheduleBox", primaryKeys = ["group_name", "course", "specialization_name"])
data class ScheduleBoxDbEntity(
    @ColumnInfo(name = "group_name") val groupName: String,
    @ColumnInfo(name = "course") val course: String,
    @ColumnInfo(name = "specialization_name") val specializationName: String
)
