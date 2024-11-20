package com.manav.timetablemanagement.Database


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TimetableDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTimeTable(timetable: Timetable)

    @Query("SELECT * FROM timetable WHERE day = :day")
    fun getAllTimetables(day:String) : LiveData<List<Timetable>>

    @Update
    suspend fun updateTimetable(timetable: Timetable)
}


