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
            sharedPreferences = this.getSharedPreferences(sharedPrefFile,
                Context.MODE_PRIVATE)
            val user = sharedPreferences.getString("user_id", "")!!

            submitInfo(phone_num, country, city, street, building, user)

        }
    }

    private fun submitInfo(phoneNum: String, country: String, city: String, street: String, building: String, user: String) {
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
            user = user
        )
        apiService.addHospitalInfo(hospitalInfo,token) {
            Log.d("HospitalResponse", hospitalInfo.toString())
            if (it != null) {
                Log.d("HOSPITAL", it.toString())

                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("hospital_id", it.message)
                editor.apply()
                editor.commit()
                Log.d("HOSPID", it.message!!)
            } else {
                Log.d("NOO", "Error adding new hospital")
            }
        }
        val intent = Intent(this@HospitalInfoActivity, HospitalLocationActivity::class.java)
        startActivity(intent)
    }
}