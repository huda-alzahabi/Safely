package com.finalproj.safely.patient

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.finalproj.safely.R
import com.finalproj.safely.user.RestApiService
import com.finalproj.safely.user.UserInfo
import org.json.JSONObject

class ConfirmAppointmentActivity : AppCompatActivity() {
    lateinit var appointment_day: String
    lateinit var appointment_time: String
    lateinit var doctor_id: String
    lateinit var doctor_name: String
    lateinit var hospital_id: String
    lateinit var hospital_name: String
    lateinit var patient_id: String
    lateinit var patient_name: String
    val sharedPrefFile = "kotlin_shared_preference"
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_appointment)


        appointment_day = intent.getStringExtra("appointment_day")!!
        appointment_time = intent.getStringExtra("appointment_time")!!
        doctor_id = intent.getStringExtra("doctor_id")!!
        doctor_name = intent.getStringExtra("doctor_name")!!
        hospital_id = intent.getStringExtra("hospital_id")!!
        hospital_name = intent.getStringExtra("hospital_name")!!


         sharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        patient_id = sharedPreferences.getString("patient_id", "")!!
        patient_name = sharedPreferences.getString("Name", "")!!

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

        val token = sharedPreferences.getString("Token", "")!!

        apiService.bookAppointment(appointmentInfo,token) {
            Log.d("Appointment", appointmentInfo.toString())
            if (it != null) {
                Log.d("it", it.toString())

            } else {
                Log.d("OKKKKK", "Error booking appointment")

            }
        }
        val newAppointment=applicationContext.resources.getString(R.string.new_appointment)
        val details= "$appointmentDay, $appointmentTime"
        pushNotification(this,
            "e3AAQ4LAQBm7Yi4FZJVK3N:APA91bHPott-AD22wGSmg_kUYeUCms5nVejBDFAkIaK7LL-Pr4aiz8V-3Naci3DgQYQIDYFIZrEM9WrGZ4FjbykTXVbk0OChD-pJWpAAwnLICdzca1_PbYsGtfSVRxKzTamiKFcgYs8D",
            newAppointment,
            details)
        val intent =
            Intent(this@ConfirmAppointmentActivity, PatientHomeActivity::class.java)
        intent.putExtra("appointment_day", appointment_day)
        intent.putExtra("appointment_time", appointment_time)
        intent.putExtra("doctor_name", doctor_name)
        intent.putExtra("hospital_name", hospital_name)
        startActivity(intent)
    }
    val BASE_URL: String = "https://fcm.googleapis.com/fcm/send"
    val SERVER_KEY: String =
        "f2HBLJinT9qGNDS2BXaYI7:APA91bHt-CMoBYv_C-mMEHixdHFNVdDHvEPn-WnZDobTwB8xy9-1RSjdA_usv91mF1EAy03ZczCwG4iZA79bKNMNGNKP5Rnot1oHfaMTAj4mbgSu82j8HxoVCv_GG2Q9fYsuAc7TO80v"


    fun pushNotification(context: Context, token: String, title: String, message: String) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val requestQueue = Volley.newRequestQueue(context)
        try {
            val json = JSONObject()
            val notification = JSONObject()
            notification.put("title", title)
            notification.put("body", message)
            json.put("to", token)
            json.put("notification", notification)
            val request: JsonObjectRequest = object : JsonObjectRequest(Request.Method.POST,
                BASE_URL,
                json,
                Response.Listener<JSONObject>
                { response ->
                    Log.d("TAGTest", response.toString())
                },
                Response.ErrorListener {
                    Log.d("TAGTest", "error: ${it.message}")
                }) {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["Authorization"] = "key=$SERVER_KEY"
                    headers["Content-Type"] = "application/json"
                    return headers
                }

            }
            requestQueue.add(request)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}