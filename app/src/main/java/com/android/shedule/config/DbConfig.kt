package com.android.shedule.config

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.shedule.dao.ScheduleBoxDAO
import com.android.shedule.dao.ScheduleDAO
import com.android.shedule.models.ScheduleBoxDbEntity
import com.android.shedule.models.ScheduleDbEntity

@Database(entities = [ScheduleBoxDbEntity::class, ScheduleDbEntity::class] , version = 1)
abstract class DbConfig: RoomDatabase() {

    abstract fun getScheduleBoxDao(): ScheduleBoxDAO
    abstract fun getScheduleDao(): ScheduleDAO

    companion object{
        fun getDb(context: Context): DbConfig{
            return Room.databaseBuilder(
                context.applicationContext,
                DbConfig::class.java,
                "scheduleSQLite.db"
            ).build()
        }
    }
}