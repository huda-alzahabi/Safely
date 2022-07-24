package com.finalproj.safely.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.finalproj.safely.R
import com.finalproj.safely.patient.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class BookAppointmentActivity : AppCompatActivity() {
    private lateinit var doctor_id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_appointment)
        val imageView: ImageView = findViewById(R.id.r)
        Glide.with(this).load(R.drawable.gif).into(imageView)
        intent.getStringExtra("doctor_id")
            ?.let {
                doctor_id = it
                Log.d("doctor_id", it)
            }

        val apiService = RestApiService()
        apiService.getAvailabilityByDrId(doctor_id) {
            Log.d("availability", it.toString())
        }

        clickedNavItem()
    }


    private fun clickedNavItem() {
       val bottomnav= findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomnav.menu.findItem(R.id.nav_hospitals).isChecked = true;

        bottomnav.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_home -> {
                        val intent =
                            Intent(this@BookAppointmentActivity, PatientHomeActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.nav_hospitals -> {

                    }
                    R.id.nav_location -> {
                        val intent = Intent(this@BookAppointmentActivity, MapsActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.nav_records -> {
                        val intent = Intent(this@BookAppointmentActivity,
                            AddMedicalRecordsActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.nav_profile -> {
                        val intent = Intent(this@BookAppointmentActivity,
                            PatientProfileActivity::class.java)
                        startActivity(intent)
                    }
                }
                return@setOnItemSelectedListener true
            }
    }
}