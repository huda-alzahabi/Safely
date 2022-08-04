package com.finalproj.safely.patient

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.finalproj.safely.R
import com.finalproj.safely.user.EditProfileActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class PatientHomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_home)
        clickedNavItem()
        val sharedPrefFile = "kotlin_shared_preference"
        var name = ""
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("Token", "")
        Log.d("YEYYY", "" + token)
        if (intent.getStringExtra("new_name") == null) {
            name = sharedPreferences.getString("Name", "")!!
            val textView = findViewById<TextView>(R.id.user_name)
            textView.text = name + "!"
        } else {
            name = intent.getStringExtra("new_name")!!
            val textView = findViewById<TextView>(R.id.user_name)
            textView.text = name + "!"
        }

        var appointment_day = ""
        var appointment_time = ""
        var doctor_name = ""
        var hospital_name = ""


        intent.getStringExtra("appointment_day")?.let {
            appointment_day = it
        }
        intent.getStringExtra("appointment_time")?.let {
            appointment_time = it
        }
        intent.getStringExtra("doctor_name")?.let {
            doctor_name = it
        }
        intent.getStringExtra("hospital_name")?.let {
            hospital_name = it
        }
        val home_date = findViewById<TextView>(R.id.home_date)
        val hospital = findViewById<TextView>(R.id.hospital_name)
        val upcoming_appointment = findViewById<TextView>(R.id.upcoming)
        val applicationContext: Context = applicationContext

        if (appointment_day != "" && appointment_time != "" && doctor_name != "" && hospital_name != "") {
            val date = "$appointment_day, $appointment_time"
            home_date.text = date
            val location =
                "$hospital_name" + applicationContext.resources.getString(R.string.hospital) + " / Dr. $doctor_name"
            hospital.setText(location)
        } else {

            upcoming_appointment.text =
                applicationContext.resources.getString(R.string.no_appointment)
            upcoming_appointment.setPadding(0, 20, 0, 0);
            home_date.text = ""
            hospital.text =
                "\u25BA " + applicationContext.resources.getString(R.string.discover) + "\n\u25BA " + applicationContext.resources.getString(
                    R.string.available_doctors) + "\n\u25BA " + applicationContext.resources.getString(
                    R.string.flow)
            hospital.setPadding(0, -10, 0, 0);
            val image = findViewById<ImageView>(R.id.calendar)
            val imageView = findViewById<ImageView>(R.id.loc)
            image.visibility = ImageView.GONE
            imageView.visibility = ImageView.GONE
        }


    }

    private fun clickedNavItem() {
        val bottomnav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomnav.menu.findItem(R.id.nav_home).isChecked = true;

        bottomnav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                }
                R.id.nav_hospitals -> {
                    val intent =
                        Intent(this@PatientHomeActivity, NearbyHospitalsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_location -> {
                    val intent = Intent(this@PatientHomeActivity, MapsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_records -> {
                    val intent =
                        Intent(this@PatientHomeActivity, AddMedicalRecordsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_profile -> {
                    val intent =
                        Intent(this@PatientHomeActivity, EditProfileActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }
    }
}