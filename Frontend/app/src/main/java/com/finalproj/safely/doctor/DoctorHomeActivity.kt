package com.finalproj.safely.doctor

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.finalproj.safely.R
import com.finalproj.safely.patient.HospitalResponse
import com.finalproj.safely.user.MainActivity
import com.finalproj.safely.user.RestApiService

class DoctorHomeActivity : AppCompatActivity() {
    val sharedPrefFile = "kotlin_shared_preference"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_home)

        var sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("Token", "")!!
        val doctorId=sharedPreferences.getString("doctor_id", "")!!
        val apiService = RestApiService()
        apiService.getAppointmentsByDrId(doctorId,token) {
            Log.d("appointments", it.toString())
        }
        val logout = findViewById<ImageView>(R.id.logout_doc)
        logout.setOnClickListener {
            logout()
            Log.d("logout", "logout")
        }
    }
    private fun logout() {
        this.getSharedPreferences(sharedPrefFile, 0).edit().clear().apply();
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}