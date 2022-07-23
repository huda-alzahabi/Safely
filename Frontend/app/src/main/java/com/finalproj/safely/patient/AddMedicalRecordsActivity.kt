package com.finalproj.safely.patient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.finalproj.safely.R
import com.finalproj.safely.user.RestApiService
import com.google.android.material.bottomnavigation.BottomNavigationView

class AddMedicalRecordsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_medical_records)

        val submit = findViewById<Button>(R.id.submit_records)

        submit.setOnClickListener {
            var height = findViewById<EditText>(R.id.height).text.toString()
            var weight = findViewById<EditText>(R.id.weight).text.toString()
            var allergies = findViewById<EditText>(R.id.allergies).text.toString()
            var blood_type = findViewById<EditText>(R.id.blood_type).text.toString()
            var current_medications =
                findViewById<EditText>(R.id.current_medications).text.toString()
            var chronic_diseases = findViewById<EditText>(R.id.chronic_diseases).text.toString()

            submitMedicalRecords(height,
                weight,
                allergies,
                blood_type,
                current_medications,
                chronic_diseases)
        }
        clickedNavItem()
    }

    private fun submitMedicalRecords(
        height: String,
        weight: String,
        allergies: String,
        bloodType: String,
        currentMedications: String,
        chronicDiseases: String,
    ) {
        val apiService = RestApiService()
        val medicalRecords = MedicalRecords(
            current_medications = currentMedications,
            chronic_diseases = chronicDiseases,
            allergies = allergies,
            blood_type = bloodType,
            weight = weight,
            height = height
            )
        apiService.submitRecords(medicalRecords) {
            Log.d("Records", medicalRecords.toString())
            if (it.toString() != null) {
                Log.d("Medss", it.toString())
            } else {
                Log.d("NOO", "Error registering new user")
            }
        }
    }
    private fun clickedNavItem() {
        val bottomnav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomnav.menu.findItem(R.id.nav_records).isChecked = true;

        bottomnav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent =
                        Intent(this@AddMedicalRecordsActivity, PatientHomeActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_hospitals -> {
                    val intent = Intent(this@AddMedicalRecordsActivity,
                        NearbyHospitalsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_location -> {
                    val intent = Intent(this@AddMedicalRecordsActivity, MapsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_records -> {

                }
                R.id.nav_profile -> {
                    val intent =
                        Intent(this@AddMedicalRecordsActivity, PatientProfileActivity::class.java)
                    startActivity(intent)
                }
            }
            return@setOnItemSelectedListener true
        }
    }
}
