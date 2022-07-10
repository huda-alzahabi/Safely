package com.finalproj.safely

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class PatientHospitalsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_hospitals)

        clickedNavItem()
    }

    private fun clickedNavItem() {
        val bottomnav= findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomnav.menu.findItem(R.id.nav_hospitals).isChecked = true;

        bottomnav.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_home -> {
                        val intent =
                            Intent(this@PatientHospitalsActivity, PatientHomeActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(0,0);
                    }
                    R.id.nav_hospitals -> {

                    }
                    R.id.nav_location -> {
                        val intent = Intent(this@PatientHospitalsActivity, MapsActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(0,0);
                    }
                    R.id.nav_records -> {
                        val intent = Intent(this@PatientHospitalsActivity,
                            MedicalRecordsActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(0,0);
                    }
                    R.id.nav_profile -> {
                        val intent = Intent(this@PatientHospitalsActivity,
                            PatientProfileActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(0,0);
                    }
                }
                return@setOnItemSelectedListener true
            }
    }
}
