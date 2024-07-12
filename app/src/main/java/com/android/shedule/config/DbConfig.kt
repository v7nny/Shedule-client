package com.android.shedule.config

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.shedule.dao.ScheduleBoxDAO
import com.android.shedule.models.ScheduleBoxDbEntity

@Database(entities = [ScheduleBoxDbEntity::class], version = 1)
abstract class DbConfig: RoomDatabase() {

    abstract fun getDao(): ScheduleBoxDAO

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