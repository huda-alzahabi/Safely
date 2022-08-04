package com.finalproj.safely.patient

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.finalproj.safely.R
import com.finalproj.safely.user.RestApiService


class PatientInfoActivity : AppCompatActivity() {
    private val sharedPrefFile = "kotlin_shared_preference"
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_patient_info)
        var selectedSex = ""
        //create dropdown for nationalities
        val nationalities = resources.getStringArray(R.array.nationalities)
        val nationalityAdapter = ArrayAdapter(this, R.layout.dropdown, nationalities)
        val nationality = findViewById<AutoCompleteTextView>(R.id.nationalityTextView)
        nationality.setAdapter(nationalityAdapter)

        //create dropdown for marital_status
        val marital = resources.getStringArray(R.array.marital_status)
        val maritalAdapter = ArrayAdapter(this, R.layout.dropdown, marital)
        val maritalStatus = findViewById<AutoCompleteTextView>(R.id.maritalTextView)
        maritalStatus.setAdapter(maritalAdapter)

        val submit = findViewById<Button>(R.id.submit)

        // save the selected sex
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->

            selectedSex = when (checkedId) {
                R.id.radio_button_1 -> findViewById<RadioButton>(R.id.radio_button_1).text.toString()
                R.id.radio_button_2 -> findViewById<RadioButton>(R.id.radio_button_2).text.toString()
                R.id.radio_button_3 -> findViewById<RadioButton>(R.id.radio_button_3).text.toString()
                else -> {
                    "NONE"
                }
            }
        }
        submit.setOnClickListener {

            //save the phone number and the date of birth
            val phone_num = findViewById<EditText>(R.id.phone_num).text.toString()
            val dob = findViewById<EditText>(R.id.dob).text.toString()

            // save the selected nationality
            val selectedNationality = nationality.text.toString()

            // save the selected maritalStatus
            val selectedStatus = maritalStatus.text.toString()

            //get user id from shared preferences

            sharedPreferences = this.getSharedPreferences(sharedPrefFile,
                Context.MODE_PRIVATE)
            val user = sharedPreferences.getString("user_id", "")!!

            submitInfo(phone_num, dob, selectedNationality, selectedStatus, selectedSex, user)

        }
    }

    private fun submitInfo(
        phone_num: String,
        dob: String,
        nationality: String,
        maritalStatus: String,
        sex: String,
        user: String,
    ) {
        sharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("Token", "")!!
        val apiService = RestApiService()
        val patientInfo = PatientInfo(
            phone_number = phone_num,
            date_of_birth = dob,
            nationality = nationality,
            sex = sex,
            marital_status = maritalStatus,
            user = user
        )
        apiService.addPatient(patientInfo, token) {
            Log.d("PATIENT", patientInfo.toString())
            if (it != null) {
                Log.d("Patienttt", it.toString())

                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("patient_id", it._id)
                editor.apply()
                editor.commit()
                Log.d("PATIENTID", it._id!!)
            } else {
                Log.d("NOO", "Error adding new patient")
            }
        }
        val intent = Intent(this@PatientInfoActivity, MapsActivity::class.java)
        startActivity(intent)
    }
}
