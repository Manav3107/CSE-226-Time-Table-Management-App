package com.manav.timetablemanagement.Fragments

import android.annotation.SuppressLint
import com.manav.timetablemanagement.TimeTableAdapater


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.manav.timetablemanagement.Database.Timetable
import com.manav.timetablemanagement.R
import com.manav.timetablemanagement.TimeTableViewModel

class MondayFragment : Fragment() {

    private lateinit var viewModel: TimeTableViewModel
    private lateinit var timeTableAdapter: TimeTableAdapater
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_monday, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewMonday)
        timeTableAdapter = TimeTableAdapater(requireContext(), ArrayList()) { timetable ->
            showEditDialog(timetable)
        }
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = timeTableAdapter
        }

        viewModel = ViewModelProvider(this)[TimeTableViewModel::class.java]
        viewModel.getAllTimetables(requireContext(), "Monday")?.observe(viewLifecycleOwner, Observer {
            timeTableAdapter.setData(it as ArrayList<Timetable>)
        })

        view.findViewById<FloatingActionButton>(R.id.fabAddEntryMonday).setOnClickListener {
            showAddDataDialog()
        }
        return view
    }

    // Function to show a dialog for editing data
    @SuppressLint("MissingInflatedId")
    private fun showEditDialog(timetable: Timetable) {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_edit_data, null)
        val alertDialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Edit Timetable Entry")
            .create()

        val classTimingEditText = dialogView.findViewById<EditText>(R.id.edtClassTiming)
        val courseCodeEditText = dialogView.findViewById<EditText>(R.id.edtCourseCode)
        val roomNumberEditText = dialogView.findViewById<EditText>(R.id.edtRoomNumber)
        val saveButton = dialogView.findViewById<Button>(R.id.btnSave)

        classTimingEditText.setText(timetable.classTiming)
        courseCodeEditText.setText(timetable.courseCode)
        roomNumberEditText.setText(timetable.roomNumber)

        saveButton.setOnClickListener {
            val classTiming = classTimingEditText.text.toString().trim()
            val courseCode = courseCodeEditText.text.toString().trim()
            val roomNumber = roomNumberEditText.text.toString().trim()

            if (inputCheck(courseCode, roomNumber, classTiming)) {
                timetable.courseCode = courseCode
                timetable.roomNumber = roomNumber
                timetable.classTiming = classTiming

                viewModel.updateTimetable(requireContext(), timetable)
                Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_SHORT).show()
                alertDialog.dismiss()
            } else {
                Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
            }
        }

        alertDialog.show()
    }

    // Function to show a dialog for adding data
    @SuppressLint("MissingInflatedId")
    private fun showAddDataDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_data, null)
        val alertDialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Add Timetable Entry")
            .create()

        val classTimingEditText = dialogView.findViewById<EditText>(R.id.addDataClassTiming)
        val courseCodeEditText = dialogView.findViewById<EditText>(R.id.addDataCourseCode)
        val roomNumberEditText = dialogView.findViewById<EditText>(R.id.addDataRoomNumber)
        val saveButton = dialogView.findViewById<Button>(R.id.addDataButtonSave)

        saveButton.setOnClickListener {
            val courseCode = courseCodeEditText.text.toString().trim()
            val roomNumber = roomNumberEditText.text.toString().trim()
            val classTiming = classTimingEditText.text.toString().trim()
            val day = "Monday"

            if (inputCheck(courseCode, roomNumber, classTiming)) {
                val timetable = Timetable(0,classTiming, courseCode, roomNumber , day)
                viewModel.insert(requireContext(), timetable)
                Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()
                alertDialog.dismiss()
            } else {
                Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
            }
        }

        alertDialog.show()
    }

    // Helper function to check if input is valid
    private fun inputCheck(courseCode: String, roomNumber: String, classTiming: String): Boolean {
        return courseCode.isNotEmpty() && roomNumber.isNotEmpty() && classTiming.isNotEmpty()
    }
}
