package com.android.shedule.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.android.shedule.models.ScheduleDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDAO {
    @Insert
    fun insertSchedule(schedule: ScheduleDbEntity)

    @Delete
    fun deleteSchedule(schedule: ScheduleDbEntity)

    @Update
    fun updateSchedule(schedule: ScheduleDbEntity)

    @Query("SELECT * FROM Schedule WHERE group_id = :groupId AND week_type = :weekType")
    fun getScheduleByGroupIdAndWeekType(groupId: Int, weekType: Int): List<ScheduleDbEntity>

    @Query("SELECT * FROM Schedule WHERE group_id = :groupId AND week_type = :weekType AND day_of_the_week = :dayOfTheWeek ORDER BY time")
    fun getScheduleByGroupIdAndWeekTypeAndWeek(groupId: Int, weekType: Int, dayOfTheWeek: Int): List<ScheduleDbEntity>
}