package com.manav.timetablemanagement.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.DayOfWeek

@Entity(tableName = "timetable")
data class Timetable(
    @PrimaryKey(autoGenerate = true) val pId: Int = 0,
    var classTiming: String,
    var courseCode: String,
    var roomNumber: String,
    val day: String // New column to store the day
)




