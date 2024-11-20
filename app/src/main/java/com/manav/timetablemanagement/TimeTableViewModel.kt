package com.manav.timetablemanagement

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.manav.timetablemanagement.Database.Timetable

class TimeTableViewModel: ViewModel(){

    fun insert(context: Context, timetable: Timetable){
        TimeTableRepository.insert(context,timetable)
    }

    fun getAllTimetables(context: Context,day :String) : LiveData<List<Timetable>>?{
        return TimeTableRepository.getAllTimetables(context,day)
    }

    fun updateTimetable(context: Context, timetable: Timetable) {
        TimeTableRepository.updateTimetable(context, timetable)
    }
}