package com.finalproj.safely

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit.RestAdapter

class PatientHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_home)
        clickedNavItem()
        val sharedPrefFile = "kotlinsharedpreference"

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        val token= sharedPreferences.getString("Token","")
        Log.d("YEYYY",""+token)
    }

    private fun clickedNavItem() {
       val bottomnav=findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomnav.menu.findItem(R.id.nav_home).isChecked = true;

        bottomnav.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_home -> {
                       }
                    R.id.nav_hospitals -> {
                        val intent =
                            Intent(this@PatientHomeActivity, PatientHospitalsActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.nav_location -> {
                        val intent = Intent(this@PatientHomeActivity, MapsActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.nav_records -> {
                        val intent =
                            Intent(this@PatientHomeActivity, MedicalRecordsActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.nav_profile -> {
                        val intent =
                            Intent(this@PatientHomeActivity, PatientProfileActivity::class.java)
                        startActivity(intent)
                    }
                }
                 true
            }
    }
}