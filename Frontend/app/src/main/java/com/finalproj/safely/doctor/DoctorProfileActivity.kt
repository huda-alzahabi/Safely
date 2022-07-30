package com.finalproj.safely.doctor

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import com.finalproj.safely.R
import com.finalproj.safely.patient.DoctorInfo
import com.finalproj.safely.user.RestApiService

class DoctorProfileActivity : AppCompatActivity() {
    private lateinit var hospital_id: String
    val sharedPrefFile = "kotlin_shared_preference"
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_profile)

        var profession = ""
        //create dropdown for nationalities
        val professions = resources.getStringArray(R.array.profession)
        val professionAdapter = ArrayAdapter(this, R.layout.dropdown, professions)
        val prof = findViewById<AutoCompleteTextView>(R.id.profession)
        prof.setAdapter(professionAdapter)

        intent.getStringExtra("hospital_id")?.let {
            hospital_id = it
        }
        sharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        val user = sharedPreferences.getString("user_id", "")!!

        val submit = findViewById<Button>(R.id.submit_doctor)
        submit.setOnClickListener {
            profession = prof.text.toString()
            val experience = findViewById<EditText>(R.id.experience).text.toString()
            submitInfo(profession, experience, hospital_id, user)
        }
    }

    private fun submitInfo(
        specialty: String,
        experience: String,
        hospitalId: String,
        user: String,
    ) {
        sharedPreferences = this.getSharedPreferences(sharedPrefFile,
        Context.MODE_PRIVATE)

        val apiService = RestApiService()
        val doctor =
            DoctorInfo(specialty = specialty,
                years_of_experience = experience,
                hospital = hospitalId,
                user = user)

        val token = sharedPreferences.getString("Token", "")!!
        apiService.addDoctor(doctor,token) {
            Log.d("DOCC", doctor.toString())
            if (it != null) {
                Log.d("DOCT", it.toString())

                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("doctor_id", it.message)
                editor.apply()
                editor.commit()
                Log.d("DOCTORID", it.message!!)
            } else {
                Log.d("NOO", "Error adding new doctor")
            }
        }
        val intent = Intent(this@DoctorProfileActivity, DoctorHomeActivity::class.java)
        startActivity(intent)
    }
}
