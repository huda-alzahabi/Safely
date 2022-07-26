package com.finalproj.safely.patient

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.finalproj.safely.R
import com.finalproj.safely.user.RestApiService
import com.finalproj.safely.user.UserInfo

class ConfirmAppointmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_appointment)


        val appointment_day = intent.getStringExtra("appointment_day")
        val appointment_time = intent.getStringExtra("appointment_time")
        val doctor_id = intent.getStringExtra("doctor_id")
        val doctor_name = intent.getStringExtra("doctor_name")
        val hospital_id = intent.getStringExtra("hospital_id")
        val hospital_name = intent.getStringExtra("hospital_name")

        val sharedPrefFile = "kotlin_shared_preference"
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        val patient_id = sharedPreferences.getString("patient_id", "")!!
        val patient_name = sharedPreferences.getString("Name", "")!!

        Log.d("DAY", appointment_day!!)
        Log.d("TIME", appointment_time!!)
        Log.d("DOCTOR_ID", doctor_id!!)
        Log.d("DOCTOR_NAME", doctor_name!!)
        Log.d("PATIENT_ID", patient_id)
        Log.d("PATIENT_NAME", patient_name)
        Log.d("HOSPITAL_ID", hospital_id!!)
        Log.d("HOSPITAL_NAME", hospital_name!!)

        val day = findViewById<TextView>(R.id.appointment_date)
        val time = findViewById<TextView>(R.id.appointment_time)
        val location = findViewById<TextView>(R.id.appointment_location)
        val doctor = findViewById<TextView>(R.id.appointment_doctor)

        day.setText(appointment_day)
        time.setText(appointment_time)
        location.setText(hospital_name)
        doctor.setText(doctor_name)

        day.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_today_24, 0, 0, 0);
        time.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_access_time_24,
            0,
            0,
            0);
        location.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_hospital_location_on_24,
            0,
            0,
            0);
        doctor.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_medical_services_24,
            0,
            0,
            0);

        val confirm_appointment = findViewById<Button>(R.id.confirm_appointment)
        confirm_appointment.setOnClickListener {
            confirm_appointment(appointment_day,
                appointment_time,
                doctor_id,
                doctor_name,
                hospital_id,
                hospital_name,
                patient_id,
                patient_name)
        }


    }

    private fun confirm_appointment(
        appointmentDay: String,
        appointmentTime: String,
        doctorId: String,
        doctorName: String,
        hospitalId: String,
        hospitalName: String,
        patientId: String,
        patientName: String,
    ) {
        val apiService = RestApiService()
        val appointmentInfo = AppointmentInfo(
            day = appointmentDay,
            time = appointmentTime,
            doctor_id = doctorId,
            doctor_name = doctorName,
            hospital_id = hospitalId,
            hospital_name = hospitalName,
            patient_id = patientId,
            patient_name = patientName
        )

        apiService.bookAppointment(appointmentInfo) {
            Log.d("Appointment", appointmentInfo.toString())
            if (it != null) {
                Log.d("it", it.toString())

            } else {
                Log.d("OKKKKK", "Error booking appointment")

            }
        }
    }
}