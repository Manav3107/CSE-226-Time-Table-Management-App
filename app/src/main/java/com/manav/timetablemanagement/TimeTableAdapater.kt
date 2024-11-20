package com.manav.timetablemanagement

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.manav.timetablemanagement.Database.Timetable

class TimeTableAdapater(private val context:Context, private var timeTableList: ArrayList<Timetable>
                        ,private val onEdit: (Timetable) -> Unit): RecyclerView.Adapter<TimeTableAdapater.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.new_cardview_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return timeTableList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem :Timetable = timeTableList[position]
        holder.classTiming.text = currentItem.classTiming

        val formattedText = context.getString(
            R.string.formatted_text_course_code,
            currentItem.courseCode
        )

        val formattedText2 = context.getString(
            R.string.formatted_text_room_number,
            currentItem.roomNumber
        )

        holder.courseCode.text = formattedText
        holder.roomNumber.text = formattedText2

        // Long-click listener for editing
        holder.itemView.setOnLongClickListener {
            onEdit(currentItem)
            true
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val courseCode : TextView = itemView.findViewById(R.id.tvCourseCode)
        val roomNumber : TextView = itemView.findViewById(R.id.tvRoomNumber)
        val classTiming : TextView = itemView.findViewById(R.id.tvClassTiming)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newTimeTableList : ArrayList<Timetable>){
        timeTableList.clear()
        timeTableList.addAll(newTimeTableList)
        notifyDataSetChanged()
    }
}

