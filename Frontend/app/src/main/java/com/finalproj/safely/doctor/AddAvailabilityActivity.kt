package com.finalproj.safely.doctor

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import com.finalproj.safely.R
import com.finalproj.safely.user.RestApiService
import java.util.*

class AddAvailabilityActivity : AppCompatActivity() {

    private var picked_day = ""
    private var picked_times: MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_availability)

        val layout =layoutInflater.inflate(R.layout.toast,findViewById(R.id.toast_wrapper)) as LinearLayout

        val toast=layout.findViewById<TextView>(R.id.toast_text)


        val sharedPrefFile = "kotlin_shared_preference"
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        val doctor = sharedPreferences.getString("doctor_id", "")!!

        val add_availability = findViewById<Button>(R.id.submit_day)
        add_availability.setOnClickListener {
            toast.text=  "Availability added successfully"
            Toast(this@AddAvailabilityActivity).apply {
                duration = Toast.LENGTH_LONG
                setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 30)
                view = layout
            }.show()
            Log.d("doctor", doctor)
            Log.d("date", picked_day)
            Log.d("time", picked_times.toString())
            addAvailability(picked_day, picked_times, "62e1d7548e3dfb09ce418ca7")
            picked_times.clear()
        }

        //create dropdown for times
        var times = resources.getStringArray(R.array.time_per_day)
        val timeAdapter = ArrayAdapter(this, R.layout.dropdown, times)
        val timeslot = findViewById<AutoCompleteTextView>(R.id.timeslot)
        timeslot.setAdapter(timeAdapter)
        timeslot.setOnItemClickListener { parent, _view, position, id ->
            picked_times = addElement(times[position])
            toast.text=  picked_times.toString()
            Toast(this@AddAvailabilityActivity).apply {
                duration = Toast.LENGTH_LONG
                setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 30)
                view = layout
            }.show()
        }

        val datePicker = findViewById<DatePicker>(R.id.date_Picker)
        val today = Calendar.getInstance()

        datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        ) { view, year, month, day ->
            val month = month + 1
            picked_day = "$day/$month/$year"
        }
        datePicker.setMinDate(System.currentTimeMillis() - 1000)

        //remove header from datepicker
        val datePickerLayout = datePicker.getChildAt(0) as LinearLayout
        val datePickerHeader = datePickerLayout.getChildAt(0) as LinearLayout
        datePickerHeader.visibility = View.GONE

        val back = findViewById<TextView>(R.id.go_home)
        back.setOnClickListener {
            val intent = Intent(this, DoctorHomeActivity::class.java)
            startActivity(intent)
        }

    }

    private fun addAvailability(
        pickedDay: String,
        pickedTimes: MutableList<String>,
        doctor: String,
    ) {
        val sharedPrefFile = "kotlin_shared_preference"
        var sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("Token", "")!!
        val apiService = RestApiService()
        val availability = DrAvailabilityInfo(day = pickedDay, times = pickedTimes, doctor = doctor)
        apiService.addDrAvailability(availability, token) {
            if (it != null) {
                Log.d("availability", it.message!!)
            } else {
                Log.d("NOO", "Error adding new availability")
            }
        }
    }

    private fun addElement(s: String): MutableList<String> {
        if (picked_times.contains(s)) {
            return picked_times
        } else {
            picked_times.add(s)
            return picked_times
        }
    }
}