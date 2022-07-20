package com.finalproj.safely.patient

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.finalproj.safely.R


class PatientInfoActivity : AppCompatActivity() {
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

            selectedSex =  when(checkedId) {
                R.id.radio_button_1 -> findViewById<RadioButton>(R.id.radio_button_1).text.toString()
                R.id.radio_button_2 -> findViewById<RadioButton>(R.id.radio_button_2).text.toString()
                R.id.radio_button_3 -> findViewById<RadioButton>(R.id.radio_button_3).text.toString()
                else -> {"NONE" }
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

            submitInfo(phone_num, dob, selectedNationality, selectedStatus, selectedSex)

        }
    }

    private fun submitInfo(
        phone_num: String,
        dob: String,
        nationality: String,
        maritalStatus: String,
        sex: String,
    ) {
        Log.d("Phone Number", phone_num)
        Log.d("Date of Birth", dob)
        Log.d("Nationality", nationality)
        Log.d("Marital Status", maritalStatus)
        Log.d("Sex", sex)
        val intent = Intent(this@PatientInfoActivity, MapsActivity::class.java)
        startActivity(intent)
    }
}
