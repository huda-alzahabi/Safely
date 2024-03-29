package com.finalproj.safely.hospital

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.finalproj.safely.R
import com.finalproj.safely.doctor.AddAvailabilityActivity
import com.finalproj.safely.user.RestApiService

class HospitalInfoActivity : AppCompatActivity() {
    private val sharedPrefFile = "kotlin_shared_preference"
    lateinit var sharedPreferences: SharedPreferences
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
            val outpatient_clinic = findViewById<EditText>(R.id.hours).text.toString()

            sharedPreferences = this.getSharedPreferences(sharedPrefFile,
                Context.MODE_PRIVATE)
            val user = sharedPreferences.getString("user_id", "")!!

            submitInfo(phone_num, country, city, street, building, user, outpatient_clinic)

        }
    }

    private fun submitInfo(
        phoneNum: String,
        country: String,
        city: String,
        street: String,
        building: String,
        user: String,
        outpatient_clinic: String,
    ) {
        sharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("Token", "")!!
        val apiService = RestApiService()
        val hospitalInfo = HospitalInfo(
            phone_number = phoneNum,
            country = country,
            city = city,
            street = street,
            building = building,
            user = user,
            outpatient_clinic = outpatient_clinic,
        )
        apiService.addHospitalInfo(hospitalInfo, token) {
            if (it != null) {

                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                val hospitalAddress =
                    street + ", " + city + ", " + country
                editor.putString("hospital_id", it.message)
                editor.putString("phone_num", phoneNum)
                editor.putString("hospital_address", hospitalAddress)
                editor.putString("hours", outpatient_clinic)
                editor.apply()
                editor.commit()

            } else {
                Log.d("NO", "Error adding new hospital")
            }
        }
        val intent = Intent(this@HospitalInfoActivity, HospitalLocationActivity::class.java)
        startActivity(intent)
    }
}