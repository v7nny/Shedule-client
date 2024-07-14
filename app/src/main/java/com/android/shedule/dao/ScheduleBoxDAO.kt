package com.android.shedule.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.android.shedule.models.ScheduleBoxDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleBoxDAO {
    @Insert
    fun insertScheduleBox(scheduleBox: ScheduleBoxDbEntity)

    @Delete
    fun deleteScheduleBox(scheduleBox: ScheduleBoxDbEntity)

    @Query("SELECT * FROM ScheduleBox")
    fun getAllScheduleBox(): Flow<List<ScheduleBoxDbEntity>>
}