package com.finalproj.safely

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class PatientProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_profile)
        clickedNavItem()
    }

    private fun clickedNavItem() {
        val bottomnav=findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomnav.menu.findItem(R.id.nav_profile).isChecked = true;

        bottomnav.setOnItemSelectedListener {
                    item ->
                when (item.itemId) {
                    R.id.nav_home -> {
                        val intent =
                            Intent(this@PatientProfileActivity, PatientHomeActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.nav_hospitals -> {
                        val intent = Intent(this@PatientProfileActivity,
                            PatientHospitalsActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.nav_location -> {
                        val intent = Intent(this@PatientProfileActivity, MapsActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.nav_records -> {
                        val intent =
                            Intent(this@PatientProfileActivity, MedicalRecordsActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.nav_profile -> {
                       }
                }
                return@setOnItemSelectedListener true
            }
    }
}
