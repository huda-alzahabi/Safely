package com.finalproj.safely.hospital

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.finalproj.safely.R
import com.finalproj.safely.patient.PatientInfo
import com.finalproj.safely.user.RestApiService

class HospitalInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospital_info)
        val submit = findViewById<Button>(R.id.submit_info)
        submit.setOnClickListener {

            val phone_num = findViewById<EditText>(R.id.phone_number).text.toString()
            val country = findViewById<EditText>(R.id.country).text.toString()
            val city = findViewById<EditText>(R.id.city).text.toString()
            val street = findViewById<EditText>(R.id.street).text.toString()
            val building = findViewById<EditText>(R.id.building).text.toString()
            var user="62d907013a51ab400a6dfe96"

            submitInfo(phone_num, country, city, street, building, user)

        }
    }

    private fun submitInfo(phoneNum: String, country: String, city: String, street: String, building: String, user: String) {

        val apiService = RestApiService()
        val hospitalInfo = HospitalInfo(
            phone_number = phoneNum,
            country = country,
            city = city,
            street = street,
            building = building,
            user = user
        )
        apiService.addHospitalInfo(hospitalInfo) {
            Log.d("PATIENT", hospitalInfo.toString())
            if (it != null) {
                Log.d("Hospital", it.toString())
            } else {
                Log.d("NOO", "Error adding new hospital")
            }
        }
    }
}