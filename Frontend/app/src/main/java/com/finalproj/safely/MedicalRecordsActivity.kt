package com.finalproj.safely

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class MedicalRecordsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical_records)
        clickedNavItem()
    }

    private fun clickedNavItem() {
        findViewById<BottomNavigationView>(R.id.bottom_nav).menu.findItem(R.id.nav_records).isChecked = true;

        findViewById<BottomNavigationView>(R.id.bottom_nav)
            .setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_home -> {
                        val intent =
                            Intent(this@MedicalRecordsActivity, PatientHomeActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.nav_hospitals -> {
                        val intent = Intent(this@MedicalRecordsActivity,
                            PatientHospitalsActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.nav_location -> {
                        val intent = Intent(this@MedicalRecordsActivity, MapsActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.nav_records -> {

                    }
                    R.id.nav_profile -> {
                        val intent =
                            Intent(this@MedicalRecordsActivity, PatientProfileActivity::class.java)
                        startActivity(intent)
                    }
                }
                return@setOnItemSelectedListener true
            }
    }
}
