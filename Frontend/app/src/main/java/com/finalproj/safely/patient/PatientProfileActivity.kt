package com.finalproj.safely.patient

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
import com.finalproj.safely.user.MainActivity
import com.finalproj.safely.user.RestApiService
import com.finalproj.safely.user.UserInfo
import com.google.android.material.bottomnavigation.BottomNavigationView


class PatientProfileActivity : AppCompatActivity() {
    private val sharedPrefFile = "kotlin_shared_preference"
    lateinit var sharedPreferences: SharedPreferences
    lateinit var user: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_profile)

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
            val intent = Intent(this, PatientHomeActivity::class.java)
            intent.putExtra("new_name", edit_name)
            startActivity(intent)
        }

        val logout = findViewById<ImageView>(R.id.logout_icon)
        logout.setOnClickListener {
            logout()
            Log.d("logout", "logout")
        }
        clickedNavItem()
    }

    private fun editProfile(
        editedName: String,
        editedEmail: String,
        editedPass: String,
        usertype: String,
    ) {
        val apiService = RestApiService()
        val userInfo = UserInfo(editedName, editedEmail, editedPass, usertype)
        apiService.editUserProfile(user, userInfo) {
            if (it != null) {
                Log.d("User Updated", it.toString())
            } else {
                Log.d("NOO", "Error updating user")
            }
        }
    }

    private fun logout() {
        this.getSharedPreferences(sharedPrefFile, 0).edit().clear().apply();
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun clickedNavItem() {
        val bottomnav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomnav.menu.findItem(R.id.nav_profile).isChecked = true;

        bottomnav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent =
                        Intent(this@PatientProfileActivity, PatientHomeActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_hospitals -> {
                    val intent = Intent(this@PatientProfileActivity,
                        NearbyHospitalsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_location -> {
                    val intent = Intent(this@PatientProfileActivity, MapsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_records -> {
                    val intent =
                        Intent(this@PatientProfileActivity, AddMedicalRecordsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_profile -> {
                }
            }
            return@setOnItemSelectedListener true
        }
    }
}
