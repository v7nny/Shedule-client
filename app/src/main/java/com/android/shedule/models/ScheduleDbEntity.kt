package com.android.shedule.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Schedule", primaryKeys = ["group_id", "subject", "day_of_the_week", "time", "week_type", "teacher", "auditorium"])
data class ScheduleDbEntity(
//    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "group_id") var groupId: Int,
    @ColumnInfo(name = "day_of_the_week") var dayOfTheWeek: Int,
    @ColumnInfo(name = "subject") var subject: String,
    @ColumnInfo(name = "teacher") var teacher: String,
    @ColumnInfo(name = "time") var time: String,
    @ColumnInfo(name = "auditorium") var auditorium: String,
    @ColumnInfo(name = "week_type") var weekType: Int
)
