package com.finalproj.safely.patient

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.finalproj.safely.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class PatientHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_home)
        clickedNavItem()
        val sharedPrefFile = "kotlin_shared_preference"

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("Token", "")
        Log.d("YEYYY", "" + token)
        val name = sharedPreferences.getString("Name", "")
//        val name = intent.getStringExtra("User name").toString()
        val textView: TextView = findViewById(R.id.user_name) as TextView
        textView.text = name + "!"
        Log.d("HUDA", name + "!")


    }

    private fun clickedNavItem() {
        val bottomnav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomnav.menu.findItem(R.id.nav_home).isChecked = true;

        bottomnav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                }
                R.id.nav_hospitals -> {
                    val intent =
                        Intent(this@PatientHomeActivity, NearbyHospitalsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_location -> {
                    val intent = Intent(this@PatientHomeActivity, MapsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_records -> {
                    val intent =
                        Intent(this@PatientHomeActivity, AddMedicalRecordsActivity::class.java)
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