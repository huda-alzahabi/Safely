package com.finalproj.safely.user

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.finalproj.safely.R
import com.finalproj.safely.doctor.DoctorHomeActivity
import com.finalproj.safely.hospital.HospitalHomeActivity
import com.finalproj.safely.patient.AddMedicalRecordsActivity
import com.finalproj.safely.patient.MapsActivity
import com.finalproj.safely.patient.NearbyHospitalsActivity
import com.finalproj.safely.patient.PatientHomeActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class EditProfileActivity : AppCompatActivity() {
    private val sharedPrefFile = "kotlin_shared_preference"
    lateinit var sharedPreferences: SharedPreferences
    lateinit var user: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val submit_btn = findViewById<Button>(R.id.submit_changes)
        val edit_name = findViewById<EditText>(R.id.edit_name)
        val edit_email = findViewById<EditText>(R.id.edit_email)

        sharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("Name", "")!!
        val email = sharedPreferences.getString("Email", "")!!
        val usertype = sharedPreferences.getString("user_type", "")!!
        user = sharedPreferences.getString("user_id", "")!!

        edit_name.setText(name)
        edit_email.setText(email)
        submit_btn.setOnClickListener {
            val edit_name = findViewById<EditText>(R.id.edit_name).text.toString()
            val edit_email = findViewById<EditText>(R.id.edit_email).text.toString()
            val edit_pass = findViewById<EditText>(R.id.edit_pass).text.toString()

            editProfile(edit_name, edit_email, edit_pass, usertype)
            if (usertype == "patient") {
                val intent = Intent(this, PatientHomeActivity::class.java)
                startActivity(intent)
            } else if (usertype == "doctor") {
                val intent = Intent(this, DoctorHomeActivity::class.java)
                startActivity(intent)
            } else if (usertype == "hospital") {
                val intent = Intent(this, HospitalHomeActivity::class.java)
                startActivity(intent)
            }


        }

        val logout = findViewById<ImageView>(R.id.logout_icon)
        logout.setOnClickListener {
            logout()
        }
    }

    private fun editProfile(
        editedName: String,
        editedEmail: String,
        editedPass: String,
        usertype: String,
    ) {

        val token = sharedPreferences.getString("Token", "")!!
        val apiService = RestApiService()
        val userInfo = UserInfo(editedName, editedEmail, editedPass, usertype)
        apiService.editUserProfile(user, userInfo, token) {
            if (it != null) {
                Log.d("User Updated", it.toString())
            } else {
                Log.d("NO", "Error updating user")
            }
        }
    }

    private fun logout() {
        this.getSharedPreferences(sharedPrefFile, 0).edit().clear().apply();
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}
