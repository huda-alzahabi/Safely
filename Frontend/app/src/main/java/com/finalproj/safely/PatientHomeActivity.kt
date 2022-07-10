package com.finalproj.safely

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class PatientHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_home)
        clickedNavItem()
    }

    private fun clickedNavItem() {
        findViewById<BottomNavigationView>(R.id.bottom_nav).menu.findItem(R.id.nav_home).isChecked = true;

        findViewById<BottomNavigationView>(R.id.bottom_nav)
            .setOnItemSelectedListener { item ->
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