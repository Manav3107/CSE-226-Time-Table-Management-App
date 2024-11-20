package com.manav.timetablemanagement.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Timetable::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getDao(): TimetableDao

    companion object {
        private const val DATABASE_NAME = "TimeTableDatabase"
        @Volatile
        var Instance: AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase? {
            if(Instance == null){
                synchronized(AppDatabase::class.java){
                    if(Instance == null){
                        Instance = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return Instance
        }
    }
}
