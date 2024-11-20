package com.manav.timetablemanagement

import android.content.Context
import androidx.lifecycle.LiveData
import com.manav.timetablemanagement.Database.AppDatabase
import com.manav.timetablemanagement.Database.Timetable
import com.manav.timetablemanagement.Database.TimetableDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TimeTableRepository{

    companion object{
        private var appDataBase : AppDatabase?= null
        fun inisializeDB (context: Context) : AppDatabase? {
            return AppDatabase.getInstance(context)
        }

        fun insert(context: Context,timetable: Timetable){
            appDataBase = inisializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                appDataBase?.getDao()?.insertTimeTable(timetable)
            }
        }

        fun getAllTimetables(context: Context,day:String) : LiveData<List<Timetable>>?{
            appDataBase = inisializeDB(context)
            return appDataBase?.getDao()?.getAllTimetables(day)
        }

        fun updateTimetable(context: Context, timetable: Timetable) {
            appDataBase = inisializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                appDataBase?.getDao()?.updateTimetable(timetable)
            }
        }
    }

}




