package com.finalproj.safely.user

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import androidx.appcompat.app.AppCompatActivity
import com.finalproj.safely.R
import com.finalproj.safely.doctor.AddAvailabilityActivity
import com.finalproj.safely.doctor.AllHospitalsActivity
import com.finalproj.safely.doctor.DoctorHomeActivity
import com.finalproj.safely.hospital.HospitalHomeActivity
import com.finalproj.safely.patient.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
        val sharedPrefFile = "kotlin_shared_preference"
        var sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("Token", "")!!
        val user_type = sharedPreferences.getString("user_type", "")!!

        GlobalScope.launch {
            delay(4000L)
            if (token.isEmpty()) {
                val intent = Intent(this@MainActivity, WelcomeActivity::class.java)
                startActivity(intent)
                finish()
            } else if(user_type == "patient") {
                val intent = Intent(this@MainActivity, PatientHomeActivity::class.java)
                startActivity(intent)
                finish()
            }
            else if(user_type == "doctor") {
                val intent = Intent(this@MainActivity, DoctorHomeActivity::class.java)
                startActivity(intent)
                finish()
            }
            else if(user_type == "hospital") {
                val intent = Intent(this@MainActivity, HospitalHomeActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                val intent = Intent(this@MainActivity, WelcomeActivity::class.java)
                startActivity(intent)
                finish()
            }


        }
} }

