package com.finalproj.safely.doctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.finalproj.safely.R
import com.finalproj.safely.patient.Hospital
import java.util.*

class AddAvailabilityActivity : AppCompatActivity() {
    private var picked_day=""
    private var picked_times: MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_availability)

        val add_availability = findViewById<Button>(R.id.add_availability)
        add_availability.setOnClickListener{
            Log.d("date", picked_day)
            Log.d("time", picked_times.toString())
        }

        //create dropdown for times
        val times = resources.getStringArray(R.array.time_per_day)
        val timeAdapter = ArrayAdapter(this, R.layout.dropdown, times)
        val timeslot = findViewById<AutoCompleteTextView>(R.id.timeslot)
        timeslot.setAdapter(timeAdapter)
        timeslot.setOnItemClickListener { parent, view, position, id ->
            picked_times=addElement(times[position])
        }

        val datePicker = findViewById<DatePicker>(R.id.date_Picker)
        val today = Calendar.getInstance()

        datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        ) { view, year, month, day ->
            val month = month + 1
            picked_day =  "$day/$month/$year"

            Toast.makeText(this@AddAvailabilityActivity, picked_day, Toast.LENGTH_SHORT).show()
        }
        datePicker.setMinDate(System.currentTimeMillis() - 1000)

    }

    private fun addElement(s: String): MutableList<String> {
        if(picked_times.contains(s)){
            return picked_times
        }
        else{
            picked_times.add(s)
            return picked_times
        }

    }


}