package com.finalproj.safely.patient

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.finalproj.safely.R
import com.finalproj.safely.user.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class PatientProfileActivity : AppCompatActivity() {
    private val PREFERENCES = "kotlin_shared_preference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_profile)
        val submit_btn=findViewById<Button>(R.id.submit_changes)

        submit_btn.setOnClickListener{
            editProfile()
            val intent = Intent(this, PatientHomeActivity::class.java)
            startActivity(intent)
        }

        val logout= findViewById<ImageView>(R.id.logout_icon)
        logout.setOnClickListener{
            logout()
            Log.d("logout", "logout")
        }
        clickedNavItem()
    }

    private fun editProfile() {
        TODO("Not yet implemented")
    }

    private fun logout(){
        this.getSharedPreferences(PREFERENCES, 0).edit().clear().apply();
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun clickedNavItem() {
        val bottomnav=findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomnav.menu.findItem(R.id.nav_profile).isChecked = true;

        bottomnav.setOnItemSelectedListener {
                    item ->
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
